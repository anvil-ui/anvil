plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.60" apply false
    id("org.jetbrains.kotlin.android") version "1.3.60" apply false
    id("com.android.application") version "3.5.2" apply false
    id("com.android.library") version "3.5.2" apply false
    id("dev.inkremental.module") version "0.7.1" apply false
}

subprojects {
    extra["junit_version"] = "4.12"
    extra["mockito_version"] = "2.23.0"

    /*buildscript {
        dependencies {
            classpath "com.android.tools.build:gradle:3.5.2"
        }

        repositories {
            google()
            jcenter()
        }
    }*/

    repositories {
        mavenLocal()
        google()
        jcenter()
    }

    val GROUP: String by project
    val VERSION_NAME: String by project

    group = GROUP
    version = VERSION_NAME
}

tasks.register("generateAndBuild") {
    dependsOn(
        ":anvil:generateSdkDsl",
        ":anvil:assembleRelease",
        ":anvil-appcompat-v7:generateAppCompatv7Dsl",
        ":anvil-appcompat-v7:assembleRelease",
        ":anvil-gridlayout-v7:generateGridLayoutv7Dsl",
        ":anvil-gridlayout-v7:assembleRelease",
        ":anvil-recyclerview-v7:generateRecyclerViewv7Dsl",
        ":anvil-recyclerview-v7:assembleRelease",
        ":anvil-cardview-v7:generateCardViewv7Dsl",
        ":anvil-cardview-v7:assembleRelease",
        ":anvil-design:generateMaterialDsl",
        ":anvil-design:assembleRelease",
        ":anvil-support-v4:generateSupportCoreUiDsl",
        ":anvil-support-v4:assembleRelease"
    )
}
