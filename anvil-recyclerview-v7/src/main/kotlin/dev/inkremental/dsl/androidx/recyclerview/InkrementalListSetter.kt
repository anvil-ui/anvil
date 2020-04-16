package dev.inkremental.dsl.androidx.recyclerview

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.recyclerview.widget.RecyclerViewScope
import dev.inkremental.v

fun list(configure: InkrementalListScope.() -> Unit = {}) =
        v<RecyclerView>(configure.bind(InkrementalListScope))

inline fun horizontalList(crossinline r: InkrementalListScope.() -> Unit) {
    list {
        layout(RecyclerLayoutType.Horizontal())
        r()
    }
}

inline fun verticalList(crossinline r: InkrementalListScope.() -> Unit) {
    list {
        layout(RecyclerLayoutType.Vertical())
        r()
    }
}

inline fun grid(spanCount: Int, crossinline r: InkrementalListScope.() -> Unit) {
    list {
        layout(RecyclerLayoutType.Grid(spanCount))
        r()
    }
}

abstract class InkrementalListScope : RecyclerViewScope() {

    fun <T> items(arg: List<T>, r: (index: Int, item: T) -> Unit): Unit = attr("items", HolderAttr(arg, r))
    fun <T> itemsDiffable(arg: List<T>, diffableCallback: InkrementalDiffCallback<T>, r: (index: Int, item: T) -> Unit): Unit = attr("itemsDiffable", HolderAttrDiffable(arg, diffableCallback, r))
    fun layout(arg: RecyclerLayoutType): Unit = attr("layout", arg)

    companion object : InkrementalListScope() {
        init {
            Inkremental.registerAttributeSetter(RecyclerviewV7Setter)
            Inkremental.registerAttributeSetter(CustomRecyclerViewv7Setter)
            Inkremental.registerAttributeSetter(InkrementalListSetter)
        }
    }
}

object InkrementalListSetter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        "items" -> when {
            v is RecyclerView && value is HolderAttr<*> -> {
                val holder = value as HolderAttr<Any>
                if (v.adapter == null) {
                    v.adapter = RenderableRecyclerViewAdapter.withItems(holder.items) { index, letter ->
                        holder.r(index, letter)
                    }
                }
                if (v.adapter is RenderableRecyclerViewAdapter<*>) {
                    val adapter = v.adapter as RenderableRecyclerViewAdapter<Any>
                    if (adapter.items !== holder.items) {
                        adapter.items = holder.items
                        adapter.notifyDataSetChanged()
                    }
                }
                true
            }
            else -> false
        }
        "itemsDiffable" -> when {
            v is RecyclerView && value is HolderAttrDiffable<*> -> {
                val holder = value as HolderAttrDiffable<Any>
                if (v.adapter == null) {
                    v.adapter = RenderableRecyclerViewAdapter.withItems(holder.items) { index, letter ->
                        holder.r(index, letter)
                    }
                }
                if (v.adapter is RenderableRecyclerViewAdapter<*>) {
                    val adapter = v.adapter as RenderableRecyclerViewAdapter<Any>
                    if (adapter.items !== holder.items) {
                        holder.diffableCallback.newItems = holder.items
                        holder.diffableCallback.oldItems = adapter.items
                        val diffResult = DiffUtil.calculateDiff(holder.diffableCallback)

                        adapter.items = holder.items
                        diffResult.dispatchUpdatesTo(adapter)
                    }
                }
                true
            }
            else -> false
        }
        "layout" -> when {
            v is RecyclerView && value is RecyclerLayoutType -> {
                when (value) {
                    is RecyclerLayoutType.Horizontal -> {
                        v.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.HORIZONTAL, value.reversed)
                    }
                    is RecyclerLayoutType.Vertical -> {
                        v.layoutManager = LinearLayoutManager(v.context, LinearLayoutManager.VERTICAL, value.reversed)
                    }
                    is RecyclerLayoutType.Grid -> {
                        val lm = GridLayoutManager(v.context, value.spanCount, value.orientation, value.reversed)
                        val adapter = v.adapter as RenderableRecyclerViewAdapter<Any>
                        value.spanSizeLookUp?.let { spanSizeLookUp ->
                            lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                                override fun getSpanSize(position: Int): Int {
                                    return spanSizeLookUp(adapter.items[position])
                                }
                            }
                            v.layoutManager = lm
                        }
                    }
                }
                true
            }
            else -> false
        }
        else -> false
    }
}

sealed class RecyclerLayoutType {
    data class Horizontal(val reversed: Boolean = false) : RecyclerLayoutType()
    data class Vertical(val reversed: Boolean = false) : RecyclerLayoutType()
    data class Grid(val spanCount: Int,
                    val orientation: Int = RecyclerView.VERTICAL,
                    val reversed: Boolean = false,
                    val spanSizeLookUp: ((Any) -> Int)? = null) : RecyclerLayoutType()
}

class HolderAttr<T>(val items: List<T>, val r: (index: Int, item: T) -> Unit) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        return other === items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }
}

abstract class InkrementalDiffCallback<T> : DiffUtil.Callback() {
    lateinit var newItems: List<T>
    lateinit var oldItems: List<T>

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

}

class HolderAttrDiffable<T>(val items: List<T>, val diffableCallback: InkrementalDiffCallback<T>, val r: (index: Int, item: T) -> Unit) {
    override fun equals(other: Any?): Boolean {
        other ?: return false
        return other === items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }
}
