package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import dev.inkremental.meta.model.FunctionModel
import dev.inkremental.meta.model.ParameterModel
import java.io.File
import java.lang.reflect.*
import java.net.URL
import java.util.*
import kotlin.reflect.KFunction
import kotlin.reflect.KTypeParameter
import kotlin.reflect.full.valueParameters

operator fun File.div(relative: String): File = resolve(relative)

internal fun File.jarUrl(): URL = URL("jar", "", "file:$absolutePath!/")

internal fun <T> Enumeration<T>.toSequence(): Sequence<T> = sequence {
    while (hasMoreElements()) {
        yield(nextElement())
    }
}

internal val <T> T.className: String get() = if(this == null) "[null]" else this!!::class.java.simpleName
