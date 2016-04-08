package trikita.anvilgen


import com.squareup.javapoet.MethodSpec
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AnvilGenPlugin implements Plugin<Project> {

    static class BuilderLock {
        def locked = false
    }

    static {
        MethodSpec.Builder.mixin BuilderLock
    }

    @Override
    void apply(Project project) {
        // Add the 'anvilgen' extension object
        project.extensions.create("anvilgen", AnvilGenPluginExtension)

        project.afterEvaluate { generateTasks(it) }
    }

    def generateTasks(Project project) {
        def type = project.anvilgen.type
        if (type == "sdk") {
            // Most practical API versions according to Android Dashboards:
            // - newer then API level 10 (GB 2.3.3, 99.9% of devices)
            // - newer than API level 15 (ICS 4.0.3, 97.3% of devices)
            // - newer than API level 19 (KK 4.0.3, 72.7% of devices)
            // - newer than API level 21 (LP 5.0, 38.4% of devices)
            project.task(type: DSLGeneratorTask, "generateSDK19DSL", getSDKClosure(project, 19))
            project.task(type: DSLGeneratorTask, "generateSDK15DSL", getSDKClosure(project, 15))
            project.task(type: DSLGeneratorTask, "generateSDK10DSL", getSDKClosure(project, 10))

            project.task(dependsOn: ["generateSDK10DSL", "generateSDK15DSL", "generateSDK19DSL"], "generateSDKDSL")
        } else {
            project.task(type: DSLGeneratorTask, "generate${dashToCamelCase(type)}DSL")
        }
    }

    def getSDKClosure(project, apiLevel) {
        return {
            taskName = "generateSDK${apiLevel}DSL"
            javadocContains = "It contains views and their setters from API level ${apiLevel}"
            outputDirectory = "sdk${apiLevel}"
            jarFile = getAndroidJar(project, apiLevel)
        }
    }

    def getAndroidJar(project, api) {
        def rootDir = project.rootDir
        def localProperties = new File(rootDir, "local.properties")
        def sdkDir
        if (localProperties.exists()) {
            Properties properties = new Properties()
            localProperties.withInputStream { instr -> properties.load(instr) }
            sdkDir = properties.getProperty('sdk.dir')
        } else {
            sdkDir = System.getenv('ANDROID_HOME')
        }
        return new File(sdkDir + "/platforms/android-" + api + "/android.jar")
    }

    def dashToCamelCase(str) {
        if (!str || str.isAllWhitespace()) {
            return ''
        }
        return str.replaceAll(/_\w/) { it[1].toUpperCase() }
    }
}