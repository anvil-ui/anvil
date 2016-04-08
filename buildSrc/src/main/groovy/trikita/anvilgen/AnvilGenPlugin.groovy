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
            def version = project.anvilgen.version
            def dependencies = project.anvilgen.dependencies
            project.task(type: DSLGeneratorTask, dependsOn: ["prepareReleaseDependencies"],
                    "generate${dashToCamelCase(type)}DSL",
                    getSupportClosure(project, type, version, dependencies))
        }
    }

    def getSDKClosure(project, apiLevel) {
        return {
            taskName = "generateSDK${apiLevel}DSL"
            javadocContains = "It contains views and their setters from API level ${apiLevel}"
            outputDirectory = "sdk${apiLevel}"
            jarFile = getAndroidJar(project, apiLevel)
            dependencies = []
        }
    }

    def getSupportClosure(project, type, version, List<String> rawDeps) {
        return {
            taskName = "generateSDK${dashToCamelCase(type)}DSL"
            javadocContains = "It contains views and their setters from the library ${type}"
            outputDirectory = "sdk"
            jarFile = getSupportJar(project, type, version)
            dependencies = rawDeps.collect {
                getSupportJar(project, it, version)
            } << getAndroidJar(project, 19)
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

    def getSupportJar(project, type, version) {
        return new File(project.buildDir.absoluteFile,
                "intermediates/exploded-aar/com.android.support/$type/$version/jars/classes.jar")
    }

    def dashToCamelCase(String str) {
        if (!str || str.isAllWhitespace()) {
            return ''
        }
        def remainder = str.substring(1).replaceAll(/-\w/) { it[1].toUpperCase() }
        return new StringBuilder().append(str.charAt(0).toUpperCase()).append(remainder).toString()
    }
}