package dev.inkremental.meta.gradle

import com.android.build.gradle.LibraryExtension
import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublicationContainer
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
        apply<BintrayPlugin>()

        val compileSdk = 28
        val minSdk = 15
        val targetSdk = compileSdk

        val anvil = extensions.getByType<InkrementalMetaExtension>()

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

        val bintrayUser = prop("bintrayUser")

        val bintrayExtension = if(bintrayUser == null) {
            null
        } else {
            extensions.getByType<BintrayExtension>().apply {
                user = bintrayUser
                key = prop("bintrayApiKey")!!
                pkg.apply {
                    repo = prop("BINTRAY_REPO")!!
                    userOrg = prop("BINTRAY_ORG")!!
                    name = prop("POM_PACKAGE_NAME")
                    websiteUrl = prop("POM_URL")
                    vcsUrl = prop("POM_SCM_URL")
                    setLicenses(prop("POM_LICENCE_SHORT_NAME"))
                    publish = true
                    version.apply {
                        name = project.version.toString()

                        val bintrayGpgPassword = prop("bintrayGpgPassword")
                        if(bintrayGpgPassword != null) {
                            gpg.apply {
                                sign = true
                                passphrase = bintrayGpgPassword
                            }
                        }

                        val bintrayOssUser = prop("bintrayOssUser")
                        if(bintrayOssUser != null) {
                            mavenCentralSync.apply {
                                sync = true
                                user = bintrayOssUser
                                password = prop("bintrayOssPassword")!!
                                close = "1"
                            }
                        }
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

        fun registerAnvilPublication(name: String, artifactId: String) = registerAnvilPublication(
            extensions.getByType<PublishingExtension>().publications,
            bintrayExtension,
            name,
            artifactId,
            sourcesJar,
            javadocJar
        )

        afterEvaluate {
            when {
                anvil.isSdk -> {
                    registerAnvilPublication("sdk15", prop("POM_ARTIFACT_SDK15_ID")!!)
                    registerAnvilPublication("sdk19", prop("POM_ARTIFACT_SDK19_ID")!!)
                    registerAnvilPublication("sdk21", prop("POM_ARTIFACT_SDK21_ID")!!)
                }
                anvil.isSupport -> registerAnvilPublication("lib", prop("POM_ARTIFACT_ID")!!)
                else -> error("Unknown generator type: \"${anvil.type}\"")
            }
        }
    }

    private fun Project.prop(key: String): String? =
        project.findProperty(key)?.let { it as String }

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

    private fun Project.registerAnvilPublication(
        container: PublicationContainer,
        bintrayExtension: BintrayExtension?,
        name: String,
        artifactId: String,
        sourcesJar: Jar,
        javadocJar: Jar) = container.register<MavenPublication>(name) {
        this.groupId = project.group.toString()
        this.artifactId = artifactId
        this.version = project.version.toString()
        artifact(buildDir / "outputs" / "aar" / "$artifactId-release.aar")
        artifact(sourcesJar)
        artifact(javadocJar)
        fixPom(this)

        bintrayExtension?.apply {
            if(publications == null) {
                setPublications(name)
            } else {
                setPublications(*publications + name)
            }
        }
    }
}
