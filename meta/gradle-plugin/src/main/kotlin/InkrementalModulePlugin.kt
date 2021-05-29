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
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin
import kotlin.text.isNullOrEmpty

class InkrementalModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        apply<InkrementalGenPlugin>()
        apply<MavenPublishPlugin>()
        apply<SigningPlugin>()

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

        val sonatypeUser = envOrProp("ossrhUsername")
        val sonatypePassword = envOrProp("ossrhPassword")

        if(sonatypeUser != null && sonatypePassword != null) {
            extensions.configure<PublishingExtension> {

                repositories {
                    maven {
                        name = "sonatype"
                        setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                        credentials {
                            username = sonatypeUser ?: return@credentials
                            password = sonatypePassword ?: return@credentials
                        }
                    }
                }
            }

            extensions.configure<SigningExtension> {
                val publishing: PublishingExtension by project

                sign(publishing.publications)
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
            logger.debug("registerPublication: $name")
            registerAnvilPublication(
                name,
                artifactId,
                verion,
                tasks.getByName(androidAarTaskName(name)), // TODO named?
                //tasks.getByName("generate${name}ReleaseJavadocJar"),
                *configurations.getByName(moduleDefConfigurationName(name)).artifacts.toTypedArray()
            )
        }

        val extension = extensions.getByType<InkrementalMetaExtension>()

        afterEvaluate {
            extension.modules.configureEach {
                val pubVersion = (if (version.isNotEmpty()) "$version-" else "") + project.version.toString()
                registerPublication(dslName, prop("POM_ARTIFACT_ID")!!, pubVersion)
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
    developers {
        developer {
            id.set("sgrekov")
            name.set("Sergey Grekov")
            email.set("sngrekov@gmail.com")
        }
        developer {
            id.set("r4zzz4k")
            name.set("Andrew Mikhaylov")
            email.set("mail@r4zzz4k.me")
        }
    }
}
