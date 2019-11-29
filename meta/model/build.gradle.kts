plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        getByName("jvmMain") {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}
