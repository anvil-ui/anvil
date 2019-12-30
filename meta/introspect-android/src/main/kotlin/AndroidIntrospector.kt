package dev.inkremental.meta.android

import com.squareup.kotlinpoet.asClassName
import dev.inkremental.meta.model.*
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.kotlinFunction

class AndroidIntrospector(
    val jarFiles: Iterable<File>,
    val dependencies: Iterable<File>,
    val nullabilityHolder: NullabilityHolder
): Introspector {
    override fun provideViewModels(quirks: InkrementalQuirks): List<ViewModel> =
        viewsSequence().mapNotNull { it.createViewModel(quirks) }.toList()

    private fun Class<*>.createViewModel(quirks: InkrementalQuirks): ViewModel? {
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

        val superType = if(plainType == VIEW)
            null
        else
            ViewModelSupertype.Unresolved(kotlin.superclasses.mapNotNull { it.qualifiedName })

        return ViewModel(
            name = name,
            plainType = plainType,
            parametrizedType = asParameterizedType(),
            attrs = attrs,
            superType = superType,
            isRootType = plainType == VIEW
        )
    }

    private fun viewsSequence(): Sequence<Class<*>> {
        val urls = jarFiles.map { it.jarUrl() } + dependencies.map { it.jarUrl() }
        val loader = URLClassLoader(urls.toTypedArray(), javaClass.classLoader)
        val viewClass = loader.loadClass(VIEW.canonicalName)

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
                                //println("Unable to process method: ${it.declaringClass.canonicalName}.${it.name}")
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

}
