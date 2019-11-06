plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("com.squareup:kotlinpoet:1.3.0")
    implementation("org.ow2.asm:asm:7.2")
    implementation("org.ow2.asm:asm-tree:7.2")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
    compileClasspath("com.android.tools.build:gradle:3.5.2")
    runtimeClasspath("com.android.tools.build:gradle:3.5.2")
}

gradlePlugin {
    plugins {
        register("anvilgen") {
            id = "trikita.anvilgen"
            implementationClass = "trikita.anvilgen.AnvilGenPlugin"
        }
        register("module") {
            id = "trikita.module"
            implementationClass = "trikita.anvilgen.AnvilModulePlugin"
        }
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
