package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import dev.inkremental.meta.model.*
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.*
import org.gradle.api.tasks.Optional
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.lang.reflect.*
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile
import kotlin.reflect.KFunction
import kotlin.reflect.KTypeParameter
import kotlin.reflect.full.*
import kotlin.reflect.jvm.kotlinFunction

abstract class GenerateModelTask : DefaultTask() {

    @get:Input abstract var quirks: InkrementalQuirks
    @get:Input @get:Optional abstract var configuration: Configuration?
    @get:Input abstract var javadocContains: String
    @get:Input abstract var packageName: String
    @get:Input @get:Optional abstract var manualSetterName: String?
    @get:Classpath abstract var jarFiles: Iterable<File>
    @get:Classpath abstract var nullabilitySourceFiles: Iterable<File>
    @get:Classpath @get:Optional abstract var dependencies: Iterable<File>?
    @get:OutputFile abstract var outputFile: File

    lateinit var camelCaseName: String

    var isSourceSdk: Boolean = false

    private lateinit var nullabilityHolder : NullabilityHolder

    @TaskAction
    fun run() {
        initialize()
        val model = createModel()
        dumpModel(model)
        val json = Json {
            encodeDefaults = false
            prettyPrint = false
            serialModule = ModelModule
        }
        val outputStr = json.stringify(ModuleModel.serializer(), model)
        outputFile.writeText(outputStr)
    }

    private fun initialize() {
        val configuration = configuration
        if(configuration != null) {
            val resolved = configuration.resolvedConfiguration
            val libs = resolved.firstLevelModuleDependencies.flatMap { it.moduleArtifacts }.map { it.file }.toList()
            val deps = resolved.resolvedArtifacts.map { it.file }.toList()

            project.logger.info("Generator task resolved $configuration\nLibs:\n" +
                    libs.map { it.absolutePath }.sorted().joinToString(separator = "\n") { "    $it" } +
                    "\nDeps ${deps.size}:\n" +
                    deps.map { it.absolutePath }.sorted().joinToString(separator = "\n") { "    $it" })

            jarFiles = libs
            nullabilitySourceFiles = libs
            dependencies = deps + (dependencies ?: listOf())
        }

        nullabilityHolder = NullabilityHolder(
            isSourceSdk,
            URLClassLoader(
                nullabilitySourceFiles.map { it.jarUrl() }.toTypedArray(),
                javaClass.classLoader))
    }

    private fun Class<*>.createViewModel(quirks: InkrementalQuirks): Pair<ViewModel, List<String>>? {
        val quirk = quirks[canonicalName]

        val name = when(val alias = quirk?.get("__viewAlias")) {
            null -> simpleName
            false -> return null
            else -> alias as String
        }

        val attrs = attrsSequence().filter {
            quirk?.get("${it.setterName}:${it.type.plainType}") != false && quirk?.get(it.setterName) != false
        }.toList()

        val plainType = kotlin.asClassName()

        val viewModel = ViewModel(
            name = name,
            plainType = plainType,
            parametrizedType = asParameterizedType(),
            attrs = attrs
        ).also { it.backlinkAttrs() }
        return viewModel to kotlin.superclasses.mapNotNull { it.qualifiedName }
    }

    fun viewsSequence(): Sequence<Class<*>> {
        val urls = jarFiles.map { it.jarUrl() } + dependencies!!.map { it.jarUrl() }
        val loader = URLClassLoader(urls.toTypedArray(), javaClass.classLoader)
        val viewClass = loader.loadClass(VIEW_CNAME)

        return jarFiles.asSequence()
            .flatMap { JarFile(it).entries().toSequence() }
            .map { it.name }
            .filter { it.endsWith(".class") }
            .sorted()
            .onEach { nullabilityHolder.fillClassNullabilityInfo(it) }
            .map { it.replace(".class", "").replace("/", ".") }
            .filter { '$' !in it }
            .mapNotNull {
                try {
                    loader.loadClass(it)
                } catch (e: NoClassDefFoundError) {
                    // Simply skip this class.
                    e.printStackTrace()
                    null
                }
            }
            .filter { viewClass.isAssignableFrom(it) && it.isPublic }
    }

