package trikita.anvilgen

import com.android.build.gradle.LibraryExtension
import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*

class AnvilModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        apply<AnvilGenPlugin>()
        apply<MavenPublishPlugin>()
        apply<BintrayPlugin>()

        val compileSdk = 28
        val minSdk = 15
        val targetSdk = compileSdk

        val artifactId = prop("POM_ARTIFACT_ID")!!
        val publication = "somestuff"

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

        val sourcesJar by tasks.creating(Jar::class) {
            from(android.sourceSets["main"].java.srcDirs)
            archiveClassifier.set("sources")
        }

        val javadoc by tasks.creating(Javadoc::class) {
            source(android.sourceSets["main"].java.srcDirs)
            classpath += project.files(android.bootClasspath) //.join(File.pathSeparator))
        }

        val javadocJar by tasks.creating(Jar::class) {
            from(javadoc.destinationDir)
            archiveClassifier.set("javadoc")
        }

        artifacts {
            add("archives", sourcesJar)
            add("archives", javadocJar)
        }

        extensions.getByType<PublishingExtension>().publications.register<MavenPublication>(publication) {
            this.groupId = project.group.toString()
            this.artifactId = artifactId
            this.version = project.version.toString()
            artifact(buildDir / "outputs" / "aar" / "$artifactId-release.aar")
            artifact(sourcesJar)
            artifact(javadocJar)
            fixPom(this)
        }

        extensions.getByType<BintrayExtension>().apply {
            user = prop("bintrayUser") ?: ""
            key = prop("bintrayApiKey") ?: ""
            setPublications(publication)
            pkg.apply {
                repo = "maven"
                name = prop("POM_PACKAGE_NAME")
                websiteUrl = prop("POM_URL")
                vcsUrl = prop("POM_SCM_URL")
                setLicenses(prop("POM_LICENCE_SHORT_NAME"))
                publish = true
                version.apply {
                    name = project.version.toString()
                    gpg.apply {
                        sign = true
                        passphrase = prop("bintrayGpgPassword")
                    }
                    mavenCentralSync.apply {
                        sync = true
                        user = prop("bintrayOssUser")
                        password = prop("bintrayOssPassword")
                        close = "1"
                    }
                }
            }
        }

        android.libraryVariants.all { variant ->
            val name = variant.name
            val androidJar = android.sdkDirectory / "platforms" / compileSdk.toString() / "android.jar"
            val compileClasspath = variant.getCompileClasspath(null)

            tasks.register<Javadoc>("generate${name.capitalize()}Javadoc") {
                description = "Generates Javadoc for $name."
                source = compileClasspath.asFileTree
                classpath = files(compileClasspath, androidJar)
                extra["androidJar"] = androidJar
            }
            true
        }

        Unit
    }

    private fun Project.prop(key: String): String? = properties[key]?.toString()

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
            with(appendNode("developers")) {
                with(appendNode("developer")) {
                    appendNode("id", property("POM_DEVELOPER_ID"))
                    appendNode("name", property("POM_DEVELOPER_NAME"))
                    appendNode("email", property("POM_DEVELOPER_EMAIL"))
                }
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
}
