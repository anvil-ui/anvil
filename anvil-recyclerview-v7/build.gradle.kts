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
	androidLibrary("recyclerview-v7") {
		camelCaseName = "RecyclerViewv7"
        srcPackage = "androidx.recyclerview"
        modulePackage = "dev.inkremental.dsl.androidx.recyclerview"
		manualSetterName = "CustomRecyclerViewv7Setter"
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	inkremental(project(":anvil", "inkrementalDefSdk15"))

	inkrementalGen("androidx.recyclerview:recyclerview:1.1.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
