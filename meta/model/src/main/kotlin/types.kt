package dev.inkremental.meta.model

import kotlinx.serialization.Serializable

// Inner Any can be String and Boolean for __viewAlias and Boolean for others
typealias InkrementalQuirks = MutableMap<String, Map<String, Any?>>
typealias InkrementalTransformers = Map<String, Map<String, List<DslTransformer>>>

enum class InkrementalType { SDK, LIBRARY }
enum class InkrementalPlatform { ANDROID, IOS }

//TODO: convert to sealed class when issue with serialisation will be resolved
enum class DslTransformer {
    FLoatPixelToDipSizeTransformer,
    RequiresApi21Transformer
}