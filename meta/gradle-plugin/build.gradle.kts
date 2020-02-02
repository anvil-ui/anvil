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

    //compileClasspath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.60")
    //runtimeClasspath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.60")
    compileClasspath("com.android.tools.build:gradle:3.5.3")
    compileClasspath("androidx.annotation:annotation:1.1.0")
    runtimeClasspath("com.android.tools.build:gradle:3.5.3")
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
