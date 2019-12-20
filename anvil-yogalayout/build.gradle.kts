plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("dev.inkremental.module")
}

android {
    defaultConfig {
        missingDimensionStrategy("api", "sdk15")
    }
}

inkremental {
    androidLibrary("yogalayout") {
        camelCaseName = "Yoga"
    }
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))

    api("com.facebook.yoga.android:yoga-layout:1.16.0")
    api("com.facebook.soloader:soloader:0.6.1")
    api("androidx.constraintlayout:constraintlayout-solver:1.1.3")

    testImplementation("junit:junit:$junit_version")
    testImplementation("org.mockito:mockito-core:$mockito_version")
}
