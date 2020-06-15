package dev.inkremental.dsl.androidx.core

import android.view.View
import androidx.viewpager.widget.ViewPager
import dev.inkremental.Inkremental

object InkrementalViewPagerSetter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        "pagerItems" -> when {
            v is ViewPager && value is ViewPagerHolderAttr<*> -> {
                val holder = value as ViewPagerHolderAttr<Any>
                if (v.adapter == null) {
                    val adapter = InkrementalPagerAdapter<Any>()
                    adapter.items = value.items
                    adapter.r = value.r
                    v.adapter = adapter
                }
                if (v.adapter is InkrementalPagerAdapter<*>) {
                    val adapter = v.adapter as InkrementalPagerAdapter<Any>
                    adapter.r = value.r
                    if (adapter.items !== holder.items) {
                        adapter.items = holder.items
                        adapter.notifyDataSetChanged()
                    }
                }
                true
            }
            else -> false
        }
        "titles" -> when {
            v is ViewPager && value is ViewPagerTitlesAttr -> {
                if (v.adapter == null) {
                    v.adapter = InkrementalPagerAdapter<Any>()
                }
                if (v.adapter is InkrementalPagerAdapter<*>) {
                    val adapter = v.adapter as InkrementalPagerAdapter<Any>
                    adapter.titlesLambda = value.lambda
                }
                true
            }
            else -> false
        }
        else -> false
    }
}

class ViewPagerHolderAttr<T>(val items: List<T>, val r: (position: Int, item: T) -> Unit) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        return other === items
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }
}

class ViewPagerTitlesAttr(val lambda: (position: Int) -> String) {

    override fun equals(other: Any?): Boolean {
        other ?: return false
        return true
    }

    override fun hashCode(): Int = 0
}