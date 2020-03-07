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

val gridVersion = "1.0.0"

inkremental {
	androidLibrary("gridlayout-v7", gridVersion) {
        srcPackage = "androidx.gridlayout"
        modulePackage = "dev.inkremental.dsl.androidx.gridlayout"
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

	"inkrementalGen"("androidx.gridlayout:gridlayout:$gridVersion")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
