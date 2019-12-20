package dev.inkremental.meta.gradle

import java.lang.reflect.Method
import java.lang.reflect.Modifier

internal val Class<*>.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

internal val Method.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)

internal val Method.isDeprecated: Boolean
    get() {
        val annotations = declaredAnnotations as Array<java.lang.annotation.Annotation>
        return java.lang.Deprecated::class.java in annotations.map { it.annotationType() }
    }

internal fun Method.getParameterType(): Class<*>? {
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
