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

val cardVersion = "1.0.0"

inkremental {
	androidLibrary("cardview-v7", cardVersion) {
		camelCaseName = "CardViewv7"
        srcPackage = "androidx.cardview"
        modulePackage = "dev.inkremental.dsl.androidx.cardview"
		manualSetterName = "CustomCardViewv7Setter"
		quirks = mutableMapOf(
				"androidx.cardview.widget.CardView" to mapOf(
						"setCardElevation" to false,
						"setMaxCardElevation" to false,
						"setRadius" to false
				)
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	inkremental(project(":anvil", "inkrementalDefSdk17"))

	inkrementalGen("androidx.cardview:cardview:$cardVersion")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
