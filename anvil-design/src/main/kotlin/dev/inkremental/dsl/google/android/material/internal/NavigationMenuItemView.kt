@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.NavigationMenuItemView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun navigationMenuItemView(configure: NavigationMenuItemViewScope.() -> Unit = {}) =
    v<NavigationMenuItemView>(configure.bind(NavigationMenuItemViewScope))
abstract class NavigationMenuItemViewScope : ForegroundLinearLayoutScope() {
  fun horizontalPadding(arg: Int): Unit = attr("horizontalPadding", arg)
  fun needsEmptyIcon(arg: Boolean): Unit = attr("needsEmptyIcon", arg)
  fun textAppearance(arg: Int): Unit = attr("textAppearance", arg)
  companion object : NavigationMenuItemViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
