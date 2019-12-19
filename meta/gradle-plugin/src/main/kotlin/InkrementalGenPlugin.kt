package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.MemberName
import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.transform.*
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.file.FileType
import org.gradle.api.internal.artifacts.ArtifactAttributes.ARTIFACT_FORMAT
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.*
import org.gradle.work.ChangeType
import org.gradle.work.InputChanges
import java.io.File
import java.util.*
import java.util.zip.ZipFile
import javax.inject.Inject

class InkrementalGenPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val extension = extensions.create<InkrementalMetaExtension>("inkremental")

        val configuration = configurations.create("inkremental") {
            description = "Artifacts that should be processed by Anvil code generator"
            isTransitive = true
            isCanBeResolved = true
            isCanBeConsumed = false
            attributes {
                attribute(ARTIFACT_FORMAT, ArtifactTypeDefinition.JAR_TYPE)
            }
        }

        dependencies.registerTransform(AarToJarTransform::class.java) {
            from.attribute(ARTIFACT_FORMAT, "aar")
            to.attribute(ARTIFACT_FORMAT, ArtifactTypeDefinition.JAR_TYPE)
        }

        // "implementation" extends from "api", so it's also covered
        configurations["api"].extendsFrom(configuration)

        afterEvaluate {
            extension.modules.forEach {
                generateTasks(it, configuration)
            }
        }
    }

    fun Project.generateTasks(module: InkrementalMetaModule, configuration: Configuration) {
        when(module.type) {
            InkrementalType.SDK -> {
                createDslGeneratorTask("SDK21", getSdkConfiguration(21, module.manualSetterName, module.quirks))
                createDslGeneratorTask("SDK19", getSdkConfiguration(19, module.manualSetterName, module.quirks))
                createDslGeneratorTask("SDK15", getSdkConfiguration(15, module.manualSetterName, module.quirks))

                project.tasks.register<Task>("generateSDKDSL") {
                    dependsOn("generateSDK15DSL", "generateSDK19DSL", "generateSDK21DSL")
                }
            }
            InkrementalType.LIBRARY -> {
                createDslGeneratorTask(module.camelCaseName,
                    // temporary toggle for supporting both array- and configuration-based libraries declaration
                    if(module.libraries.isNotEmpty()) {
                        error("You should use configuration to setup generator")
                        /*getSupportConfiguration(
                            module.camelCaseName, module.name,
                            module.manualSetterName, module.libraries, module.dependencies,
                            module.quirks
                        )*/
                    } else {
                        getSupportConfiguration(
                            module.camelCaseName, module.name,
                            module.manualSetterName, configuration,
                            module.quirks
                        )
                    }
                )
            }
        }
    }

    private fun Project.createDslGeneratorTask(dslName: String, configuration: DSLGeneratorTask.() -> Unit) =
        tasks.register("generate${dslName}DSL", configuration)

    private fun getSdkConfiguration(apiLevel: Int, manualSetterName: String, quirks: InkrementalQuirks): DSLGeneratorTask.() -> Unit = {
        javadocContains = "It contains views and their setters from API level $apiLevel"
        outputDirectory = "sdk$apiLevel"
        this.quirks = quirks
        jarFiles = listOf(project.getAndroidJar(apiLevel))
        nullabilitySourceFiles = listOf(project.getAndroidJar(28))
        isSourceSdk = true
        camelCaseName = "Sdk"
        packageName = "trikita.anvil"
        manualSetter = MemberName(packageName, manualSetterName)
    }

    private fun Project.getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        superclassName: String,
        configuration: Configuration,
        quirks: InkrementalQuirks
    ) = getSupportConfiguration(
        camelCaseName,
        libraryName,
        superclassName,
        configuration,
        quirks,
        listOf(),
        listOf(getAndroidJar(28))
    )

    private fun Project.getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        superclassName: String,
        libraries: Map<String, String>,
        rawDeps: Map<String, String>,
        quirks: InkrementalQuirks
    ) = getSupportConfiguration(
        camelCaseName,
        libraryName,
        superclassName,
        null,
        quirks,
        libraries.map { getDependencyJar(it.key, it.value) },
        rawDeps.map { getDependencyJar(it.key, it.value) } + getAndroidJar(28)
    )

    private fun getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        manualSetterName: String,
        configuration: Configuration?,
        quirks: InkrementalQuirks,
        libFiles: List<File> = listOf(),
        depFiles: List<File> = listOf()
    ): DSLGeneratorTask.() -> Unit = {
        if(configuration == null) {
            dependsOn("copyDependenciesRelease")
        }

        this.quirks = quirks
        this.configuration = configuration
        javadocContains = "It contains views and their setters from the library $libraryName"
        outputDirectory = "main"
        jarFiles = libFiles
        nullabilitySourceFiles = libFiles
        dependencies = depFiles
        this.camelCaseName = camelCaseName

        // FIXME as soon as metadata for generated scopes is present, put proper package name here
        //packageName = "trikita.anvil." + dashToDot(libraryName)
        packageName = "trikita.anvil"

        if (manualSetterName.isNotEmpty()) {
            // TODO append package name
            manualSetter = MemberName(packageName, manualSetterName)
        }
    }

    private fun Project.getAndroidJar(api: Int): File {
        val localProperties = File(rootDir, "local.properties")
        val sdkDir = if (localProperties.exists()) {
            loadPropertiesFromFile(localProperties).getProperty("sdk.dir")
        } else {
            System.getenv("ANDROID_HOME")
        }
        val jarFile = File("$sdkDir/platforms/android-$api/android.jar")
        if(!jarFile.exists()) error("Jar file for SDK $api is not found at ${jarFile.absolutePath}")
        return jarFile
    }

    private fun Project.getDependencyJar(libraryName: String, version: String): File =
        File(buildDir.absoluteFile, "dependencies/release/$libraryName-$version.jar")
}

@Suppress("UnstableApiUsage")
abstract class AarToJarTransform : TransformAction<TransformParameters.None> {
    @get:Inject
    abstract val inputChanges: InputChanges

    @get:InputArtifact
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val input: Provider<FileSystemLocation>

    override fun transform(outputs: TransformOutputs) {
        inputChanges.getFileChanges(input).forEach { change ->
            val changedFile = change.file
            val outFile = outputs.file("${changedFile.nameWithoutExtension}.jar")
            if (change.fileType != FileType.FILE) {
                return@forEach
            }
            when (change.changeType) {
                ChangeType.ADDED, ChangeType.MODIFIED -> {
                    outFile.parentFile.mkdirs()
                    ZipFile(changedFile).use { zipFile ->
                        zipFile.entries()
                            .toList()
                            .first { it.name == "classes.jar" }
                            .let(zipFile::getInputStream)
                            .use { input ->
                                outFile.outputStream().use { output ->
                                    input.copyTo(output)
                                }
                            }
                    }
                }
                ChangeType.REMOVED -> {
                    outFile.delete()
                }
            }
        }
    }
}

fun loadPropertiesFromFile(file: File): Properties = Properties().apply {
    file.inputStream().use { load(it) }
}

fun dashToDot(libraryName: String?): String = libraryName?.replace('-', '.') ?: ""
