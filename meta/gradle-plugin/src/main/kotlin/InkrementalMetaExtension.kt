package dev.inkremental.meta.gradle

import dev.inkremental.meta.model.*
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

open class InkrementalMetaExtension @Inject constructor(objectFactory: ObjectFactory) {
    val modules = objectFactory.domainObjectContainer(InkrementalMetaModule::class.java) { name ->
        InkrementalMetaModule(name)
    }
    fun module(name: String, configure: InkrementalMetaModule.() -> Unit) = modules.register(name, configure)

    fun androidSdk(action: InkrementalMetaModule.() -> Unit) =
        modules.register("sdk") {
            type = InkrementalType.SDK
            platform = InkrementalPlatform.ANDROID
            action()
        }

    fun androidLibrary(
        name: String,
        action: InkrementalMetaModule.() -> Unit) =
        modules.register(name) {
            type = InkrementalType.LIBRARY
            platform = InkrementalPlatform.ANDROID
            action()
        }

    fun iosLibrary(
        name: String,
        action: InkrementalMetaModule.() -> Unit) =
        modules.register(name) {
            type = InkrementalType.LIBRARY
            platform = InkrementalPlatform.IOS
            action()
        }
}

class InkrementalMetaModule(
    var name: String = "",
    var type: InkrementalType = InkrementalType.LIBRARY,
    var platform: InkrementalPlatform? = null,
    var camelCaseName: String = "",
    var quirks: InkrementalQuirks = mutableMapOf(),
    var dependencies: MutableMap<String, String> = mutableMapOf(),
    var manualSetterName: String? = null
)
