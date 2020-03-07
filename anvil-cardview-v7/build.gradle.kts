import dev.inkremental.meta.model.DslTransformer.*

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

val cardVersion = "1.0.0"

inkremental {
	androidLibrary("cardview-v7", cardVersion) {
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
	"inkremental"(project(":anvil", "sdk-17InkrementalDef"))

	"inkrementalGen"("androidx.cardview:cardview:$cardVersion")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
