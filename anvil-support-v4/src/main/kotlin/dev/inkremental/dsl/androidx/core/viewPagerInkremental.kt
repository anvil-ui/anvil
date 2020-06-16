package dev.inkremental.dsl.androidx.core

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPagerScope
import dev.inkremental.*

fun viewPagerInkremental(configure: InkrementalViewPagerScope.() -> Unit = {}) =
        v<ViewPager>(configure.bind(InkrementalViewPagerScope))

abstract class InkrementalViewPagerScope : ViewPagerScope() {

    fun <T> pagerItems(data: List<T>, r: (index: Int, item: T) -> Unit): Unit = attr("pagerItems", ViewPagerHolderAttr(data, r))
    fun titles(titlesLambda: (Int) -> String): Unit = attr("titles", ViewPagerTitlesAttr(titlesLambda))

    companion object : InkrementalViewPagerScope() {
        init {
            Inkremental.registerAttributeSetter(InkrementalViewPagerSetter)
            Inkremental.registerAttributeSetter(SupportCoreUiSetter)
            Inkremental.registerAttributeSetter(CustomSupportV4Setter)
        }
    }
}

