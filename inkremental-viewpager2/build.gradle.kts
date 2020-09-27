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

val viewpager2Version = "1.0.0"

inkremental {
	androidLibrary("viewpager2", viewpager2Version) {
        srcPackage = "androidx.viewpager2"
        modulePackage = "dev.inkremental.dsl.androidx.viewpager2"
		manualSetterName = "CustomViewPager2Setter"
		quirks = mutableMapOf(
				"androidx.viewpager2.widget.ViewPager2" to mapOf(
						"setAdapter" to false) //needs covariant in ViewHolder type, so we handle it
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	implementation(project(":anvil-recyclerview-v7"))
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

	"inkrementalGen"("androidx.viewpager2:viewpager2:$viewpager2Version")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
