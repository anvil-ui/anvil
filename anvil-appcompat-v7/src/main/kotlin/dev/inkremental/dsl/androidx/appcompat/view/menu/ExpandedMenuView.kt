@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.view.menu

import androidx.appcompat.view.menu.ExpandedMenuView
import dev.inkremental.Inkremental
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
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
