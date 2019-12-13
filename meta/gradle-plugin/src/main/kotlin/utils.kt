package dev.inkremental.meta.gradle

import java.io.File

operator fun File.div(relative: String): File = resolve(relative)
