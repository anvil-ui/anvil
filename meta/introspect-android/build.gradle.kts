plugins {
    kotlin("jvm")
}

kotlin {
    dependencies {
        api(project(":model"))
        implementation(kotlin("stdlib"))
        testImplementation(kotlin("stdlib-jdk8"))
    }
}
