package dev.inkremental.meta.model

interface Introspector {
    fun viewModelSequence(quirks: InkrementalQuirks): Sequence<Pair<ViewModel, List<String>>>
}
