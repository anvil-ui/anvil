package trikita.anvilgen

open class AnvilGenPluginExtension {
    var type = ""
    var libraryName = ""
    var camelCaseName = ""
    var version = ""
    var quirks = mutableMapOf<String, Map<String, Any>>()
    var dependencies = mutableMapOf<String, String?>()
    var superclass = ""
}