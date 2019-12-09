package trikita.anvilgen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile
import kotlin.Boolean
import kotlin.Char
import kotlin.Int
import kotlin.String
import kotlin.also
import kotlin.reflect.*
import kotlin.reflect.full.*

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

    private fun createModel(): DslModel {
        val quirks = project.anvilgen.quirks

        return DslModel(
            name = camelCaseName,
            views = viewsSequence().mapNotNull {
                val quirk = quirks[it.canonicalName]

                val name = when(val alias = quirk?.get("__viewAlias")) {
                    null -> it.simpleName
                    false -> return@mapNotNull null
                    else -> alias as String
                }

                val attrs = it.attrsSequence().filter {
                    quirk?.get("${it.setter.name}:${it.type.qualifiedName}") != false && quirk?.get(it.setter.name) != false
                }.toList()

                ViewModel(
                    type = it.kotlin,
                    name = name,
                    attrs = attrs
                )
            }.toList()
        )
    }

    private fun dumpModel(model: DslModel) {
        model.views.forEach { view ->
            project.logger.warn("[gen] View: ${view.type.qualifiedName}")
            view.attrs.forEach { (name, setter, arg, isListener, isNullable) ->
                project.logger.warn("[gen]     Attr: $name | ${setter.name} | ${arg.qualifiedName} | ${if(isListener) "is" else "not"} listener | ${if(isNullable) "nullable" else "nonnull"}")
            }
            project.logger.warn("[gen]")
        }
    }

    private fun renderModel(model: DslModel) {
        val fileSpec = FileSpec.builder(packageName, camelCaseName)

        val setterBody = CodeBlock.builder().beginControlFlow("return when (name)")
        model.views
            .flatMap { it.attrs }
            .groupBy { it.name }
            .mapValues { (_, it) -> it.sortedBy { it.type.simpleName } }
            .forEach { (name, attrs) ->
                val filtered = attrs.filter { a ->
                    !attrs.any { b ->
                        a != b && a.type == b.type &&
                                a.setter.declaringClass.isAssignableFrom(b.setter.declaringClass)
                    }
                }

                setterBody.beginControlFlow("%S -> when", name)
                filtered.forEach {
                    val code = it.buildCode()
                    if(code != null) {
                        setterBody.add(code)
                    }
                }

                setterBody.add("else -> false\n")
                setterBody.endControlFlow()
            }
        setterBody
            .add("else -> false\n")
            .endControlFlow()

        val initializer = CodeBlock.builder()
            .add("Anvil.registerAttributeSetter(this)\n")
        if(manualSetter != null) {
            initializer.add("Anvil.registerAttributeSetter(%M)\n", manualSetter)
        }

        val setterObject = TypeSpec.objectBuilder(ClassName(PACKAGE, "${camelCaseName}Setter"))
            .addKdoc("""DSL for creating views and settings their attributes.
                |This file has been generated by
                |{@code gradle $name}
                |$javadocContains.
                |Please, don't edit it manually unless for debugging.""".trimMargin())
            .addModifiers(KModifier.PUBLIC)
            .addSuperinterface(ANVIL.nestedClass("AttributeSetter").parameterizedBy(ANY_N))
            .addInitializerBlock(initializer.build())
            .addFunction(FunSpec.builder("set")
                .addParameter("v", VIEW)
                .addParameter("name", STRING)
                .addParameter("arg", ANY_N)
                .addParameter("old", ANY_N)
                .returns(BOOLEAN)
                .addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
                .addCode(setterBody.build())
                .build())
            .build()

        val attr = MemberName(PACKAGE, "attr")
        val bind = MemberName(PACKAGE, "bind")
        val v = MemberName(PACKAGE, "v")

        model.views.forEach { view ->
            val viewName = view.name
            val scopeType = ClassName(packageName, "${viewName}Scope")

            val viewType = view.type.starProjectedType.asTypeName()

            val methods = view.attrs
                .map {
                    val type = if(!it.isListener) {
                        it.type.toParametrizedType()
                    } else {
                        val methods = it.type.nonDefultMethods
                        if(methods.size == 1) {
                            methods.first().asTypeName()
                        } else {
                            it.type.toParametrizedType()
                        }
                    }.copy(nullable = it.isNullable)
                    FunSpec.builder(it.name)
                        .addParameter("arg", type)
                        .returns(UNIT)
                        .addCode(CodeBlock.of("return %M(%S, arg)", attr, it.name))
                        .build()
                }

            fileSpec.addFunction(FunSpec.builder(viewName.toCase(Char::toLowerCase))
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
                    // View DSL doesn't need supertype
                    if(view.type.qualifiedName != VIEW_CNAME) {
                        // Find first supertype we didn't skip due to quirks or heuristics
                        // TODO this won't work for library classes depending on other libraries or SDK ones
                        // TODO this can be fixed by using generated metadata
                        /*var superType: KClass<*> = view.type
                        project.logger.warn("[sup] ${superType.qualifiedName}")
                        do {
                            superType = superType.superclasses.first { !it.isInterface }
                            project.logger.warn("[sup] \t${superType.qualifiedName}")
                        } while (model.views.none { it.type == superType } && superType.qualifiedName != VIEW_CNAME)
                        project.logger.warn("[sup] : ${superType.qualifiedName}")
                        val superView = superType.simpleName
                        it.superclass(ClassName(packageName, "${superView}Scope"))*/
                        it.superclass(ClassName(packageName, "${view.type.superclasses[0].simpleName}Scope"))
                    } else {
                        it.superclass(ClassName(packageName, ROOT_VIEW_SCOPE))
                    }
                }
                .addType(
                    TypeSpec.companionObjectBuilder()
                        .superclass(scopeType)
                        .addInitializerBlock(CodeBlock.of(
                            "%T.registerAttributeSetter(%N)", ANVIL, setterObject))
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
            .sortedBy { it.name }
            .filter { it.isPublic && !it.isSynthetic && !it.isBridge }
            .mapNotNull { method ->
                val parameterType = getMethodParameterType(method) ?: return@mapNotNull null
                val formattedMethodName = formatMethodName(method.name, method.parameterCount) ?: return@mapNotNull null
                val isNullable = nullabilityHolder.isParameterNullable(method) ?: formattedMethodName.isListener

                AttrModel(formattedMethodName.formattedName, method, parameterType, formattedMethodName.isListener, isNullable)
            }

    private fun getMethodParameterType(m: Method): KClass<*>? {
        if (m.parameterTypes.isEmpty()) {
            return null
        }

        val parameterType = m.parameterTypes[0]
        if(!parameterType.isPublic) return null

        if (m.isDeprecated) {
            return null
        } else if (m.declaringClass.canonicalName == VIEW_CNAME) {
            return parameterType.kotlin
        }

        // Check if the setter overrode from a super class.
        var supClass = m.declaringClass.superclass
        while (true) {
            if (supClass == null) {
                break
            }
            try {
                supClass.getMethod(m.name, *m.parameterTypes)
                return null
            } catch (ignored: NoSuchMethodException) {
                // Intended to occur
            }

            if (supClass.canonicalName == VIEW_CNAME) {
                break
            }
            supClass = supClass.superclass
        }
        return parameterType.kotlin
    }

    private fun AttrModel.buildCode(): CodeBlock? = if(isListener) {
        buildListener()
    } else {
        buildSetter()
    }

    private fun AttrModel.buildListener(): CodeBlock {
        val viewClass = setter.declaringClass.canonicalName
        val functions = type.nonDefultMethods.sortedBy { it.name }

        val checkedType = if (functions.size == 1) {
            FUNCTION_STAR
        } else {
            type.asTypeName()
        }

        val samLike = functions.size == 1

        val body = buildCodeBlock {
            if(samLike) {
                add("arg as %T\n", functions.first().asTypeName())
            }
            if (samLike && type.isInterface) {
                val function = functions.first()
                val args = function.parameterSpecs.joinToString { it.name }

                beginControlFlow("v.${setter.name} { $args ->")
                add(function.buildListenerCode(putReturn = false, functionalType = true))
                endControlFlow()
            } else {
                val listener = TypeSpec.anonymousClassBuilder().apply {
                    if (type.isInterface) {
                        addSuperinterface(type)
                    } else {
                        superclass(type)
                    }
                    functions
                        .map { it.buildListenerFunction(functionalType = samLike) }
                        .forEach { addFunction(it) }
                }.build()
                addStatement("v.${setter.name}(%L)", listener)
            }
        }

        return buildCodeBlock {
            if (viewClass == VIEW_CNAME) {
                // @formatter:off
                beginControlFlow("arg == null ->")
                    addStatement("v.${setter.name}(null as? %T?)", type)
                    addStatement("true")
                endControlFlow()
                beginControlFlow("arg is %T ->", checkedType)
                    add(body)
                    addStatement("true")
                endControlFlow()
                // @formatter:on
            } else {
                val viewType = setter.declaringClass.kotlin
                // TODO toParametrizedType
                val v = if (viewType.typeParameters.isEmpty()) {
                    "v"
                } else {
                    "(v as ${viewType.asClassName().parameterizedBy(viewType.typeParameters)})"
                }
                // @formatter:off
                beginControlFlow("v is %T -> when", viewType.starProjectedType.asTypeName())
                    beginControlFlow("arg == null ->")
                        addStatement("$v.${setter.name}(null as? %T?)", type)
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

    private fun KFunction<*>.buildListenerFunction(functionalType: Boolean): FunSpec {
        return FunSpec.builder(name)
            .addModifiers(KModifier.PUBLIC, KModifier.OVERRIDE)
            .returns(returnType.asTypeName())
            .addParameters(parameterSpecs)
            .addCode(buildListenerCode(putReturn = true, functionalType = functionalType))
            .build()
    }

    private fun KFunction<*>.buildListenerCode(putReturn: Boolean, functionalType: Boolean): CodeBlock = buildCodeBlock {
        val args = parameterSpecs.joinToString { it.name }
        if(putReturn) {
            add("return ")
        }
        // TODO this can blow up if listener method has method named "arg"
        add("arg")
        if(!functionalType) {
            add(".%L", name)
        }
        add("($args).also·{ %T.render() }\n", ANVIL)
    }

    private fun AttrModel.buildSetter(): CodeBlock? {
        val viewClass = setter.declaringClass.canonicalName
        val builder = CodeBlock.builder()

        val checkArgLiteral = if (isNullable) {
            "(arg == null || arg is %T)"
        } else {
            "arg is %T"
        }
        val argAsParam = when {
            setter.isVarArgs -> "*arg"
            isNullable || type.isArray -> "arg as %T"
            else -> "arg"
        }

        val t = type.toParametrizedType()

        // TODO check if getter is present and if so, use property assignment, else use setter call
        return if (viewClass == VIEW_CNAME) {
            builder
                .beginControlFlow("$checkArgLiteral ->", type.starProjectedType.asTypeName())
                .addStatement("v.${setter.name}($argAsParam)", t)
                .addStatement("true")
                .endControlFlow()
        } else {

            val viewType = setter.declaringClass.kotlin
            // TODO toParametrizedType
            val v = if(viewType.typeParameters.isEmpty()) {
                "v"
            } else {
                "(v as ${viewType.asClassName().parameterizedBy(viewType.typeParameters)})"
            }

            builder
                .beginControlFlow("v is %T && $checkArgLiteral ->", setter.declaringClass.kotlin.starProjectedType.asTypeName(), type.starProjectedType.asTypeName())
                .addStatement("$v.${setter.name}($argAsParam)", t)
                .addStatement("true")
                .endControlFlow()
        }.build()
    }
}

data class DslModel(
    val name: String,
    val views: List<ViewModel>
)

data class ViewModel(
    val type: KClass<*>,
    val name: String,
    val attrs: List<AttrModel>
)

data class AttrModel(
    val name: String,
    val setter: Method,
    val type: KClass<*>,
    val isListener: Boolean,
    val isNullable: Boolean
)

data class Attr(
    val name: String,
    val param: Class<*>,
    val setter: Method,
    var unreachableBreak: Boolean = false,
    val code: CodeBlock.Builder = CodeBlock.builder())
data class FormattedMethod(val formattedName : String, val isListener : Boolean)

fun formatMethodName(originalMethodName : String, parameterCount : Int) : FormattedMethod? {
    return if (originalMethodName.matches(Regex("^setOn.*Listener$"))) {
        FormattedMethod(
            "on" + originalMethodName.substring(5, originalMethodName.length - 8),
            true
        )
    } else if (originalMethodName.startsWith("set")
        && originalMethodName.length > 3
        && originalMethodName[3].isUpperCase()
        && parameterCount == 1) {
            FormattedMethod(
                Character.toLowerCase(originalMethodName[3]).toString() + originalMethodName.substring(
                    4
                ), false
            )
    } else null
}

fun String.toCase(fn: (Char) -> Char): String =
    fn(get(0)).toString() + substring(1)

private fun File.jarUrl(): URL = URL("jar", "", "file:$absolutePath!/")

private fun <T> Enumeration<T>.toSequence(): Sequence<T> = sequence {
    while (hasMoreElements()) {
        yield(nextElement())
    }
}

private val Project.anvilgen: AnvilGenPluginExtension
    get() = this.extensions.getByType()

private val Class<*>.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

private val Method.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

private val KClass<*>.isInterface: Boolean
    get() = java.isInterface

private val KClass<*>.isArray: Boolean
    get() = java.isArray

private val defaultMethods = setOf("equals", "hashCode", "toString")

private val KClass<*>.nonDefultMethods: List<KFunction<*>>
    get() = memberFunctions.filter { it.name !in defaultMethods }

private val KFunction<*>.parameterSpecs: List<ParameterSpec>
        get() = valueParameters.mapIndexed { index, parameter ->
            ParameterSpec.builder(parameter.name ?: "a$index", parameter.type.asTypeName()).build()
        }

private fun KFunction<*>.asTypeName(): LambdaTypeName =
    LambdaTypeName.get(null, parameterSpecs, returnType.asTypeName())

private val Method.isDeprecated: Boolean
    get() {
        val annotations = declaredAnnotations as Array<java.lang.annotation.Annotation>
        return java.lang.Deprecated::class.java in annotations.map { it.annotationType() }
    }

private fun KClass<*>.toParametrizedType(): TypeName = when {
    typeParameters.isEmpty() -> asTypeName()
    java.isArray -> parameterizedBy(java.componentType.kotlin)
    else -> asClassName().parameterizedBy(typeParameters)
}

private fun ClassName.parameterizedBy(typeParameters: List<KTypeParameter>) =
    parameterizedBy(*typeParameters.map { it.upperBounds[0].asTypeName() }.toTypedArray())

private const val PACKAGE = "trikita.anvil"
private const val VIEW_CNAME = "android.view.View"
private const val ROOT_VIEW_SCOPE = "RootViewScope"

private val ANY_N: ClassName = ANY.copy(nullable = true)
private val FUNCTION_STAR: TypeName = ClassName("kotlin", "Function").parameterizedBy(STAR)
private val ANVIL: ClassName = ClassName(PACKAGE, "Anvil")
private val VIEW: ClassName = ClassName("android.view", "View")

