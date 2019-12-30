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
        val minSdk = 15
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

        val sourcesJar by tasks.creating(Jar::class) {
            from(android.sourceSets["main"].java.srcDirs)
            archiveClassifier.set("sources")
        }

        val javadoc by tasks.creating(Javadoc::class) {
            source(android.sourceSets["main"].java.srcDirs)
            classpath += project.files(android.bootClasspath)
        }

        val javadocJar by tasks.creating(Jar::class) {
            from(javadoc.destinationDir)
            archiveClassifier.set("javadoc")
        }

        artifacts {
            add("archives", sourcesJar)
            add("archives", javadocJar)
        }

        val bintrayUser = prop("bintrayUser")
        val bintrayApiKey = prop("bintrayApiKey")
        if(bintrayUser != null && bintrayApiKey != null) {
            extensions.configure<PublishingExtension> {
                repositories {
                    maven {
                        name = "Bintray"
                        url = uri("https://api.bintray.com/content/" +
                            "${prop("BINTRAY_ORG")}/" +
                            "${prop("BINTRAY_REPO")}/" +
                            "${prop("POM_PACKAGE_NAME")}/" +
                            "${project.version}/" +
                            ";publish=1;override=1")
                        credentials {
                            username = bintrayUser
                            password = bintrayApiKey
                        }
                    }
                }
            }
        }

        android.libraryVariants.configureEach {
            val androidJar = android.sdkDirectory / "platforms" / compileSdk.toString() / "android.jar"
            val compileClasspath = getCompileClasspath(null)

            tasks.register<Javadoc>("generate${name.capitalize()}Javadoc") {
                description = "Generates Javadoc for $name."
                source = compileClasspath.asFileTree
                classpath = files(compileClasspath, androidJar)
                extra["androidJar"] = androidJar
            }
        }

        fun registerAnvilPublications(name: String, bundleName: String, artifactId: String) {
            registerAnvilPublication(
                name,
                artifactId,
                tasks.getByName("bundle${bundleName}ReleaseAar"),
                sourcesJar,
                javadocJar
            )
            registerAnvilPublication(
                "${name}Module",
                artifactId,
                *configurations.getByName(CONFIGURATION_MODULE_DEF + name).artifacts.toTypedArray()
            )
        }

        afterEvaluate {
            extensions.getByType<InkrementalMetaExtension>().modules.forEach {
                when(it.type) {
                    InkrementalType.SDK -> {
                        registerAnvilPublications("Sdk15", "Sdk15", prop("POM_ARTIFACT_SDK15_ID")!!)
                        registerAnvilPublications("Sdk19", "Sdk19", prop("POM_ARTIFACT_SDK19_ID")!!)
                        registerAnvilPublications("Sdk21", "Sdk21", prop("POM_ARTIFACT_SDK21_ID")!!)
                    }
                    InkrementalType.LIBRARY ->
                        registerAnvilPublications(it.camelCaseName, "", prop("POM_ARTIFACT_ID")!!)
                }
            }
        }
    }

    private fun Project.registerAnvilPublication(
        name: String,
        artifactId: String,
        vararg artifacts: Any) =
        project.extensions.getByType<PublishingExtension>()
            .publications
            .register<MavenPublication>(name) {
                this.groupId = project.group.toString()
                this.artifactId = artifactId
                this.version = project.version.toString()
                artifacts.forEach { artifact(it) }
                fixPom(this)
            }
}


private fun Project.prop(key: String): String? =
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
