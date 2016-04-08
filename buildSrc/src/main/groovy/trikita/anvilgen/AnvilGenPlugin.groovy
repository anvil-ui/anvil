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

        // Most practical API versions according to Android Dashboards:
        // - newer then API level 10 (GB 2.3.3, 99.9% of devices)
        // - newer than API level 15 (ICS 4.0.3, 97.3% of devices)
        // - newer than API level 19 (KK 4.0.3, 72.7% of devices)
        // - newer than API level 21 (LP 5.0, 38.4% of devices)
        project.task(type: DSLGeneratorTask, "generateSDK19DSL") { apiLevel = 19 }
        project.task(type: DSLGeneratorTask, "generateSDK15DSL") { apiLevel = 15 }
        project.task(type: DSLGeneratorTask, "generateSDK10DSL") { apiLevel = 10 }

        project.task(dependsOn: ["generateSDK10DSL", "generateSDK15DSL", "generateSDK19DSL"], "generateDSL")
    }
}