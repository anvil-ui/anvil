package dev.inkremental.meta.model

import java.io.File

operator fun File.div(relative: String): File = resolve(relative)

fun String.dashesToCamelCase(): String {
    var first = true
    return splitToSequence('-').joinToString(separator = "") {
        if(first) it.also { first = false } else it.capitalize()
    }
}

fun buildCamelCaseString(vararg components: String?): String =
    components.filterNotNull().fold("") { acc, it ->
        if(acc.isEmpty()) {
            it.decapitalize()
        } else {
            acc + it.capitalize()
        }
    }

@Deprecated(
    "Kotlin 1.3.70 would have it out of the box",
    ReplaceWith("scan(initial, operation)", "kotlin.collections.scan")
)
public inline fun <T, R> Iterable<T>.scan(initial: R, operation: (acc: R, T) -> R): List<R> {
    val estimatedSize = if (this is Collection<*>) this.size else 9
    if (estimatedSize == 0) return listOf(initial)
    val result = ArrayList<R>(estimatedSize + 1).apply { add(initial) }
    var accumulator = initial
    for (element in this) {
        accumulator = operation(accumulator, element)
        result.add(accumulator)
    }
    return result
}
