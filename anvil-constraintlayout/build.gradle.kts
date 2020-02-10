plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("dev.inkremental.module")
}

android {
    defaultConfig {
        missingDimensionStrategy("api", "sdk17")
    }
}

val constraintVersion = "1.1.3"

inkremental {
    androidLibrary("constraintlayout", constraintVersion) {
        camelCaseName = "Constraint"
        srcPackage = "androidx.constraintlayout"
        modulePackage = "dev.inkremental.dsl.androidx.constraintlayout"
    }
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	inkremental(project(":anvil", "inkrementalDefSdk17"))

    //inkrementalGen("androidx.constraintlayout:constraintlayout:$constraintVersion")
    api("androidx.constraintlayout:constraintlayout:$constraintVersion")
    api("androidx.constraintlayout:constraintlayout-solver:$constraintVersion")

    testImplementation("junit:junit:$junit_version")
    testImplementation("org.mockito:mockito-core:$mockito_version")
}
