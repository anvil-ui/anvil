package dev.inkremental.meta.gradle

import dev.inkremental.meta.android.*
import dev.inkremental.meta.model.Introspector
import org.gradle.api.tasks.Classpath
import java.io.File
import java.net.URLClassLoader

abstract class GenerateAndroidSdkModelTask : GenerateModelTask() {

    @get:Classpath abstract var jarFiles: Iterable<File>
    @get:Classpath abstract var nullabilitySourceFiles: Iterable<File>

    override fun createIntrospector(): Introspector = AndroidIntrospector(
        jarFiles = jarFiles,
        dependencies = listOf(),
        nullabilityHolder = NullabilityHolder(
            isSourceSdk = true,
            classLoader = URLClassLoader(
                nullabilitySourceFiles.map { it.jarUrl() }.toTypedArray(),
                javaClass.classLoader
            )
        )
    )
}
