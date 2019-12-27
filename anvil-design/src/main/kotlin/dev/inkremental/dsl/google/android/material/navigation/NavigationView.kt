@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.navigation

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.internal.ScrimInsetsFrameLayoutScope
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun navigationView(configure: NavigationViewScope.() -> Unit = {}) =
    v<NavigationView>(configure.bind(NavigationViewScope))
abstract class NavigationViewScope : ScrimInsetsFrameLayoutScope() {
  fun checkedItem(arg: MenuItem): Unit = attr("checkedItem", arg)
  fun checkedItem(arg: Int): Unit = attr("checkedItem", arg)
  fun itemBackground(arg: Drawable?): Unit = attr("itemBackground", arg)
  fun itemBackgroundResource(arg: Int): Unit = attr("itemBackgroundResource", arg)
  fun itemHorizontalPadding(arg: Int): Unit = attr("itemHorizontalPadding", arg)
  fun itemHorizontalPaddingResource(arg: Int): Unit = attr("itemHorizontalPaddingResource", arg)
  fun itemIconPadding(arg: Int): Unit = attr("itemIconPadding", arg)
  fun itemIconPaddingResource(arg: Int): Unit = attr("itemIconPaddingResource", arg)
  fun itemIconTintList(arg: ColorStateList?): Unit = attr("itemIconTintList", arg)
  fun itemTextAppearance(arg: Int): Unit = attr("itemTextAppearance", arg)
  fun itemTextColor(arg: ColorStateList?): Unit = attr("itemTextColor", arg)
  fun navigationItemSelectedListener(arg: NavigationView.OnNavigationItemSelectedListener?): Unit =
      attr("navigationItemSelectedListener", arg)
  companion object : NavigationViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
