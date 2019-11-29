plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.60" apply false
    id("org.jetbrains.kotlin.android") version "1.3.60" apply false
    id("com.android.application") version "3.5.2" apply false
    id("com.android.library") version "3.5.2" apply false
    id("dev.inkremental.module") version "0.7.1" apply false
}

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
        google()
        jcenter()
    }

    val GROUP: String by project
    val VERSION_NAME: String by project

    group = GROUP
    version = VERSION_NAME
}
