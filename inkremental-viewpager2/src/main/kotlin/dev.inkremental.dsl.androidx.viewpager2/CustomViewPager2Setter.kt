package dev.inkremental.dsl.androidx.viewpager2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.inkremental.Inkremental
import dev.inkremental.dsl.androidx.viewpager2.widget.ViewPager2Scope


fun ViewPager2Scope.adapter(arg: RecyclerView.Adapter<out RecyclerView.ViewHolder>?): Unit = dev.inkremental.attr("adapter", arg)

object CustomViewPager2Setter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
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