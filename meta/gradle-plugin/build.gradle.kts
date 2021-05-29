plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation(project(":introspect-android"))
    implementation(project(":introspect-ios")) {
        exclude("org.jetbrains.kotlin", "kotlin-native-library-reader")
    }

    //compileClasspath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    //runtimeClasspath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    compileClasspath("com.android.tools.build:gradle:3.5.3")
    runtimeClasspath("com.android.tools.build:gradle:3.5.3")
}

gradlePlugin {
    plugins {
        register("gen") {
            id = "dev.inkremental.gen"
            implementationClass = "dev.inkremental.meta.gradle.InkrementalGenPlugin"
        }
        register("module") {
            id = "dev.inkremental.module"
            implementationClass = "dev.inkremental.meta.gradle.InkrementalModulePlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
