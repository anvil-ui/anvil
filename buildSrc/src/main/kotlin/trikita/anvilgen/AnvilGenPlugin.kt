package trikita.anvilgen

import com.squareup.javapoet.ClassName
import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.kotlin.dsl.*
import java.io.File
import java.util.*

class AnvilGenPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val extension = extensions.create<AnvilGenPluginExtension>("anvilgen")

        val configuration = configurations.create("anvilgen")
        // "implementation" extends from "compile", so it's also covered
        configurations["compile"].extendsFrom(configuration)

        afterEvaluate { generateTasks(extension, configuration) }
    }

    fun Project.generateTasks(extension: AnvilGenPluginExtension, configuration: Configuration) {
        when(extension.type) {
            "sdk" -> {
                createDslGeneratorTask("SDK21", getSdkConfiguration(21))
                createDslGeneratorTask("SDK19", getSdkConfiguration(19))
                createDslGeneratorTask("SDK15", getSdkConfiguration(15))

                project.tasks.register<Task>("generateSDKDSL") {
                    dependsOn("generateSDK15DSL", "generateSDK19DSL", "generateSDK21DSL")
                }
            }
            "support" -> {
                createDslGeneratorTask(extension.camelCaseName,
                    // temporary toggle for supporting both array- and configuration-based libraries declaration
                    if(extension.libraries.isNotEmpty()) {
                        getSupportConfiguration(
                            extension.camelCaseName, extension.moduleName,
                            extension.superclass, extension.libraries, extension.dependencies
                        )
                    } else {
                        getSupportConfiguration(
                            extension.camelCaseName, extension.moduleName,
                            extension.superclass, configuration
                        )
                    }
                )
            }
            else -> error("Unknown generator type: ${extension.type}")
        }
    }

    private fun Project.createDslGeneratorTask(dslName: String, configuration: DSLGeneratorTask.() -> Unit) =
        tasks.register("generate${dslName}DSL", configuration)

    private fun getSdkConfiguration(apiLevel: Int): DSLGeneratorTask.() -> Unit = {
        javadocContains = "It contains views and their setters from API level $apiLevel"
        outputDirectory = "sdk$apiLevel"
        jarFiles = listOf(project.getAndroidJar(apiLevel))
        nullabilitySourceFiles = listOf(project.getAndroidJar(28))
        isSourceSdk = true
        outputClassName = "DSL"
        packageName = "trikita.anvil"
        superclass = ClassName.get("trikita.anvil", "BaseDSL")
    }

    private fun Project.getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        superclassName: String,
        configuration: Configuration
    ) = getSupportConfiguration(
        camelCaseName,
        libraryName,
        superclassName,
        configuration,
        listOf(),
        listOf(getAndroidJar(28))
    )

    private fun Project.getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        superclassName: String,
        libraries: Map<String, String>,
        rawDeps: Map<String, String>
    ) = getSupportConfiguration(
        camelCaseName,
        libraryName,
        superclassName,
        null,
        libraries.map { getDependencyJar(it.key, it.value) },
        rawDeps.map { getDependencyJar(it.key, it.value) } + getAndroidJar(28)
    )

    private fun getSupportConfiguration(
        camelCaseName: String,
        libraryName: String,
        superclassName: String,
        configuration: Configuration?,
        libFiles: List<File> = listOf(),
        depFiles: List<File> = listOf()
    ): DSLGeneratorTask.() -> Unit = {
        if(configuration == null) {
            dependsOn("copyDependenciesRelease")
        }

        this.configuration = configuration
        javadocContains = "It contains views and their setters from the library $libraryName"
        outputDirectory = "main"
        jarFiles = libFiles
        nullabilitySourceFiles = libFiles
        dependencies = depFiles
        outputClassName = "${camelCaseName}DSL"
        packageName = "trikita.anvil." + dashToDot(libraryName)

        if (superclassName.isNotEmpty()) {
            superclass = ClassName.get(packageName, superclassName)
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

fun loadPropertiesFromFile(file: File): Properties = Properties().apply {
    file.inputStream().use { load(it) }
}

fun dashToDot(libraryName: String?): String = libraryName?.replace('-', '.') ?: ""
