@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.recyclerview

import android.view.View
import androidx.recyclerview.widget.*
import dev.inkremental.dsl.androidx.recyclerview.widget.RecyclerViewScope
import dev.inkremental.Inkremental
import dev.inkremental.attr

fun RecyclerViewScope.linearLayoutManager(
    orientation: Int = LinearLayoutManager.VERTICAL,
    reverseLayout: Boolean = false) =
    attr(
        "linearLayoutManager",
        LayoutManagerParams(orientation, reverseLayout)
    )

fun RecyclerViewScope.gridLayoutManager(
    spanCount: Int,
    orientation: Int = LinearLayoutManager.VERTICAL,
    reverseLayout: Boolean = false,
    spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null) =
    attr(
        "gridLayoutManager",
        LayoutManagerParams(orientation, reverseLayout, spanCount, spanSizeLookup)
    )

fun RecyclerViewScope.adapter(arg: RecyclerView.Adapter<out RecyclerView.ViewHolder>?): Unit = attr("adapter", arg)

object CustomRecyclerViewv7Setter : Inkremental.AttributeSetter<Any> {
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
                    value.reverseLayout
                ).also {
                    value.spanSizeLookup?.let { spanSizeLookup ->
                        it.spanSizeLookup = spanSizeLookup
                    }
                }
                true
            }
            else -> false
        }
        "adapter" -> when {
            v is RecyclerView && value is RecyclerView.Adapter<*>? -> {
                v.adapter = value
                true
            }
            else -> false
        }
        else -> false
    }
}

data class LayoutManagerParams(
    val orientation: Int,
    val reverseLayout: Boolean,
    val spanCount: Int = 0,
    val spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null
)
