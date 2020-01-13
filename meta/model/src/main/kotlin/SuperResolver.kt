package dev.inkremental.meta.model

class SuperResolver {
    private val superRequests: MutableList<Pair<ViewModel, List<String>>> = mutableListOf()
    private val superViews: MutableMap<String, ViewModel> = mutableMapOf()

    fun processView(view: ViewModel) {
        superViews[view.plainType.canonicalName] = view

        when(val superType = view.superType) {
            null -> return // this is root view
            is ViewModelSupertype.Resolved -> error("View supertypes should be unresolved at this point")
            is ViewModelSupertype.Unresolved -> {
                val references = superType.references
                if(!tryResolve(view, references)) {
                    superRequests += view to superType.references
                }
            }
        }
    }

    fun finalize() {
        superRequests.forEach { (view, references) ->
            if(!tryResolve(view, references)) {
                error("None of super candidates found for ${view.plainType}. Candidates: ${references.joinToString()}")
            }
        }
    }

    private fun tryResolve(view: ViewModel, references: List<String>): Boolean =
        references.asSequence()
            .mapNotNull { superViews[it] }
            .firstOrNull()
            ?.also { view.superType = ViewModelSupertype.Resolved(it) } != null
}
