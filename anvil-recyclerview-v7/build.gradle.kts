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
	module("recyclerview-v7") {
		camelCaseName = "RecyclerViewv7"
		manualSetterName = "RecyclerViewDslSetter"
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))

	inkremental("androidx.recyclerview:recyclerview:1.1.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
