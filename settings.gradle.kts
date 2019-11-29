pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "inkremental-root"

includeBuild("meta")

include(
    ":anvil",
    ":anvil-support-v4",
    ":anvil-appcompat-v7",
    ":anvil-cardview-v7",
    ":anvil-gridlayout-v7",
    ":anvil-design",
    ":anvil-recyclerview-v7",
    ":anvil-constraintlayout",
    ":anvil-yogalayout",
    ":sample"
)
