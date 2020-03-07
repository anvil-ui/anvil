package dev.inkremental.dsl.androidx.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class InkrementalRecyclerView(context : Context) : RecyclerView(context) {

    var localAdapter : InkrementalRecyclerViewAdapter<Any>? = null
    var items : List<*>? = null

}

class InkrementalRecyclerViewAdapter<T>(var items: List<T>,
                                        val r: (index: Int, item: T) -> Unit) : RenderableRecyclerViewAdapter() {
    override fun view(holder: RecyclerView.ViewHolder?) {
        holder?.let {
            val i = it.layoutPosition
            r(i, items[i])
        }
    }

    override fun getItemCount(): Int = items.size

}

