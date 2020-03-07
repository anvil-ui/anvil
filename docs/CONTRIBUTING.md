# Building

1. You should have Android SDK installed.
2. Make sure you have Android SDK Platforms 15, 19, 21, 28 installed.
3. Provide either environment variable called `ANDROID_HOME`
   or `local.properties` with `sdk.dir` pointing to Android SDK
   root.
4. Run `./generateDsl.sh`

If you have `PATH` set up properly, you can run the following commands
to ensure Android SDK is ready:

```sh
sdkmanager --licenses
# Accept everything needed if you agree with terms
sdkmanager \
    "platforms;android-15" \
    "platforms;android-19" \
    "platforms;android-21" \
    "platforms;android-28" \
    "build-tools;28.0.3" \
    "extras;android;m2repository" \
    "platform-tools" \
    "tools"
```

# Adding new libraries

Every Gradle module provides publications for any number of libraries. Let's walk through this
by an example.

Suppose we want to generate bindings for the library called `androidx.teapot`,
versions `1.2.0` and `1.2.4`. We declare new Inkremental module as follows:

```kotlin
inkremental {
    androidLibrary("androidx-teapot", listOf("1.2.0", "1.2.4") {
        ...
    }
}
```

Let's say that this snippet belongs to the build script of Gradle subproject called `inkremental-teapot`.

This creates two Inkremental modules: `androidxTeapot-1.2.0` and `androidxTeapot-1.2.4`.
They both have own Android flavors with dimension called `dev.inkremental.variant.inkremental-teapot`.

For these modules we would have following source sets:

* `src/main/kotlin`
* `src/androidxTeapot/kotlin`
* `src/androidxTeapot-1/kotlin`
* `src/androidxTeapot-1.2/kotlin`
* `src/androidxTeapot-1.2.4/kotlin`
* `src/androidxTeapot-1.2.5/kotlin`
* `src/gen-androidxTeapot-1.2.4/kotlin`
* `src/gen-androidxTeapot-1.2.5/kotlin`

All but last four are common for these two libraries. This can be helpful if libraries follow
semantic versioning and you have some custom code that can be shared for different versions
with the same major or minor component.

Last two source sets will contain automatically generated code.

We would similarily have following Gradle configurations:

* `inkremental`
* `androidxTeapotInkremental`
* `androidxTeapot-1Inkremental`
* `androidxTeapot-1.2Inkremental`
* `androidxTeapot-1.2.4Inkremental`
* `androidxTeapot-1.2.5Inkremental`

These can be used to declare dependencies on other Inkremental modules.

* `inkrementalGen`
* `androidxTeapotInkrementalGen`
* `androidxTeapot-1InkrementalGen`
* `androidxTeapot-1.2InkrementalGen`
* `androidxTeapot-1.2.4InkrementalGen`
* `androidxTeapot-1.2.5InkrementalGen`

These configurations are used to collect artifacts for code generator processing.

All the custom code like your own attributes and setters that you have can be placed in any
of source sets mentioned above. To run this process on our sample library, you would declare
something along those lines:

```kotlin
dependencies {
    "inkremental"("dev.inkremental.androidx.heater:heater:1.6.1@json")
    "androidxHeater-1.6.1InkrementalGen"("androidx.heater:heater:1.6.1")
}
```

If you'd like to declare dependency on another Inkremental module within the same project,
instead of that you'll have the following:

```kotlin
android {
	defaultConfig {
		missingDimensionStrategy("dev.inkremental.variant.inkremental-heater", "androidxHeater-1.6.1")
	}
}

dependencies {
    implementation(project(":inkremental-heater"))
    "inkremental"(project(":inkremental-heater", "androidxHeater-1.6.1InkrementalDef"))
    "androidxHeater-1.6.1InkrementalGen"("androidx.heater:heater:1.6.1")
}
```

Don't forget to add your project to the root `settings.gradle.kts` and mention it in root `build.gradle.kts`
so that common build and publish tasks would catch it.

# Publishing

Metadata for publishing is mostly declared at root `gradle.properties` file. To publish
artifacts to repository, you should declare following environment variables:

* `BINTRAY_USER`: username of Bintray account.
* `BINTRAY_API_KEY`: API key for Bintray, can be found [in profile settings](https://bintray.com/profile/edit).
* `BINTRAY_REPO` *(optional)*: overrides repo declared in properties file, can be used to test
   publishing with different Maven repository.

# Notes

* To disable code generation on every build, you can run Gradle
  with the following parameter: `-PdontGenerateCodeOnBuild=true`. You
  can also add `dontGenerateCodeOnBuild=true` to your `local.properties`
  as a permanent solution.
  **Please** don't forget to disable this flag when preparing PR.
