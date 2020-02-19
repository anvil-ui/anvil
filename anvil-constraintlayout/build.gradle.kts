plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("dev.inkremental.module")
}

android {
    defaultConfig {
        missingDimensionStrategy("dev.inkremental.variant.anvil", "sdk-17")
    }
}

val constraintVersion = "1.1.3"

inkremental {
    androidLibrary("constraintlayout", constraintVersion) {
        srcPackage = "androidx.constraintlayout"
        modulePackage = "dev.inkremental.dsl.androidx.constraintlayout"
    }
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

    //inkrementalGen("androidx.constraintlayout:constraintlayout:$constraintVersion")
    api("androidx.constraintlayout:constraintlayout:$constraintVersion")
    api("androidx.constraintlayout:constraintlayout-solver:$constraintVersion")

    testImplementation("junit:junit:$junit_version")
    testImplementation("org.mockito:mockito-core:$mockito_version")
}
