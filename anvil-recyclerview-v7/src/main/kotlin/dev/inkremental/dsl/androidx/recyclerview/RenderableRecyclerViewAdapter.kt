package dev.inkremental.dsl.androidx.recyclerview

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import dev.inkremental.Inkremental.Mount
import dev.inkremental.Inkremental.render
import dev.inkremental.RenderableAdapter


abstract class RenderableRecyclerViewAdapter : RecyclerView.Adapter<RenderableRecyclerViewAdapter.MountHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountHolder {
        val root = FrameLayout(parent.context)
        root.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        return MountHolder(root)
    }

    override fun onBindViewHolder(h: MountHolder, position: Int) {
        if (h.mount == null) {
            h.mount = Mount(h.itemView as ViewGroup) { view(h) }
            render(h.mount!!)
        } else {
            render(h.mount!!)
        }
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong() // just a most common implementation
    }

    class MountHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        var mount: Mount? = null
    }

    abstract fun view(holder: RecyclerView.ViewHolder?)

    companion object {
        fun <T> withItems(items: List<T>,
                          r: RenderableAdapter.Item<T>): RenderableRecyclerViewAdapter {
            return object : RenderableRecyclerViewAdapter() {

                override fun getItemCount(): Int {
                    return items.size
                }

                override fun getItemViewType(pos: Int): Int {
                    val item: Any? = items[pos]
                    return item?.javaClass?.hashCode() ?: 0
                }

                override fun view(holder: RecyclerView.ViewHolder?) {
                    holder?.let {
                        val i = it.layoutPosition
                        r.view(i, items[i])
                    }
                }

                init {
                    setHasStableIds(false)
                }
            }
        }
    }
}