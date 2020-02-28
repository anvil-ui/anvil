package dev.inkremental.meta.gradle

import com.android.utils.usLocaleDecapitalize
import dev.inkremental.meta.model.*
import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.named
import javax.inject.Inject

open class InkrementalMetaExtension @Inject constructor(objectFactory: ObjectFactory) {
    internal lateinit var project: Project

    val modules = objectFactory.domainObjectContainer(InkrementalMetaModule::class.java) { name ->
        InkrementalMetaModule(project, name)
    }

    val androidModules = objectFactory.domainObjectContainer(InkrementalNamedModule::class.java) {
        InkrementalNamedModule(it)
    }

    fun configureModule(name: String, versionPrefix: String? = null, action: InkrementalMetaModule.() -> Unit) {
        modules.configureEach {
            if(this.name != name && versionPrefix != null && !this.version.startsWith(versionPrefix)) {
                return@configureEach
            }
            apply(action)
        }
    }

    fun androidSdk(action: InkrementalMetaModule.() -> Unit) {
        androidModules.register("sdk#17")
        androidModules.register("sdk#19")
        androidModules.register("sdk#21")
        modules.register("sdk") {
            type = InkrementalType.SDK
            platform = InkrementalPlatform.ANDROID
            camelCaseName = "Sdk"
            action()
        }
    }

    fun androidLibrary(
        name: String,
        versions: List<String>,
        action: InkrementalMetaModule.() -> Unit) =
        versions.forEach { version -> androidLibrary(name, version, action) }

    fun androidLibrary(
        name: String,
        version: String,
        action: InkrementalMetaModule.() -> Unit) {
        val internalName = name.dashesToCamelCase().decapitalize()
        androidModules.register("$internalName#$version")
        modules.register("$name-$version") {
            type = InkrementalType.LIBRARY
            platform = InkrementalPlatform.ANDROID
            camelCaseName = internalName.capitalize()
            this.version = version
            action()
        }
    }
}

data class InkrementalMetaModule(
    private val project: Project,
    var name: String,
    var version: String = "",
    var type: InkrementalType = InkrementalType.LIBRARY,
    var platform: InkrementalPlatform? = null,
    var camelCaseName: String = "",
    var quirks: InkrementalQuirks = mutableMapOf(),
    var transformers : InkrementalTransformers = mutableMapOf(),
    var dependencies: MutableMap<String, String> = mutableMapOf(),
    var srcPackage: String = "",
    var modulePackage: String = "",
    var manualSetterName: String? = null
) {
    fun dependencies(configure: InkrementalDependencyHandler.() -> Unit) =
        InkrementalDependencyHandler(this, project).run(configure)
}

class InkrementalDependencyHandler(
    val parent: InkrementalMetaModule,
    val project: Project
) {
    fun inkremental(depedencyNotation: Any): Dependency? =
        addDependencyByAnyNotation(moduleConfigurationName(parent.dslName), depedencyNotation)

    fun inkrementalGen(depedencyNotation: Any): Dependency? =
        addDependencyByAnyNotation(genConfigurationName(parent.dslName), depedencyNotation)

    private fun addDependencyByAnyNotation(configurationName: String, dependencyNotation: Any): Dependency? =
        project.dependencies.add(configurationName, dependencyNotation)
}

val InkrementalMetaModule.configurationPrefix: String
    get() = camelCaseName.decapitalize()

val InkrementalMetaModule.dslName: String
    get() = "$configurationPrefix-$version"

class InkrementalNamedModule(private val name: String) : Named {
    override fun getName(): String = name
}
