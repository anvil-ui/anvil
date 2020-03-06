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

val yogaVersion = "1.16.0"

inkremental {
    androidLibrary("yogalayout", yogaVersion) {
        srcPackage = "com.facebook.yoga"
        modulePackage = "dev.inkremental.dsl.yoga"
    }
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

    api("com.facebook.yoga.android:yoga-layout:$yogaVersion")
    api("com.facebook.soloader:soloader:0.6.1")
    api("androidx.constraintlayout:constraintlayout-solver:1.1.3")

    testImplementation("junit:junit:$junit_version")
    testImplementation("org.mockito:mockito-core:$mockito_version")
}
