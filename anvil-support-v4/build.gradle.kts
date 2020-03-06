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

val coreVersion = "1.1.0"

inkremental {
	androidLibrary("support-core-ui", coreVersion) {
        srcPackage = "androidx.core"
        modulePackage = "dev.inkremental.dsl.androidx.core"
		quirks = mutableMapOf(
			// Deprecated view; framework nas android.widget.Space since API 14
			"androidx.legacy.widget.Space" to mapOf(
				"__viewAlias" to false
			),
			"androidx.core.widget.NestedScrollView" to mapOf(
				"setOnScrollChangeListener" to false
			)
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

	"inkrementalGen"("androidx.coordinatorlayout:coordinatorlayout:1.0.0")
	"inkrementalGen"("androidx.core:core:$coreVersion")
	"inkrementalGen"("androidx.drawerlayout:drawerlayout:1.0.0")
	"inkrementalGen"("androidx.legacy:legacy-support-core-ui:1.0.0")
	"inkrementalGen"("androidx.slidingpanelayout:slidingpanelayout:1.0.0")
	"inkrementalGen"("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
	"inkrementalGen"("androidx.viewpager:viewpager:1.0.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}


