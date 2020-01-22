package dev.inkremental

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import dev.inkremental.Inkremental.Mount

abstract class RenderableAdapter : BaseAdapter() {
    private var currentPosition = -1

    interface Item<T> {
        fun view(index: Int, item: T)
    }

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            currentPosition = pos
            val vg = FrameLayout(parent.context)
            val m = Mount(vg) { view(currentPosition) }
            Inkremental.render(m)
            vg.tag = m
            v = vg
        } else {
            val m = v.tag as Mount
            currentPosition = pos
            Inkremental.render(m)
        }
        return v
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong() // just a most common implementation
    }

    abstract fun view(index: Int)

    companion object {
        fun <T> withItems(items: List<T>, r: Item<T>): RenderableAdapter {
            return object : RenderableAdapter() {
                override fun getCount(): Int {
                    return items.size
                }

                override fun getItem(pos: Int): T {
                    return items[pos]
                }

                override fun view(pos: Int) {
                    r.view(pos, getItem(pos))
                }
            }
        }
    }
}