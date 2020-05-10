@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.tabs

import android.graphics.drawable.Drawable
import com.google.android.material.tabs.TabLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.ColorState
import dev.inkremental.dsl.android.widget.HorizontalScrollViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun tabLayout(configure: TabLayoutScope.() -> Unit = {}) =
    v<TabLayout>(configure.bind(TabLayoutScope))
abstract class TabLayoutScope : HorizontalScrollViewScope() {
  fun inlineLabel(arg: Boolean): Unit = attr("inlineLabel", arg)
  fun inlineLabelResource(arg: Int): Unit = attr("inlineLabelResource", arg)
  fun selectedTabIndicator(arg: Drawable?): Unit = attr("selectedTabIndicator", arg)
  fun selectedTabIndicator(arg: Int): Unit = attr("selectedTabIndicator", arg)
  fun selectedTabIndicatorColor(arg: Int): Unit = attr("selectedTabIndicatorColor", arg)
  fun selectedTabIndicatorGravity(arg: Int): Unit = attr("selectedTabIndicatorGravity", arg)
  fun tabGravity(arg: Int): Unit = attr("tabGravity", arg)
  fun tabIconTint(arg: ColorState?): Unit = attr("tabIconTint", arg?.value)
  fun tabIconTintResource(arg: Int): Unit = attr("tabIconTintResource", arg)
  fun tabIndicatorFullWidth(arg: Boolean): Unit = attr("tabIndicatorFullWidth", arg)
  fun tabMode(arg: Int): Unit = attr("tabMode", arg)
  fun tabRippleColor(arg: ColorState): Unit = attr("tabRippleColor", arg.value)
  fun tabRippleColorResource(arg: Int): Unit = attr("tabRippleColorResource", arg)
  fun tabTextColors(arg: ColorState?): Unit = attr("tabTextColors", arg?.value)
  fun unboundedRipple(arg: Boolean): Unit = attr("unboundedRipple", arg)
  fun unboundedRippleResource(arg: Int): Unit = attr("unboundedRippleResource", arg)
  companion object : TabLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
