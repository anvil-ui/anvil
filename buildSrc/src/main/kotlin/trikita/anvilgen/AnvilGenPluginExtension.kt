package trikita.anvilgen

open class AnvilGenPluginExtension {
    var type = ""
    var moduleName = ""
    var libraries = mutableMapOf<String, String>()
    var camelCaseName = ""
    // Inner Any can be String and Boolean for __viewAlias and Boolean for others
    var quirks = mutableMapOf<String, Map<String, Any?>>()
    var dependencies = mutableMapOf<String, String>()
    var manualSetterName = ""
}

val AnvilGenPluginExtension.isSdk: Boolean
    get() = type == "sdk"

val AnvilGenPluginExtension.isSupport: Boolean
    get() = type == "support"
