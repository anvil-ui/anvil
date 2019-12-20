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
	androidLibrary("material") {
		camelCaseName = "Material"
		manualSetterName = "MaterialDslSetter"
		quirks = mutableMapOf(
			// Depends on coordinatorlayout which is not covered yet
			"com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout" to mapOf(
				"__viewAlias" to false
			),

			// Access limited to the same library
			"com.google.android.material.bottomnavigation.BottomNavigationItemView" to mapOf(
				"setCheckable:kotlin.Boolean" to false,
				"setChecked:kotlin.Boolean" to false,
				"setIcon:android.graphics.drawable.Drawable" to false,
				"setIconSize:kotlin.Int" to false,
				"setIconTintList:android.content.res.ColorStateList" to false,
				"setItemBackground:android.graphics.drawable.Drawable" to false,
				"setItemBackground:kotlin.Int" to false,
				"setItemPosition:kotlin.Int" to false,
				"setLabelVisibilityMode:kotlin.Int" to false,
				"setShifting:kotlin.Boolean" to false,
				"setTextAppearanceActive:kotlin.Int" to false,
				"setTextAppearanceInactive:kotlin.Int" to false,
				"setTextColor:android.content.res.ColorStateList" to false,
				"setTitle:kotlin.CharSequence" to false
			),
			"com.google.android.material.bottomnavigation.BottomNavigationMenuView" to mapOf(
				"setIconTintList:android.content.res.ColorStateList" to false,
				"setItemBackground:android.graphics.drawable.Drawable" to false,
				"setItemBackgroundRes:kotlin.Int" to false,
				"setItemHorizontalTranslationEnabled:kotlin.Boolean" to false,
				"setItemIconSize:kotlin.Int" to false,
				"setItemTextAppearanceActive:kotlin.Int" to false,
				"setItemTextAppearanceInactive:kotlin.Int" to false,
				"setItemTextColor:android.content.res.ColorStateList" to false,
				"setLabelVisibilityMode:kotlin.Int" to false,
				"setPresenter:com.google.android.material.bottomnavigation.BottomNavigationPresenter" to false
			),
			"com.google.android.material.internal.CheckableImageButton" to mapOf(
				"setChecked:kotlin.Boolean" to false
			),
			"com.google.android.material.internal.NavigationMenuItemView" to mapOf(
				"setCheckable:kotlin.Boolean" to false,
				"setChecked:kotlin.Boolean" to false,
				"setIcon:android.graphics.drawable.Drawable" to false,
				"setIconPadding:kotlin.Int" to false,
				"setTextColor:android.content.res.ColorStateList" to false,
				"setTitle:kotlin.CharSequence" to false
			)
		)
	}
}

dependencies {
	val junit_version: String by project.extra
	val mockito_version: String by project.extra

	implementation(project(":anvil"))
	implementation(project(":anvil-appcompat-v7"))
	implementation(project(":anvil-cardview-v7"))
	implementation(project(":anvil-recyclerview-v7"))

	inkremental("com.google.android.material:material:1.0.0")

	testImplementation("junit:junit:$junit_version")
	testImplementation("org.mockito:mockito-core:$mockito_version")
}
