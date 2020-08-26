package dev.inkremental.dsl.androidx.viewpager2

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.widget.ViewPager2
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.recyclerview.HolderAttr
import dev.inkremental.dsl.androidx.recyclerview.HolderAttrDiffable
import dev.inkremental.dsl.androidx.recyclerview.InkrementalDiffCallback
import dev.inkremental.dsl.androidx.recyclerview.RenderableRecyclerViewAdapter
import dev.inkremental.dsl.androidx.viewpager2.widget.ViewPager2Scope
import dev.inkremental.v

fun viewPager2Inkremental(configure: InkrementalViewPager2Scope.() -> Unit = {}) =
        v<ViewPager2>(configure.bind(InkrementalViewPager2Scope))

abstract class InkrementalViewPager2Scope : ViewPager2Scope() {

    fun <T> pagerItems(data: List<T>, r: (index: Int, item: T) -> Unit): Unit = attr("pagerItems", HolderAttr(data, r))
    fun <T> pagerItemsDiffable(arg: List<T>, diffableCallback: InkrementalDiffCallback<T>, r: (index: Int, item: T) -> Unit): Unit = attr("pagerItemsDiffable", HolderAttrDiffable(arg, diffableCallback, r))

    companion object : InkrementalViewPager2Scope() {
        init {
            Inkremental.registerAttributeSetter(InkrementalViewPager2Setter)
            Inkremental.registerAttributeSetter(Viewpager2Setter)
            Inkremental.registerAttributeSetter(CustomViewPager2Setter)
        }
    }
}


object InkrementalViewPager2Setter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        "pagerItems" -> when {
            v is ViewPager2 && value is HolderAttr<*> -> {
                val holder = value as HolderAttr<Any>
                if (v.adapter == null) {
                    v.adapter = RenderableRecyclerViewAdapter.withItems(holder.items, true) { index, letter ->
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
        "pagerItemsDiffable" -> when {
            v is ViewPager2 && value is HolderAttrDiffable<*> -> {
                val holder = value as HolderAttrDiffable<Any>
                if (v.adapter == null) {
                    v.adapter = RenderableRecyclerViewAdapter.withItems(holder.items, true) { index, letter ->
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
        else -> false
    }
}