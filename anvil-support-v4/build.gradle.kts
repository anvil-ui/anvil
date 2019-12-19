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
	module("support-core-ui") {
		camelCaseName = "SupportCoreUi"
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

	inkremental("androidx.coordinatorlayout:coordinatorlayout:1.0.0")
	inkremental("androidx.core:core:1.1.0")
	inkremental("androidx.drawerlayout:drawerlayout:1.0.0")
	inkremental("androidx.legacy:legacy-support-core-ui:1.0.0")
	inkremental("androidx.slidingpanelayout:slidingpanelayout:1.0.0")
	inkremental("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
	inkremental("androidx.viewpager:viewpager:1.0.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}


