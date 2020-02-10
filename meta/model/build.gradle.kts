plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("stdlib-jdk8"))
        api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")
        api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")
        api("com.squareup:kotlinpoet:1.3.0")
        compileClasspath("androidx.annotation:annotation:1.1.0")
        testImplementation(kotlin("test"))
        testImplementation(kotlin("test-junit"))
    }
}

