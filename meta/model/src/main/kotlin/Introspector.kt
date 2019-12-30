package dev.inkremental.meta.model

interface Introspector {
    fun provideViewModels(quirks: InkrementalQuirks): List<ViewModel>
}
