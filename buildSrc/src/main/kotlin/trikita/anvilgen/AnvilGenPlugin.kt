package trikita.anvilgen

import com.squareup.javapoet.ClassName
import groovy.lang.Closure
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.*

class AnvilGenPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Add the 'anvilgen' extension object
        project.extensions.create("anvilgen", AnvilGenPluginExtension::class.java)

        project.afterEvaluate { generateTasks(it) }
    }

    fun generateTasks(project: Project) {
        val extension = project.extensions.getByName("anvilgen") as AnvilGenPluginExtension

        val type = extension.type
        if (type == "sdk") {
            // Most practical API versions according to Android Dashboards:
            // - newer than API level 15 (ICS 4.0.3, 97.3% of devices)
            // - newer than API level 19 (KK 4.0.3, 72.7% of devices)
            // - newer than API level 21 (LP 5.0, 38.4% of devices)
            project.task(mapOf("type" to DSLGeneratorTask::class.java), "generateSDK21DSL", getSDKClosure(project, 21))
            project.task(mapOf("type" to DSLGeneratorTask::class.java), "generateSDK19DSL", getSDKClosure(project, 19))
            project.task(mapOf("type" to DSLGeneratorTask::class.java), "generateSDK15DSL", getSDKClosure(project, 15))

            project.task(mapOf("dependsOn" to arrayOf("generateSDK15DSL", "generateSDK19DSL", "generateSDK21DSL")), "generateSDKDSL")
        } else {
            val supportClosure = getSupportClosure(project, extension.camelCaseName,
                    extension.libraryName, extension.version, extension.dependencies)
            project.task(mapOf("type" to DSLGeneratorTask::class.java, "dependsOn" to listOf("prepareReleaseDependencies")),
                    "generate${extension.camelCaseName}DSL",
                    supportClosure)
        }
    }

    fun getSDKClosure(project: Project, apiLevel: Int): Closure<Any> {
        return object : Closure<Any>(this) {
            init {
                maximumNumberOfParameters = 1
            }

            override fun call(arguments: Any?): Any? {
                (arguments as DSLGeneratorTask).apply {
                    taskName = "generateSDK${apiLevel}DSL"
                    javadocContains = "It contains views and their setters from API level $apiLevel"
                    outputDirectory = "sdk$apiLevel"
                    jarFile = getAndroidJar(project, apiLevel)
                    dependencies = listOf()
                    outputClassName = "DSL"
                    packageName = "trikita.anvil"
                    superclass = ClassName.get("trikita.anvil", "BaseDSL")
                }
                return null
            }
        }
    }

    fun getSupportClosure(project: Project,
                          camelCaseName: String,
                          libraryName: String,
                          version: String,
                          rawDeps: Map<String, List<String?>>): Closure<Any> {
        return object : Closure<Any>(this) {
            init {
                maximumNumberOfParameters = 1
            }

            override fun call(arguments: Any?): Any? {
                (arguments as DSLGeneratorTask).apply {
                    taskName = "generate${camelCaseName}DSL"
                    javadocContains = "It contains views and their setters from the library $libraryName"
                    outputDirectory = "main"
                    jarFile = getSupportJar(project, libraryName, version)
                    dependencies = getSupportDependencies(project, version, rawDeps)
                    outputClassName = "${camelCaseName}DSL"
                    packageName = "trikita.anvil." + dashToDot(libraryName)
                }
                return null
            }
        }
    }

    fun dashToDot(libraryName: String?): String {
        if (libraryName == null || libraryName.isBlank()) {
            return ""
        }
        return libraryName.replace('-', '.')
    }

    fun getSupportDependencies(project: Project, version: String, rawDeps: Map<String, List<String?>>): List<File> {
        val list = rawDeps.flatMap { entry ->
            entry.value.map {
                getInternalSupportJar(project, entry.key, version, it ?: "classes")
            }
        }

        val arrayList = ArrayList(list)
        arrayList.add(getAndroidJar(project, 19))
        return arrayList
    }

    fun getAndroidJar(project: Project, api: Int): File {
        val rootDir = project.rootDir
        val localProperties = File(rootDir, "local.properties")
        val sdkDir: String
        if (localProperties.exists()) {
            val properties = Properties()
            localProperties.inputStream().use { properties.load(it) }
            sdkDir = properties.getProperty("sdk.dir")
        } else {
            sdkDir = System.getenv("ANDROID_HOME")
        }
        return File("$sdkDir/platforms/android-$api/android.jar")
    }

    fun getSupportJar(project: Project, libraryName: String, version: String): File {
        return File(project.buildDir.absoluteFile,
                "intermediates/exploded-aar/com.android.support/$libraryName/$version/jars/classes.jar")
    }

    fun getInternalSupportJar(project: Project,
                              libraryName: String,
                              version: String,
                              internalJar: String): File {
        return File(project.buildDir.absoluteFile,
                "intermediates/exploded-aar/com.android.support/$libraryName/$version/jars/$internalJar.jar")
    }
}