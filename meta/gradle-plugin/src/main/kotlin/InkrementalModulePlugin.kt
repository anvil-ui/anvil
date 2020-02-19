package dev.inkremental.meta.gradle

import com.android.build.gradle.LibraryExtension
import dev.inkremental.meta.model.InkrementalType
import dev.inkremental.meta.model.buildCamelCaseString
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.*
import kotlin.text.isNullOrEmpty

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

        val bintrayUser = envOrProp("BINTRAY_USER")
        val bintrayApiKey = envOrProp("BINTRAY_API_KEY")
        val bintrayRepo = envOrProp("BINTRAY_REPO")
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

        fun registerPublication(name: String, artifactId: String, verion: String) {

            logger.error("registerPublication: $name")
            registerAnvilPublication(
                name,
                artifactId,
                verion,
                tasks.getByName("bundle${name.capitalize()}ReleaseAar"),
                //tasks.getByName("generate${name}ReleaseJavadocJar"),
                *configurations.getByName(buildCamelCaseString(name, CONFIGURATION_MODULE_DEF)).artifacts.toTypedArray()
            )
        }

        val extension = extensions.getByType<InkrementalMetaExtension>()

        afterEvaluate {
            extension.modules.configureEach {
                val it = this
                val version = (if (it.version.isNotEmpty()) "${it.version}-" else "") + project.version.toString()
                when (it.type) {
                    InkrementalType.SDK -> {
                        registerPublication("sdk-17", prop("POM_ARTIFACT_SDK17_ID")!!, version)
                        registerPublication("sdk-19", prop("POM_ARTIFACT_SDK19_ID")!!, version)
                        registerPublication("sdk-21", prop("POM_ARTIFACT_SDK21_ID")!!, version)
                    }
                    InkrementalType.LIBRARY ->
                        registerPublication(it.dslName, prop("POM_ARTIFACT_ID")!!, version)
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

private fun Project.envOrProp(key: String): String? =
    System.getenv(key).takeUnless(String::isNullOrEmpty) ?: prop(key)

private fun Project.fixPom(publication: MavenPublication) = publication.pom.apply {
    packaging = "aar"
    description.set(prop("POM_DESCRIPTION"))
    name.set(prop("POM_NAME"))
    url.set(prop("POM_URL"))
    scm {
        url.set(prop("POM_SCM_URL"))
        connection.set(prop("POM_SCM_CONNECTION"))
        developerConnection.set(prop("POM_SCM_DEV_CONNECTION"))
    }
    licenses {
        license {
            name.set(prop("POM_LICENCE_NAME"))
            url.set(prop("POM_LICENCE_URL"))
            distribution.set(prop("POM_LICENCE_DIST"))
        }
    }
}
