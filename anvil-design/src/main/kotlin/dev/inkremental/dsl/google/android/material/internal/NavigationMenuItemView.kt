@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.NavigationMenuItemView
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun navigationMenuItemView(configure: NavigationMenuItemViewScope.() -> Unit = {}) =
    v<NavigationMenuItemView>(configure.bind(NavigationMenuItemViewScope))
abstract class NavigationMenuItemViewScope : ForegroundLinearLayoutScope() {
  fun horizontalPadding(arg: Int): Unit = attr("horizontalPadding", arg)
  fun needsEmptyIcon(arg: Boolean): Unit = attr("needsEmptyIcon", arg)
  fun textAppearance(arg: Int): Unit = attr("textAppearance", arg)
  companion object : NavigationMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
