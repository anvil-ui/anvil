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

val recyclerVersion = "1.1.0"

inkremental {
	androidLibrary("recyclerview-v7", recyclerVersion) {
		camelCaseName = "RecyclerViewv7"
        srcPackage = "androidx.recyclerview"
        modulePackage = "dev.inkremental.dsl.androidx.recyclerview"
		manualSetterName = "CustomRecyclerViewv7Setter"
		quirks = mutableMapOf(
				"androidx.recyclerview.widget.RecyclerView" to mapOf(
						"setAdapter" to false) //needs covariant in ViewHolder type, so we handle it
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	inkremental(project(":anvil", "inkrementalDefSdk17"))

	inkrementalGen("androidx.recyclerview:recyclerview:$recyclerVersion")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
