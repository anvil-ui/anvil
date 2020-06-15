package dev.inkremental.dsl.androidx.core

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import dev.inkremental.Inkremental
import dev.inkremental.RenderableView

class InkrementalPagerAdapter<T> : PagerAdapter() {

    var titlesLambda: (Int) -> String = { "" }

    open var items: List<T>? = null

    var r: ((Int, T) -> Unit)? = null

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = items?.size ?: 1

    override fun getPageTitle(position: Int): CharSequence? = titlesLambda(position)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = object : RenderableView(container.context) {
            override val renderable: () -> Unit = {
                items?.let { items ->
                    val item = items[position]
                    r?.invoke(position, item)
                }
            }
        }
        container.addView(view)
        Inkremental.render(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getItemPosition(obj: Any): Int {
        Inkremental.render(obj as RenderableView)
        return POSITION_UNCHANGED
    }
}