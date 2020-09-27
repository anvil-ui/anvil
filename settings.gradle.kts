pluginManagement {
    repositories {
        mavenLocal()
        maven(url = "https://dl.bintray.com/inkremental/maven")
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
    ":inkremental-viewpager2",
    ":sample"
)
