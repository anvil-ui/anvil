package trikita.anvil

import android.view.View
import androidx.recyclerview.widget.*
import trikita.anvil.*

fun RecyclerViewScope.linearLayoutManager(
    orientation: Int = LinearLayoutManager.VERTICAL,
    reverseLayout: Boolean = false) =
    attr("linearLayoutManager", LayoutManagerParams(0, orientation, reverseLayout, null))

fun RecyclerViewScope.gridLayoutManager(
    spanCount: Int,
    orientation: Int = LinearLayoutManager.VERTICAL,
    reverseLayout: Boolean = false,
    spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null) =
    attr("linearLayoutManager", LayoutManagerParams(spanCount, orientation, reverseLayout, null))

object RecyclerViewDslSetter : Anvil.AttributeSetter<Any?> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "linearLayoutManager" -> when {
            v is RecyclerView && value is LayoutManagerParams -> {
                v.layoutManager = LinearLayoutManager(v.context, value.orientation, value.reverseLayout)
                true
            }
            else -> false
        }
        "gridLayoutManager" -> when {
            v is RecyclerView && value is LayoutManagerParams -> {
                v.layoutManager = GridLayoutManager(
                    v.context,
                    value.spanCount,
                    value.orientation,
                    value.reverseLayout).also {
                    it.spanSizeLookup = value.spanSizeLookup
                }
                true
            }
            else -> false
        }
        else -> false
    }
}

data class LayoutManagerParams(
    val spanCount: Int,
    val orientation: Int,
    val reverseLayout: Boolean,
    val spanSizeLookup: GridLayoutManager.SpanSizeLookup?
)
