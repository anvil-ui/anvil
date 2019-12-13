package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import dev.inkremental.meta.model.*
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.TaskAction
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

open class DSLGeneratorTask : DefaultTask() {

    var configuration: Configuration? = null
    lateinit var jarFiles: List<File>
    lateinit var nullabilitySourceFiles: List<File>
    var dependencies: List<File> = listOf()

    lateinit var javadocContains: String
    lateinit var outputDirectory: String
    lateinit var camelCaseName: String
    lateinit var packageName: String
    var manualSetter: MemberName? = null

    var isSourceSdk: Boolean = false

    private lateinit var nullabilityHolder : NullabilityHolder

    @TaskAction
    fun run() {
        initialize()
        val model = createModel()
        dumpModel(model)
        renderModel(model)
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
            dependencies = deps + dependencies
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

        return ViewModel(
            name = name,
            plainType = plainType,
            parametrizedType = asParameterizedType(),
            attrs = attrs
        ).also { view -> view.attrs.forEach { it.owner = view } } to kotlin.superclasses.mapNotNull { it.qualifiedName }
    }

    fun viewsSequence(): Sequence<Class<*>> {
        val urls = jarFiles.map { it.jarUrl() } + dependencies.map { it.jarUrl() }
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
        val quirks = project.inkremental.quirks

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

    private fun renderModel(model: ModuleModel) {
        val fileSpec = FileSpec.builder(packageName, camelCaseName)

        val setterBody = buildCodeBlock {
            beginControlFlow("return when (name)")
            model.views
                .flatMap { it.attrs }
                .groupBy { it.name }
                .mapValues { (_, it) -> it.sortedBy { it.type.name } }
                .forEach { (name, attrs) ->
                    val filtered = attrs.filter { a ->
                        attrs.none { b ->
                            // TODO what?
                            a != b && a.type == b.type && a.owner.isAssignableFrom(b.owner)
                        }
                    }

                    beginControlFlow("%S -> when", name)
                    filtered.map {
                        if (it.isListener) {
                            it.buildListener()
                        } else {
                            it.buildSetter()
                        }
                    }.forEach { add(it) }

                    add("else -> false\n")
                    endControlFlow()
                }
            add("else -> false\n")
            endControlFlow()
        }
        val setterFunction = FunSpec.builder("set")
            .addParameter("v", VIEW)
            .addParameter("name", STRING)
            .addParameter("arg", ANY_N)
            .addParameter("old", ANY_N)
            .returns(BOOLEAN)
            .addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
            .addCode(setterBody)
            .build()

        val setterObject = TypeSpec.objectBuilder(ClassName(PACKAGE, "${camelCaseName}Setter"))
            .addKdoc("""DSL for creating views and settings their attributes.
                |This file has been generated by
                |{@code dev.inkremental.meta.gradle $name}
                |$javadocContains.
                |Please, don't edit it manually unless for debugging.""".trimMargin())
            .addModifiers(KModifier.PUBLIC)
            .addSuperinterface(ANVIL.nestedClass("AttributeSetter").parameterizedBy(ANY_N))
            .addFunction(setterFunction)
            .build()

        val attr = MemberName(PACKAGE, "attr")
        val bind = MemberName(PACKAGE, "bind")
        val v = MemberName(PACKAGE, "v")

        model.views.forEach { view ->
            val viewName = view.name
            val scopeType = ClassName(packageName, "${viewName}Scope")

            val viewType = view.starProjectedType

            val methods = view.attrs
                .map {
                    FunSpec.builder(it.name)
                        .addParameter("arg", it.type.argType.copy(nullable = it.isNullable))
                        .returns(UNIT)
                        .addCode(CodeBlock.of("return %M(%S, arg)", attr, it.name))
                        .build()
                }

            fileSpec.addFunction(FunSpec.builder(viewName[0].toLowerCase() + viewName.substring(1))
                .addParameter(
                    ParameterSpec.builder(
                        "configure",
                        LambdaTypeName.get(receiver = scopeType, returnType = UNIT))
                        .defaultValue("{}")
                        .build())
                .addCode(CodeBlock.of("return %M<%T>(configure.%M(%T))", v, viewType, bind, scopeType))
                .build())
            fileSpec.addType(TypeSpec.classBuilder(scopeType)
                .addModifiers(KModifier.PUBLIC, KModifier.ABSTRACT)
                .also {
                    val superType = view.superType
                    if(superType != null) {
                        it.superclass(ClassName(packageName, "${superType.name}Scope"))
                    }
                }
                .addType(
                    TypeSpec.companionObjectBuilder()
                        .superclass(scopeType)
                        .addInitializerBlock(buildCodeBlock {
                            add("%T.registerAttributeSetter(%N)\n", ANVIL, setterObject)
                            if(manualSetter != null) {
                                add("Anvil.registerAttributeSetter(%M)\n", manualSetter)
                            }
                        })
                        .build()
                )
                .addFunctions(methods)
                .build())
        }

        fileSpec
            .addType(setterObject)
            .addAnnotation(AnnotationSpec.builder(Suppress::class)
                .addMember("\"DEPRECATION\", \"UNCHECKED_CAST\", \"MemberVisibilityCanBePrivate\", \"unused\"")
                .useSiteTarget(AnnotationSpec.UseSiteTarget.FILE)
                .build())
            .build()
            .writeTo(File(project.projectDir, "src/$outputDirectory/kotlin").also { it.mkdirs() })
    }

    private fun Method.getParameterType(): Class<*>? {
        if (parameterTypes.isEmpty()) {
            return null
        }

        val parameterType = parameterTypes[0]
        if(!parameterType.isPublic) return null

        if (isDeprecated) {
            return null
        } else if (declaringClass.canonicalName == VIEW_CNAME) {
            return parameterType
        }

        // Check if the setter overrode from a super class.
        var supClass = declaringClass.superclass
        while (true) {
            if (supClass == null) {
                break
            }
            try {
                supClass.getMethod(name, *parameterTypes)
                return null
            } catch (ignored: NoSuchMethodException) {
                // Intended to occur
            }

            if (supClass.canonicalName == VIEW_CNAME) {
                break
            }
            supClass = supClass.superclass
        }
        return parameterType
    }

    private fun AttrModel.buildListener(): CodeBlock {
        val viewClass = owner.plainType.toString()

        val body = buildCodeBlock {
            if(type.isSamLike) {
                add("arg as %T\n", type.argType.copy(nullable = isNullable))
            }
            if (type.isSamLike && type.isInterface) {
                val function = type.functions.first()
                val args = function.argsString

                beginControlFlow("v.$setterName { $args ->")
                add(function.buildListenerCode(putReturn = false, functionalType = true))
                endControlFlow()
            } else {
                val listener = TypeSpec.anonymousClassBuilder().apply {
                    if (type.isInterface) {
                        addSuperinterface(type.plainType)
                    } else {
                        superclass(type.plainType)
                    }
                    type.functions
                        .map { it.buildListenerFunction(functionalType = type.isSamLike) }
                        .forEach { addFunction(it) }
                }.build()
                addStatement("v.$setterName(%L)", listener)
            }
        }

        return buildCodeBlock {
            val checkedType = if (type.isSamLike) {
                FUNCTION_STAR
            } else {
                type.plainType
            }

            if (viewClass == VIEW_CNAME) {
                // @formatter:off
                beginControlFlow("arg == null ->")
                    addStatement("v.$setterName(null as? %T?)", type.plainType)
                    addStatement("true")
                endControlFlow()
                beginControlFlow("arg is %T ->", checkedType)
                    add(body)
                    addStatement("true")
                endControlFlow()
                // @formatter:on
            } else {
                val v = owner.parametrizedType?.let { "(v as $it)" } ?: "v"
                // @formatter:off
                beginControlFlow("v is %T -> when", owner.starProjectedType)
                    beginControlFlow("arg == null ->")
                        addStatement("$v.$setterName(null as? %T)", type.plainType.copy(nullable = true))
                        addStatement("true")
                    endControlFlow()
                    beginControlFlow("arg is %T ->", checkedType)
                        add(body)
                        addStatement("true")
                    endControlFlow()
                    addStatement("else -> false")
                endControlFlow()
                // @formatter:on
            }
        }
    }

    private fun FunctionModel.buildListenerFunction(functionalType: Boolean): FunSpec {
        return FunSpec.builder(name)
            .addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
            .returns(returnType)
            .addParameters(parameters.map(ParameterModel::toParameterSpec))
            .addCode(buildListenerCode(putReturn = true, functionalType = functionalType))
            .build()
    }

    private fun FunctionModel.buildListenerCode(putReturn: Boolean, functionalType: Boolean): CodeBlock = buildCodeBlock {
        if(putReturn) {
            add("return ")
        }
        // TODO this can blow up if listener method has method named "arg"
        add("arg")
        if(!functionalType) {
            add(".%L", name)
        }
        add("($argsString).also·{ %T.render() }\n", ANVIL)
    }

    private fun AttrModel.buildSetter(): CodeBlock {
        val viewClass = owner.plainType.toString()

        val argAsParam = when {
            isVarArg -> "*arg"
            isNullable || isArray -> "arg as %T"
            else -> "arg"
        }

        // TODO check if getter is present and if so, use property assignment, else use setter call
        return buildCodeBlock {
            if (viewClass == VIEW_CNAME) {
                beginControlFlow("arg is %T ->", type.starProjectedType.copy(nullable = isNullable))
                addStatement("v.$setterName($argAsParam)", type.parametrizedType)
                addStatement("true")
                endControlFlow()
            } else {
                val v = owner.parametrizedType?.let { "(v as $it)" } ?: "v"

                beginControlFlow(
                   "v is %T && arg is %T ->",
                    owner.starProjectedType,
                    type.starProjectedType.copy(nullable = isNullable)
                )
                addStatement("$v.$setterName($argAsParam)", type.parametrizedType)
                addStatement("true")
                endControlFlow()
            }
        }
    }
}

fun parseAttrName(input : String, parameterCount: Int): Pair<String, Boolean>? = when {
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

private val Project.inkremental: InkrementalMetaExtension
    get() = extensions.getByType()

private fun File.jarUrl(): URL = URL("jar", "", "file:$absolutePath!/")

private fun <T> Enumeration<T>.toSequence(): Sequence<T> = sequence {
    while (hasMoreElements()) {
        yield(nextElement())
    }
}



private val Class<*>.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

private val Method.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

private fun KFunction<*>.toFunctionModel(): FunctionModel = FunctionModel(
    name = name,
    parameters = valueParameters.mapIndexed { index, parameter ->
        ParameterModel(parameter.name ?: "a$index", parameter.type.asTypeName())
    },
    returnType = returnType.asTypeName()
)

private fun ParameterModel.toParameterSpec(): ParameterSpec = ParameterSpec.builder(name, type).build()

private fun FunctionModel.asLambdaTypeName(): LambdaTypeName =
    LambdaTypeName.get(
        null,
        parameters.map(ParameterModel::toParameterSpec),
        returnType
    )

private val Method.isDeprecated: Boolean
    get() {
        val annotations = declaredAnnotations as Array<java.lang.annotation.Annotation>
        return java.lang.Deprecated::class.java in annotations.map { it.annotationType() }
    }

private fun Class<*>.asParameterizedType(): ParameterizedTypeName? = when {
    isArray && !componentType.isPrimitive -> kotlin.parameterizedBy(componentType.kotlin)
    typeParameters.isEmpty() -> null
    else -> kotlin.asClassName().parameterizedBy(kotlin.typeParameters)
}

private fun ClassName.parameterizedBy(typeParameters: List<KTypeParameter>): ParameterizedTypeName =
    parameterizedBy(*typeParameters.map { it.upperBounds[0].asTypeName() }.toTypedArray())

private fun ClassName.parameterizedBy(typeParameters: Array<out TypeVariable<out Class<*>>>): ParameterizedTypeName =
    parameterizedBy(*typeParameters.map { it.bounds[0].asTypeName() }.toTypedArray())

private const val PACKAGE = "trikita.anvil"
private const val VIEW_CNAME = "android.view.View"

private val ANY_N: ClassName = ANY.copy(nullable = true)
private val FUNCTION_STAR: TypeName = ClassName("kotlin", "Function").parameterizedBy(STAR)
private val ANVIL: ClassName = ClassName(PACKAGE, "Anvil")
private val VIEW: ClassName = ClassName("android.view", "View")

internal val <T> T.className: String get() = if(this == null) "[null]" else this!!::class.java.simpleName
