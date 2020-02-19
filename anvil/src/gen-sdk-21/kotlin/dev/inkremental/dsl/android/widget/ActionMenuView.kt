@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.view.MenuItem
import android.widget.ActionMenuView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun actionMenuView(configure: ActionMenuViewScope.() -> Unit = {}) =
    v<ActionMenuView>(configure.bind(ActionMenuViewScope))
abstract class ActionMenuViewScope : LinearLayoutScope() {
  fun onMenuItemClick(arg: ((arg0: MenuItem) -> Boolean)?): Unit = attr("onMenuItemClick", arg)
  fun popupTheme(arg: Int): Unit = attr("popupTheme", arg)
  companion object : ActionMenuViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
