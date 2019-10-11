plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation("androidx.annotation:annotation:1.0.0")
    implementation("com.squareup:javapoet:1.7.0")
    implementation("com.squareup:kotlinpoet:1.3.0")
    implementation("org.ow2.asm:asm:7.2")
    implementation("org.ow2.asm:asm-tree:7.2")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
