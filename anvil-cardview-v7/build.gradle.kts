import dev.inkremental.meta.model.DslTransformer.*

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

inkremental {
	androidLibrary("cardview-v7") {
		camelCaseName = "CardViewv7"
        srcPackage = "androidx.cardview"
        modulePackage = "dev.inkremental.dsl.androidx.cardview"
		transformers = mapOf(
				"androidx.cardview.widget.CardView" to mapOf(
						"setCardElevation" to listOf(FloatPixelToDipSizeTransformer),
						"setMaxCardElevation" to listOf(FloatPixelToDipSizeTransformer),
						"setRadius" to listOf(FloatPixelToDipSizeTransformer)
				)
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	inkremental(project(":anvil", "inkrementalDefSdk17"))

	inkrementalGen("androidx.cardview:cardview:1.0.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
