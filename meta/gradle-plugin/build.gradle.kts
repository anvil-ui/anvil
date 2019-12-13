plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(project(":model"))
    implementation(project(":introspect-android"))
    implementation(project(":introspect-ios")) {
        exclude("org.jetbrains.kotlin", "kotlin-native-library-reader")
    }

    implementation("androidx.annotation:annotation:1.1.0")
    implementation("org.ow2.asm:asm:7.2")
    implementation("org.ow2.asm:asm-tree:7.2")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
    compileClasspath("com.android.tools.build:gradle:3.5.2")
    runtimeClasspath("com.android.tools.build:gradle:3.5.2")
}

gradlePlugin {
    plugins {
        register("module") {
            id = "dev.inkremental.module"
            implementationClass = "dev.inkremental.meta.gradle.InkrementalModulePlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
