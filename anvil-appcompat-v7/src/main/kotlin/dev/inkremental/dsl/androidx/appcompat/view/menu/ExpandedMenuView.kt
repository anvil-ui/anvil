@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.view.menu

import androidx.appcompat.view.menu.ExpandedMenuView
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.ListViewScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun expandedMenuView(configure: ExpandedMenuViewScope.() -> Unit = {}) =
    v<ExpandedMenuView>(configure.bind(ExpandedMenuViewScope))
abstract class ExpandedMenuViewScope : ListViewScope() {
  companion object : ExpandedMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
