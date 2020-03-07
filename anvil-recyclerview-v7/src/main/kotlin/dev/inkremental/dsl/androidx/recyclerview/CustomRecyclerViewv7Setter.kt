@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.recyclerview

import android.view.View
import androidx.recyclerview.widget.*
import dev.inkremental.dsl.androidx.recyclerview.widget.RecyclerViewScope
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.v

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
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
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

fun list(configure: InkrementalListScope.() -> Unit = {}) =
        v<InkrementalRecyclerView>(configure.bind(InkrementalListScope))

inline fun hList(crossinline r : () -> Unit) {
    list {
        layout(RecyclerLayoutType.Horizontal())
        r.invoke()
    }
}

inline fun vList(crossinline r : () -> Unit) {
    list {
        layout(RecyclerLayoutType.Vertical())
        r.invoke()
    }
}

inline fun grid(spanCount: Int, crossinline r : () -> Unit) {
    list {
        layout(RecyclerLayoutType.Grid(spanCount))
        r.invoke()
    }
}

abstract class InkrementalListScope : RecyclerViewScope() {

    fun items(arg: List<Any>, r: (index: Int, item: Any) -> Unit): Unit = attr("items", HolderAttr(arg, r))
    fun layout(arg: RecyclerLayoutType): Unit = attr("layout", arg)

    companion object : InkrementalListScope() {
        init {
            Inkremental.registerAttributeSetter(RecyclerViewv7Setter)
            Inkremental.registerAttributeSetter(CustomRecyclerViewv7Setter)
            Inkremental.registerAttributeSetter(InkrementalListSetter)
        }
    }
}

sealed class RecyclerLayoutType {
    data class Horizontal(val reversed: Boolean = false) : RecyclerLayoutType()
    data class Vertical(val reversed: Boolean = false) : RecyclerLayoutType()
    data class Grid(val spanCount: Int, val orientation: Int =RecyclerView.VERTICAL , val reversed: Boolean = false) : RecyclerLayoutType()
}

class HolderAttr(val items: List<Any>, val r: (index: Int, item: Any) -> Unit)

object InkrementalListSetter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        "items" -> when {
            v is InkrementalRecyclerView && value is HolderAttr -> {
                if (v.localAdapter == null) {
                    v.items = value.items
                    v.localAdapter = InkrementalRecyclerViewAdapter(value.items) { index, letter ->
                        value.r(index, letter)
                    }
                    v.adapter = v.localAdapter
                }
                if (v.localAdapter?.items !== value.items) {
                    v.items = value.items
                    v.localAdapter?.items = value.items
                    v.adapter?.notifyDataSetChanged()
                }
                true
            }
            else -> false
        }
        "layout" -> when {
            v is InkrementalRecyclerView && value is RecyclerLayoutType -> {
                when (value) {
                    is RecyclerLayoutType.Horizontal -> {
                        v.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, value.reversed)
                    }
                    is RecyclerLayoutType.Vertical -> {
                        v.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, value.reversed)
                    }
                    is RecyclerLayoutType.Grid -> {
                        v.layoutManager = GridLayoutManager(v.context, value.spanCount, value.orientation, value.reversed)
                    }
                }
                true
            }
            else -> false
        }
        else -> false
    }
}