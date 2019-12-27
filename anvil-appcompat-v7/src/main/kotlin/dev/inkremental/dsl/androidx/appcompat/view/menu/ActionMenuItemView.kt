@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.view.menu

import android.graphics.drawable.Drawable
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuBuilder
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatTextViewScope
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun actionMenuItemView(configure: ActionMenuItemViewScope.() -> Unit = {}) =
    v<ActionMenuItemView>(configure.bind(ActionMenuItemViewScope))
abstract class ActionMenuItemViewScope : AppCompatTextViewScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun expandedFormat(arg: Boolean): Unit = attr("expandedFormat", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun itemInvoker(arg: MenuBuilder.ItemInvoker): Unit = attr("itemInvoker", arg)
  fun popupCallback(arg: ActionMenuItemView.PopupCallback): Unit = attr("popupCallback", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  companion object : ActionMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
