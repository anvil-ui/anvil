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

    /*buildscript {
        dependencies {
            classpath "com.android.tools.build:gradle:3.5.2"
        }

        repositories {
            google()
            jcenter()
        }
    }*/

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
