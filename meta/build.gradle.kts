import java.util.Properties

plugins {
    kotlin("jvm") version "1.3.60" apply false
    kotlin("plugin.serialization") version "1.3.60" apply false
}

Properties()
    .also { props ->
        file("$rootDir/../gradle.properties").inputStream().use {
            props.load(it)
        }
    }
    .forEach { name, value -> rootProject.extra[name as String] = value }

subprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
    }

    val GROUP: String by project
    val VERSION_NAME: String by project

    group = GROUP
    version = VERSION_NAME
}
