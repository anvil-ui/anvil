package dev.inkremental.gradle

open class InkrementalMetaExtension {
    var type: InkrementalType = InkrementalType.LIBRARY
    var moduleName: String = ""
    var libraries: MutableMap<String, String> = mutableMapOf()
    var camelCaseName: String = ""
    var quirks: InkrementalQuirks = mutableMapOf()
    var dependencies: MutableMap<String, String> = mutableMapOf()
    var manualSetterName: String = ""
}

// Inner Any can be String and Boolean for __viewAlias and Boolean for others
typealias InkrementalQuirks = MutableMap<String, Map<String, Any?>>

enum class InkrementalType { SDK, LIBRARY }

// TODO remove these
val InkrementalMetaExtension.isSdk: Boolean
    get() = type == InkrementalType.SDK

val InkrementalMetaExtension.isSupport: Boolean
    get() = type == InkrementalType.LIBRARY
