package dev.inkremental.meta.android

import java.io.File
import java.net.URL
import java.util.*

fun File.jarUrl(): URL = URL("jar", "", "file:$absolutePath!/")

internal fun <T> Enumeration<T>.toSequence(): Sequence<T> = sequence {
    while (hasMoreElements()) {
        yield(nextElement())
    }
}
