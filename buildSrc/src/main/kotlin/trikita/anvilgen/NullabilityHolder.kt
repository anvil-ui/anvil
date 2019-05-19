package trikita.anvilgen

import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.tree.AnnotationNode
import jdk.internal.org.objectweb.asm.tree.ClassNode
import java.io.IOException
import java.lang.reflect.Method
import java.net.URLClassLoader

class NullabilityHolder(isSourceSdk: Boolean) {

    val nullabilityMap: MutableMap<String, Boolean?> = HashMap()
    var nullableLiteral : String
    var nonNullableLiteral : String

    init {
        if (isSourceSdk){
            nullableLiteral = "Landroidx/annotation/RecentlyNullable;"
            nonNullableLiteral = "Landroidx/annotation/RecentlyNonNull;"
        } else {
            nullableLiteral = "Landroid/support/annotation/Nullable;"
            nonNullableLiteral = "Landroid/support/annotation/NonNull;"
        }
    }

    fun fillClassNullabilityInfo(loader: URLClassLoader, rawClassName: String) {
        val cn = ClassNode()
        val cr = try {
            ClassReader(loader.getResourceAsStream(rawClassName))
        } catch (io : IOException) {
            return
        }

        cr.accept(cn, 0)

        val className = rawClassName.replace(".class", "").replace("/", ".")

        cn.methods.forEach { methodNode ->
            if (methodNode.name != "<init>"
                && methodNode.invisibleParameterAnnotations?.isNotEmpty() == true
                && methodNode.localVariables?.size == 2
            ) {

                val hasNullable = hasNullableOrNonNullAnnotation(methodNode.invisibleParameterAnnotations[0])
                val argType = convertTypeNameFromRaw(methodNode.localVariables[1].desc)
                println("++++++++++++++++++++++++++++++")
                println("class: $className method: ${methodNode.name} param name ${methodNode.localVariables[1].name} type $argType : hasNullableOrNonNullAnnotation : $hasNullable")
                val formattedMethodName = formatMethodName(methodNode.name, 1)
                formattedMethodName?.let {
                    nullabilityMap["$className\$${it.formattedName}\$$argType"] = hasNullable
                }
            }
        }
    }

    private fun convertTypeNameFromRaw(typeName: String): String {
        return typeName.replace("/", ".").let {
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
                when (anNode.desc){
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
            val key = "${m.declaringClass.canonicalName}\$${it.formattedName}\$${m.parameters[0].type.canonicalName}"
            nullabilityMap[key]
        }
    }

    fun isParameterNullable(className: String, methodName: String, argType : String ): Boolean? {
        val key = "$className\$$methodName\$$argType"
        return nullabilityMap[key]
    }


}