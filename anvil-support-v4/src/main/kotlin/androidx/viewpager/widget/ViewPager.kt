@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.viewpager.widget

import android.graphics.drawable.Drawable
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun viewPager(configure: ViewPagerScope.() -> Unit = {}) =
    v<ViewPager>(configure.bind(ViewPagerScope))
abstract class ViewPagerScope : ViewGroupScope() {
  fun adapter(arg: PagerAdapter): Unit = attr("adapter", arg)
  fun currentItem(arg: Int): Unit = attr("currentItem", arg)
  fun offscreenPageLimit(arg: Int): Unit = attr("offscreenPageLimit", arg)
  fun pageMargin(arg: Int): Unit = attr("pageMargin", arg)
  fun pageMarginDrawable(arg: Drawable?): Unit = attr("pageMarginDrawable", arg)
  fun pageMarginDrawable(arg: Int): Unit = attr("pageMarginDrawable", arg)
  companion object : ViewPagerScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
