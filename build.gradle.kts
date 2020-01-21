plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.60" apply false
    id("org.jetbrains.kotlin.android") version "1.3.60" apply false
    id("com.android.application") version "3.5.2" apply false
    id("com.android.library") version "3.5.2" apply false
    id("dev.inkremental.module") version "0.7.1" apply false
}

fun loadProperties(fileName: String) =
    java.util.Properties()
        .also { props ->
            try {
                file("$rootDir/$fileName").inputStream().use {
                    props.load(it)
                }
            } catch(e: java.io.FileNotFoundException) {
                // do nothing
            }
        }
        .forEach { name, value -> rootProject.extra[name as String] = value }

loadProperties("local.properties")

subprojects {
    extra["junit_version"] = "4.12"
    extra["mockito_version"] = "2.23.0"

    repositories {
        mavenLocal()
        maven(url = "https://dl.bintray.com/inkremental/maven")
        google()
        jcenter()
    }

    val GROUP: String by project
    val VERSION_NAME: String by project

    group = GROUP
    version = VERSION_NAME
}

// TODO constraintlayout and yogalayout

tasks.register("generateAndCheck") {
    dependsOn(
        ":anvil:check",
        ":anvil-appcompat-v7:check",
        ":anvil-gridlayout-v7:check",
        ":anvil-recyclerview-v7:check",
        ":anvil-cardview-v7:check",
        ":anvil-design:check",
        ":anvil-support-v4:check"
    )
}

tasks.register("generateAndPublishLocally") {
    dependsOn(
        ":anvil:publishToMavenLocal",
        ":anvil-appcompat-v7:publishToMavenLocal",
        ":anvil-gridlayout-v7:publishToMavenLocal",
        ":anvil-recyclerview-v7:publishToMavenLocal",
        ":anvil-cardview-v7:publishToMavenLocal",
        ":anvil-design:publishToMavenLocal",
        ":anvil-support-v4:publishToMavenLocal"
    )
}

tasks.register("generateAndPublish") {
    dependsOn(
        ":anvil:publish",
        ":anvil-appcompat-v7:publish",
        ":anvil-gridlayout-v7:publish",
        ":anvil-recyclerview-v7:publish",
        ":anvil-cardview-v7:publish",
        ":anvil-design:publish",
        ":anvil-support-v4:publish"
    )
}