    fun Class<*>.attrsSequence(): Sequence<AttrModel> =
        declaredMethods
            .asSequence()
            .filter { it.isPublic && !it.isSynthetic && !it.isBridge }
            .mapNotNull { method ->
                val setterName = method.name
                val parameterType = method.getParameterType() ?: return@mapNotNull null
                val (attrName, isListener) = parseAttrName(setterName, method.parameterCount) ?: return@mapNotNull null
                val isNullable = nullabilityHolder.isParameterNullable(method) ?: isListener

                val functions = parameterType.declaredMethods.sortedBy { it.name }

                val samLike = functions.size == 1
                // TODO View.setOutlineProvider(ViewOutlineProvider): is not listener, but is SAM-like. Should we use lambdas for it?

                val isArray = parameterType.isArray

                val plainType = parameterType.kotlin.asClassName()

                val parametrizedType = parameterType.asParameterizedType()

                val lambdaType = if(isListener && samLike) {
                    functions.first().kotlinFunction!!.toFunctionModel().asLambdaTypeName()
                } else {
                    null
                }

                val type = TypeModel(
                    name = parameterType.simpleName,
                    isSamLike = samLike,
                    isInterface = parameterType.isInterface,
                    plainType = plainType,
                    lambdaType = lambdaType,
                    parametrizedTypeUnsafe = parametrizedType,
                    functions = functions
                        .mapNotNull {
                            try {
                                it.kotlinFunction
                            } catch (e: Throwable) {
                                // https://youtrack.jetbrains.com/issue/KT-17064
                                logger.info("Unable to process method: ${it.declaringClass.canonicalName}.${it.name}", e)
                                null
                            }
                        }
                        .map { it.toFunctionModel() }
                )

                AttrModel(
                    name = attrName,
                    setterName = setterName,
                    type = type,
                    isArray = isArray,
                    isVarArg = method.isVarArgs,
                    isListener = isListener,
                    isNullable = isNullable
                )
            }
            .sortedWith(compareBy({ it.name }, { it.type.name }))

    private fun createModel(): ModuleModel {
        val superRequests: MutableList<Pair<ViewModel, List<String>>> = mutableListOf()
        val superViews: MutableMap<String, ViewModel> = mutableMapOf()

        val views = viewsSequence()
            .mapNotNull { it.createViewModel(quirks) }
            .onEach { model ->
                superViews[model.first.plainType.toString()] = model.first
                superRequests += model
            }
            .map { it.first }
            .toList()

        superRequests.forEach { (view, supers) ->
            view.superType = supers.mapNotNull { superViews[it] }.firstOrNull()
        }

        return ModuleModel(
            name = camelCaseName,
            javadocContains = javadocContains,
            packageName = packageName,
            manualSetter = manualSetterName?.let { MemberName(packageName, it) },
            views = views
        )
    }

    private fun dumpModel(model: ModuleModel) {
        model.views.forEach { view ->
            logger.warn("[gen] ${view.plainType}")
            view.attrs.forEach {
                val t = it.type

                val flag = when {
                    it.isListener -> "L"
                    it.isNullable -> "F?"
                    else -> "F"
                }
                logger.warn("[gen]   ${it.name} | ${t.argType} | $flag")
                logger.warn("[gen]       plain   ${t.plainType}")
                t.lambdaType?.let { lambdaType ->
                    logger.warn("[gen]       lmbd    $lambdaType")
                }
                t.parametrizedTypeUnsafe?.let { parametrizedType ->
                    logger.warn("[gen]       prmtrzd $parametrizedType")
                }
            }
            logger.warn("[gen]")
        }
    }
}

internal fun parseAttrName(input : String, parameterCount: Int): Pair<String, Boolean>? = when {
    input.matches(Regex("^setOn.*Listener$")) -> {
        "on" + input.substring(5, input.length - 8) to true
    }
    input.startsWith("set")
        && input.length > 3
        && input[3].isUpperCase()
        && parameterCount == 1 -> {
            input[3].toLowerCase() + input.substring(4) to false
    }
    else -> null
}
