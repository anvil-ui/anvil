@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.ScrollingTabContainerView
import dev.inkremental.dsl.android.widget.HorizontalScrollViewScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun scrollingTabContainerView(configure: ScrollingTabContainerViewScope.() -> Unit = {}) =
    v<ScrollingTabContainerView>(configure.bind(ScrollingTabContainerViewScope))
abstract class ScrollingTabContainerViewScope : HorizontalScrollViewScope() {
  fun allowCollapse(arg: Boolean): Unit = attr("allowCollapse", arg)
  fun contentHeight(arg: Int): Unit = attr("contentHeight", arg)
  fun tabSelected(arg: Int): Unit = attr("tabSelected", arg)
  companion object : ScrollingTabContainerViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
