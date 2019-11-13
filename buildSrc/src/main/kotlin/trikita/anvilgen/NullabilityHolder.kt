package trikita.anvilgen

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.AnnotationNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LocalVariableNode
import org.objectweb.asm.tree.MethodNode
import java.io.IOException
import java.lang.reflect.Method
import java.net.URLClassLoader

class NullabilityHolder(isSourceSdk: Boolean) {

    private val nullabilityMap: MutableMap<MethodSignature, Boolean?> = mutableMapOf()
    private val nullableLiteral: String
    private val nonNullableLiteral: String

    init {
        if (isSourceSdk) {
            nullableLiteral = "Landroidx/annotation/RecentlyNullable;"
            nonNullableLiteral = "Landroidx/annotation/RecentlyNonNull;"
        } else {
            nullableLiteral = "Landroidx/annotation/Nullable;"
            nonNullableLiteral = "Landroidx/annotation/NonNull;"
        }
    }

    fun fillClassNullabilityInfo(loader: URLClassLoader, rawClassName: String) {
        val cn = ClassNode()
        val cr = try {
            ClassReader(loader.getResourceAsStream(rawClassName))
        } catch (io: IOException) {
            return
        }

        cr.accept(cn, 0)

        val className = rawClassName.replace(".class", "").replace("/", ".")

        (cn.methods as List<MethodNode>).forEach { methodNode : MethodNode ->
            if (methodNode.name != "<init>"
                && methodNode.invisibleParameterAnnotations?.isNotEmpty() == true
                && methodNode.localVariables?.size == 2
            ) {

                val hasNullable =
                    hasNullableOrNonNullAnnotation(methodNode.invisibleParameterAnnotations[0])
                val argType = convertTypeNameFromRaw((methodNode.localVariables[1] as LocalVariableNode).desc)
                val formattedMethodName = formatMethodName(methodNode.name, 1)
                formattedMethodName?.let {
                    val signature = MethodSignature(className, it.formattedName, argType)
                    nullabilityMap[signature] = hasNullable
                }
            }
        }
    }

    private fun convertTypeNameFromRaw(typeName: String): String {
        return typeName
            .replace("/", ".")
            .replace("$", ".")
            .let {
                if (it.startsWith("L")) {
                    it.replaceFirst("L", "")
                } else it
            }.let {
                if (it.endsWith(";")) {
                    it.replaceFirst(";", "")
                } else it
            }
    }

    fun hasNullableOrNonNullAnnotation(annotationList: List<*>?): Boolean? {
        if (annotationList != null && annotationList.isNotEmpty()) {
            var anNode: AnnotationNode?
            for (annotation in annotationList) {
                anNode = annotation as AnnotationNode
                when (anNode.desc) {
                    nullableLiteral -> return true
                    nonNullableLiteral -> return false
                }
            }
        }
        return null
    }

    fun isParameterNullable(m: Method): Boolean? {
        val formattedMethodName = formatMethodName(m.name, 1)
        return formattedMethodName?.let {
            val signature = MethodSignature(
                m.declaringClass.canonicalName,
                it.formattedName,
                m.parameters[0].type.canonicalName
            )

            nullabilityMap[signature]
        }
    }

    fun isParameterNullable(className: String, methodName: String, argType: String): Boolean? {
        return nullabilityMap[MethodSignature(className, methodName, argType)]
    }

}

data class MethodSignature(val className: String, val methodName: String, val firstArgType: String)
