package dev.inkremental.meta.gradle

import com.android.build.gradle.LibraryExtension
import dev.inkremental.meta.model.InkrementalType
import dev.inkremental.meta.model.div
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*

class InkrementalModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        apply<InkrementalGenPlugin>()
        apply<MavenPublishPlugin>()

        val compileSdk = 28
        val minSdk = 17
        val targetSdk = compileSdk

        val android = extensions.getByType<LibraryExtension>()

        android.apply {
            compileSdkVersion(compileSdk)

            defaultConfig {
                minSdkVersion(minSdk)
                targetSdkVersion(targetSdk)
            }

            sourceSets.all { java.srcDirs("src/$name/kotlin") }

            lintOptions.isAbortOnError = false
            testOptions.unitTests.isReturnDefaultValues = true
        }

        dependencies {
            "api"("org.jetbrains.kotlin:kotlin-stdlib-jdk7")
            "testImplementation"("org.jetbrains.kotlin:kotlin-test")
            "testImplementation"("org.jetbrains.kotlin:kotlin-test-junit")
        }

        val bintrayUser = System.getenv("BINTRAY_USER") ?: prop("bintrayUser")
        val bintrayApiKey = System.getenv("BINTRAY_API_KEY") ?: prop("bintrayApiKey")
        val bintrayRepo = System.getenv("BINTRAY_REPO") ?: prop("BINTRAY_REPO")
        if(bintrayUser != null && bintrayApiKey != null) {
            extensions.configure<PublishingExtension> {
                repositories {
                    maven {
                        name = "Bintray"
                        url = uri("https://api.bintray.com/maven/" +
                            "${prop("BINTRAY_ORG")}/" +
                            bintrayRepo +
                            "/${prop("POM_PACKAGE_NAME")}/" +
                            ";publish=1")
                        credentials {
                            username = bintrayUser
                            password = bintrayApiKey
                        }
                    }
                }
            }
        }

        // FIXME generate proper -javadoc (and -sources) JARs
        /*android.libraryVariants.configureEach {
            val androidJar = android.sdkDirectory / "platforms" / compileSdk.toString() / "android.jar"
            val compileClasspath = getCompileClasspath(null)

            val javadoc = tasks.register<Javadoc>("generate${name.capitalize()}Javadoc") {
                description = "Generates Javadoc for $name."
                source = compileClasspath.asFileTree
                classpath = files(compileClasspath, androidJar)
                extra["androidJar"] = androidJar
            }

            tasks.register<Jar>("generate${name.capitalize()}JavadocJar") {
                dependsOn(javadoc)
                from(javadoc.get().destinationDir)
                archiveClassifier.set("javadoc")
            }
        }*/

        fun registerPublication(name: String, bundleName: String, artifactId: String, verion: String) {
            registerAnvilPublication(
                name,
                artifactId,
                verion,
                tasks.getByName("bundle${bundleName}ReleaseAar"),
                //tasks.getByName("generate${bundleName}ReleaseJavadocJar"),
                *configurations.getByName(CONFIGURATION_MODULE_DEF + name).artifacts.toTypedArray()
            )
        }

        afterEvaluate {
            extensions.getByType<InkrementalMetaExtension>().modules.forEach {
                val version = (if(it.version.isNotEmpty()) "${it.version}-" else "") + project.version.toString()
                when(it.type) {
                    InkrementalType.SDK -> {
                        registerPublication("Sdk17", "Sdk17", prop("POM_ARTIFACT_SDK17_ID")!!, version)
                        registerPublication("Sdk19", "Sdk19", prop("POM_ARTIFACT_SDK19_ID")!!, version)
                        registerPublication("Sdk21", "Sdk21", prop("POM_ARTIFACT_SDK21_ID")!!, version)
                    }
                    InkrementalType.LIBRARY ->
                        registerPublication(it.camelCaseName, "", prop("POM_ARTIFACT_ID")!!, version)
                }
            }
        }
    }

    private fun Project.registerAnvilPublication(
        name: String,
        artifactId: String,
        version: String,
        vararg artifacts: Any) =
        project.extensions.getByType<PublishingExtension>()
            .publications
            .register<MavenPublication>(name) {
                this.groupId = project.group.toString()
                this.artifactId = artifactId
                this.version = version
                artifacts.forEach { artifact(it) }
                fixPom(this)
            }
}


fun Project.prop(key: String): String? =
    findProperty(key)?.let { it as String }

private fun Project.fixPom(publication: MavenPublication) = publication.pom.withXml {
    with(asNode()) {
        appendNode("description", property("POM_DESCRIPTION"))
        appendNode("name", property("POM_NAME"))
        appendNode("url", property("POM_URL"))
        with(appendNode("scm")) {
            appendNode("url", property("POM_SCM_URL"))
            appendNode("connection", property("POM_SCM_CONNECTION"))
            appendNode("developerConnection", property("POM_SCM_DEV_CONNECTION"))
        }
        with(appendNode("licenses")) {
            with(appendNode("license")) {
                appendNode("name", property("POM_LICENCE_NAME"))
                appendNode("url", property("POM_LICENCE_URL"))
                appendNode("distribution", property("POM_LICENCE_DIST"))
            }
        }
    }
}
