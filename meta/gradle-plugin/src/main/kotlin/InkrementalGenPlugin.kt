package dev.inkremental.meta.gradle

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
                createDslTasks("Sdk21", getSdkOutputDir(21), getSdkConfiguration(21, module))
                createDslTasks("Sdk19", getSdkOutputDir(19), getSdkConfiguration(19, module))
                createDslTasks("Sdk15", getSdkOutputDir(15), getSdkConfiguration(15, module))

                project.tasks.register<Task>("generateSdkModel") {
                    dependsOn("generateSdk15Model", "generateSdk19Model", "generateSdk21Model")
                }
                project.tasks.register<Task>("generateSdkDsl") {
                    dependsOn("generateSdk15Dsl", "generateSdk19Dsl", "generateSdk21Dsl")
                }
            }
            InkrementalType.LIBRARY -> {
                createDslTasks(module.camelCaseName, getOutputDir("main")) {
                    if (module.libraries.isNotEmpty()) {
                        error("You should use configuration to setup generator")
                    } else {
                        this.configuration = configuration
                        quirks = module.quirks
                        javadocContains = "It contains views and their setters from the library ${module.name}"
                        jarFiles = listOf()
                        nullabilitySourceFiles = listOf()
                        dependencies = listOf(getAndroidJar(28))
                        camelCaseName = module.camelCaseName

                        // FIXME as soon as metadata for generated scopes is present, put proper package name here
                        //packageName = "trikita.anvil." + dashToDot(module.name)
                        packageName = "trikita.anvil"
                        manualSetterName = module.manualSetterName
                        outputFile = file(getModelOutputFile(module.name))
                    }
                }
            }
        }
    }

    private fun Project.createDslTasks(dslName: String, outputDir: File, configuration: GenerateModelTask.() -> Unit) {
        val modelTask = tasks.register("generate${dslName}Model", configuration)
        tasks.register<GenerateDslTask>("generate${dslName}Dsl") {
            dependsOn(modelTask)
            modelFile = modelTask.get().outputFile
            outputDirectory = outputDir
        }
    }

    private fun Project.getSdkConfiguration(apiLevel: Int, module: InkrementalMetaModule): GenerateModelTask.() -> Unit = {
        javadocContains = "It contains views and their setters from API level $apiLevel"
        quirks = module.quirks
        jarFiles = listOf(project.getAndroidJar(apiLevel))
        nullabilitySourceFiles = listOf(project.getAndroidJar(28))
        isSourceSdk = true
        camelCaseName = "Sdk"
        dependencies = listOf()
        packageName = "trikita.anvil"
        manualSetterName = module.manualSetterName
        outputFile = file(getModelOutputFile("${module.name}$apiLevel"))
    }
    private fun Project.getModelOutputFile(modelName: String) = buildDir / "inkremental" / "$modelName.json"
    private fun Project.getOutputDir(sourceSetName: String) = projectDir / "src" / sourceSetName / "kotlin"
    private fun Project.getSdkOutputDir(apiLevel: Int) = getOutputDir("sdk$apiLevel")

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
