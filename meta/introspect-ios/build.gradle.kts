plugins {
    kotlin("jvm")
    application
}

kotlin {
    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("util-klib"))
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("native-library-reader"))
    }
}

application {
    mainClassName = "dev.inkremental.meta.introspect.ios.MainKt"
}

