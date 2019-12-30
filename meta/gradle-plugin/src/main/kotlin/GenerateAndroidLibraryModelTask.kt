package dev.inkremental.meta.gradle

import dev.inkremental.meta.android.*
import dev.inkremental.meta.model.Introspector
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.*
import java.io.File
import java.net.URLClassLoader

abstract class GenerateAndroidLibraryModelTask : GenerateModelTask() {

    @get:Input abstract var configuration: Configuration
    @get:Classpath @get:Optional abstract var sdkDependencies: Iterable<File>?

    override fun createIntrospector(): Introspector {
        val resolved = configuration.resolvedConfiguration
        val libs = resolved.firstLevelModuleDependencies
            .flatMap { it.moduleArtifacts }
            .map { it.file }
            .distinct()
        val deps = resolved.resolvedArtifacts
            .map { it.file }
            .distinct()

        project.logger.debug("Generator task resolved $configuration\nLibs:\n" +
                libs.map { it.absolutePath }.sorted().joinToString(separator = "\n") { "    $it" } +
                "\nDeps ${deps.size}:\n" +
                deps.map { it.absolutePath }.sorted().joinToString(separator = "\n") { "    $it" })

        return AndroidIntrospector(
            jarFiles = libs,
            dependencies = deps + (sdkDependencies ?: listOf()),
            nullabilityHolder = NullabilityHolder(
                isSourceSdk = false,
                classLoader = URLClassLoader(
                    libs.map { it.jarUrl() }.toTypedArray(),
                    javaClass.classLoader
                )
            )
        )
    }
}
