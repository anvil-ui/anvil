@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.view.menu

import android.graphics.drawable.Drawable
import androidx.appcompat.view.menu.ListMenuItemView
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun listMenuItemView(configure: ListMenuItemViewScope.() -> Unit = {}) =
    v<ListMenuItemView>(configure.bind(ListMenuItemViewScope))
abstract class ListMenuItemViewScope : LinearLayoutScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun forceShowIcon(arg: Boolean): Unit = attr("forceShowIcon", arg)
  fun groupDividerEnabled(arg: Boolean): Unit = attr("groupDividerEnabled", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  companion object : ListMenuItemViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
