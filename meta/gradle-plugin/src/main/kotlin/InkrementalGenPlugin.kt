package dev.inkremental.meta.gradle

import dev.inkremental.meta.model.*
import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.internal.artifacts.ArtifactAttributes.ARTIFACT_FORMAT
import org.gradle.kotlin.dsl.*
import java.io.File
import java.util.*

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
            extension.modules.forEach { module ->
                when(module.platform) {
                    InkrementalPlatform.ANDROID -> generateAndroidTasks(module, configuration)
                    else -> TODO()
                }
            }
        }
    }
}

fun loadPropertiesFromFile(file: File): Properties = Properties().apply {
    file.inputStream().use { load(it) }
}

fun dashToDot(libraryName: String?): String = libraryName?.replace('-', '.') ?: ""

fun Project.generateAndroidTasks(module: InkrementalMetaModule, configuration: Configuration) {
    when(module.type) {
        InkrementalType.SDK -> {
            createDslTasks("Sdk21", getSdkOutputDir(21), getAndroidSdkConfiguration(21, module))
            createDslTasks("Sdk19", getSdkOutputDir(19), getAndroidSdkConfiguration(19, module))
            createDslTasks("Sdk15", getSdkOutputDir(15), getAndroidSdkConfiguration(15, module))

            project.tasks.register<DefaultTask>("generateSdkModel") {
                dependsOn("generateSdk15Model", "generateSdk19Model", "generateSdk21Model")
            }
            project.tasks.register<DefaultTask>("generateSdkDsl") {
                dependsOn("generateSdk15Dsl", "generateSdk19Dsl", "generateSdk21Dsl")
            }
        }
        InkrementalType.LIBRARY -> {
            createDslTasks<GenerateAndroidLibraryModelTask>(module.camelCaseName, getOutputDir("main")) {
                this.configuration = configuration
                quirks = module.quirks
                javadocContains = "It contains views and their setters from the library ${module.name}"
                sdkDependencies = listOf(getAndroidJar(28))
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

private inline fun <reified T: GenerateModelTask> Project.createDslTasks(dslName: String, outputDir: File, noinline configuration: T.() -> Unit) {
    val modelTask = tasks.register("generate${dslName}Model", configuration)
    tasks.register<GenerateDslTask>("generate${dslName}Dsl") {
        dependsOn(modelTask)
        modelFile = modelTask.get().outputFile
        outputDirectory = outputDir
    }
}

private fun Project.getAndroidSdkConfiguration(apiLevel: Int, module: InkrementalMetaModule): GenerateAndroidSdkModelTask.() -> Unit = {
    javadocContains = "It contains views and their setters from API level $apiLevel"
    quirks = module.quirks
    jarFiles = listOf(getAndroidJar(apiLevel))
    nullabilitySourceFiles = listOf(getAndroidJar(28))
    camelCaseName = "Sdk"
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
