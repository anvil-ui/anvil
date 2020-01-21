@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.bottomnavigation

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun bottomNavigationView(configure: BottomNavigationViewScope.() -> Unit = {}) =
    v<BottomNavigationView>(configure.bind(BottomNavigationViewScope))
abstract class BottomNavigationViewScope : FrameLayoutScope() {
  fun itemBackground(arg: Drawable?): Unit = attr("itemBackground", arg)
  fun itemBackgroundResource(arg: Int): Unit = attr("itemBackgroundResource", arg)
  fun itemHorizontalTranslationEnabled(arg: Boolean): Unit =
      attr("itemHorizontalTranslationEnabled", arg)
  fun itemIconSize(arg: Int): Unit = attr("itemIconSize", arg)
  fun itemIconSizeRes(arg: Int): Unit = attr("itemIconSizeRes", arg)
  fun itemIconTintList(arg: ColorStateList?): Unit = attr("itemIconTintList", arg)
  fun itemTextAppearanceActive(arg: Int): Unit = attr("itemTextAppearanceActive", arg)
  fun itemTextAppearanceInactive(arg: Int): Unit = attr("itemTextAppearanceInactive", arg)
  fun itemTextColor(arg: ColorStateList?): Unit = attr("itemTextColor", arg)
  fun labelVisibilityMode(arg: Int): Unit = attr("labelVisibilityMode", arg)
  fun onNavigationItemReselected(arg: ((arg0: MenuItem) -> Unit)?): Unit =
      attr("onNavigationItemReselected", arg)
  fun onNavigationItemSelected(arg: ((arg0: MenuItem) -> Boolean)?): Unit =
      attr("onNavigationItemSelected", arg)
  fun selectedItemId(arg: Int): Unit = attr("selectedItemId", arg)
  companion object : BottomNavigationViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
