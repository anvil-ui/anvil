package dev.inkremental.meta.gradle

import com.android.build.gradle.internal.tasks.factory.dependsOn
import dev.inkremental.meta.model.*
import org.gradle.api.*
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Usage.USAGE_ATTRIBUTE
import org.gradle.api.component.SoftwareComponentFactory
import org.gradle.api.internal.artifacts.ArtifactAttributes.ARTIFACT_FORMAT
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.*
import java.io.File
import java.util.*
import javax.inject.Inject

class InkrementalGenPlugin @Inject constructor(
    private val componentFactory: SoftwareComponentFactory
) : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val extension = extensions.create<InkrementalMetaExtension>(EXTENSION)

        val genConfiguration = configurations.create(CONFIGURATION_GEN) {
            description = "Input artifacts that should be processed by Inkremental code generator"
            isCanBeResolved = true
            isCanBeConsumed = false
            attributes {
                attribute(ARTIFACT_FORMAT, ArtifactTypeDefinition.JAR_TYPE) // JVM_CLASS_DIRECTORY
                //attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
            }
        }

        val moduleConfiguration = configurations.create(CONFIGURATION_MODULE) {
            description = "Input descriptors of Inkremental modules"
            isCanBeResolved = true
            isCanBeConsumed = false
            attributes {
                attribute(USAGE_ATTRIBUTE, objects.named(USAGE))
            }
        }

        dependencies.registerTransform(AarToJarTransform::class.java) {
            from.attribute(ARTIFACT_FORMAT, FORMAT_AAR)
            to.attribute(ARTIFACT_FORMAT, ArtifactTypeDefinition.JAR_TYPE)
        }

        // "implementation" extends from "api", so it's also covered
        configurations["api"].extendsFrom(genConfiguration)

        afterEvaluate {
            extension.modules.forEach { module ->
                when(module.platform) {
                    InkrementalPlatform.ANDROID -> generateAndroidTasks(componentFactory, module, moduleConfiguration, genConfiguration)
                    else -> TODO()
                }
            }
        }
    }
}

const val EXTENSION = "inkremental"
const val CONFIGURATION_GEN = "inkrementalGen"
const val CONFIGURATION_MODULE = "inkremental"
const val CONFIGURATION_MODULE_DEF = "inkrementalDef"
const val USAGE = "inkremental-meta"
const val FORMAT_AAR = "aar"
const val FORMAT_JSON = "json"

fun loadPropertiesFromFile(file: File): Properties = Properties().apply {
    file.inputStream().use { load(it) }
}

fun Project.generateAndroidTasks(
    componentFactory: SoftwareComponentFactory,
    module: InkrementalMetaModule,
    moduleConfiguration: Configuration,
    configuration: Configuration
) {
    when(module.type) {
        InkrementalType.SDK -> {
            val allModelsTask = tasks.register<DefaultTask>("generateSdkModel")
            val allDslsTask = tasks.register<DefaultTask>("generateSdkDsl")

            for(apiLevel in listOf(15, 19, 21)) {
                val (modelTask, dslTask) = createDslTasks<GenerateAndroidSdkModelTask>(
                    module,
                    "Sdk$apiLevel",
                    "Sdk$apiLevel",
                    getOutputDir("sdk$apiLevel"),
                    componentFactory,
                    null
                ) {
                    camelCaseName = "Sdk"
                    javadocContains = "It contains views and their setters for Android SDK (API level $apiLevel)"
                    outputFile = file(getModelOutputFile("${module.name}$apiLevel"))

                    jarFiles = listOf(getAndroidJar(apiLevel))
                    nullabilitySourceFiles = listOf(getAndroidJar(28))
                }
                allModelsTask.dependsOn(modelTask)
                allDslsTask.dependsOn(dslTask)
            }
        }
        InkrementalType.LIBRARY -> {
            createDslTasks<GenerateAndroidLibraryModelTask>(
                module,
                module.camelCaseName,
                "",
                getOutputDir("main"),
                componentFactory,
                moduleConfiguration
            ) {
                javadocContains = "It contains views and their setters for the library ${module.name}"

                this.configuration = configuration
                sdkDependencies = listOf(getAndroidJar(28))
            }
        }
    }
}

private inline fun <reified T: GenerateModelTask> Project.createDslTasks(
    module: InkrementalMetaModule,
    dslName: String,
    variantName: String,
    outputDir: File,
    componentFactory: SoftwareComponentFactory,
    //moduleDefConfiguration: Configuration,
    moduleConfiguration: Configuration? = null,
    noinline configuration: T.() -> Unit):
        Pair<TaskProvider<T>, TaskProvider<GenerateDslTask>> {
    val outConfName = CONFIGURATION_MODULE_DEF + dslName

    val outConfiguration = configurations.create(outConfName) {
        description = "Descriptors of Inkremental modules"
        isCanBeResolved = false
        isCanBeConsumed = true
        attributes {
            attribute(USAGE_ATTRIBUTE, objects.named(USAGE))
        }
    }

    val component = componentFactory.adhoc("inkrementalModel$dslName")
    components.add(component)
    component.addVariantsFromConfiguration(outConfiguration) {
        mapToMavenScope("inkremental")
    }

    val modelTask = tasks.register<T>("generate${dslName}Model") {
        quirks = module.quirks
        camelCaseName = module.camelCaseName
        srcPackage = module.srcPackage
        modulePackage = module.modulePackage
        manualSetterName = module.manualSetterName
        outputFile = file(getModelOutputFile(module.name))
        configuration()
    }

    val modelFile = modelTask.get().outputFile
    artifacts.add(outConfName, modelFile) {
        builtBy(modelTask)
    }

    val dslTask = tasks.register<GenerateDslTask>("generate${dslName}Dsl") {
        dependsOn(modelTask)
        this.modelFile = modelFile
        this.configuration = moduleConfiguration
        this.outputDir = outputDir
    }
    //moduleDefConfiguration.artifacts.add(arti)
    listOf("Debug", "Release").forEach { tasks.getByName("compile${variantName}${it}Kotlin").dependsOn(dslTask) }
    return modelTask to dslTask
}

private fun Project.getModelOutputFile(modelName: String) = buildDir / "inkremental" / "$modelName.json"
private fun Project.getOutputDir(sourceSetName: String) = projectDir / "src" / sourceSetName / "kotlin"

private fun Project.getAndroidJar(api: Int): File {
    val localProperties = File(rootDir, "local.properties")
    val sdkDir = loadPropertiesFromFile(localProperties).getProperty("sdk.dir")
        ?: System.getenv("ANDROID_SDK_ROOT")
        ?: System.getenv("ANDROID_HOME")
        ?: error("Android SDK location is not defined. " +
                "Please put SDK path to either local.properties file to property sdk.dir " +
                "or pass it via ANDROID_SDK_ROOT environment variable.")
    val jarFile = File("$sdkDir/platforms/android-$api/android.jar")
    if(!jarFile.exists()) error("Jar file for SDK $api is not found at ${jarFile.absolutePath}." +
            "Please download platform $api via SDK manager or by invoking " +
            "\"sdkmanager platforms;android-$api\" from shell.")
    return jarFile
}
