@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.viewpager2.widget

import androidx.viewpager2.widget.ViewPager2
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.viewpager2.CustomViewPager2Setter
import dev.inkremental.dsl.androidx.viewpager2.Viewpager2Setter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun viewPager2(configure: ViewPager2Scope.() -> Unit = {}) =
    v<ViewPager2>(configure.bind(ViewPager2Scope))
abstract class ViewPager2Scope : ViewGroupScope() {
  fun currentItem(arg: Int): Unit = attr("currentItem", arg)
  fun offscreenPageLimit(arg: Int): Unit = attr("offscreenPageLimit", arg)
  fun orientation(arg: Int): Unit = attr("orientation", arg)
  fun pageTransformer(arg: ViewPager2.PageTransformer?): Unit = attr("pageTransformer", arg)
  fun userInputEnabled(arg: Boolean): Unit = attr("userInputEnabled", arg)
  companion object : ViewPager2Scope() {
    init {
      Inkremental.registerAttributeSetter(Viewpager2Setter)
      Inkremental.registerAttributeSetter(CustomViewPager2Setter)
    }
  }
}
