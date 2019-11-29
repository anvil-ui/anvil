plugins {
    kotlin("multiplatform")
    application
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("util-klib"))
            }
        }

        getByName("jvmMain") {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation(kotlin("native-library-reader"))
            }
        }
    }
}

application {
    mainClassName = "dev.inkremental.introspect.ios.MainKt"
}
