package trikita.anvilgen

open class AnvilGenPluginExtension {
    var type = ""
    var moduleName = ""
    var libraries = mutableMapOf<String, String>()
    var camelCaseName = ""
    var quirks = mutableMapOf<String, Map<String, Any>>()
    var dependencies = mutableMapOf<String, String>()
    var superclass = ""
}

val AnvilGenPluginExtension.isSdk: Boolean
    get() = type == "sdk"

val AnvilGenPluginExtension.isSupport: Boolean
    get() = type == "support"
