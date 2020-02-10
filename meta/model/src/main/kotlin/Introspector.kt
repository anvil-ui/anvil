package dev.inkremental.meta.model

interface Introspector {
    fun provideViewModels(quirks: InkrementalQuirks, transformers: InkrementalTransformers): List<ViewModel>
}
