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
	androidLibrary("gridlayout-v7") {
		camelCaseName = "GridLayoutv7"
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))

	inkremental("androidx.gridlayout:gridlayout:1.0.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
