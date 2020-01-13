@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun toolbar(configure: ToolbarScope.() -> Unit = {}) = v<Toolbar>(configure.bind(ToolbarScope))
abstract class ToolbarScope : ViewGroupScope() {
  fun collapseContentDescription(arg: CharSequence?): Unit = attr("collapseContentDescription", arg)
  fun collapseContentDescription(arg: Int): Unit = attr("collapseContentDescription", arg)
  fun collapseIcon(arg: Drawable?): Unit = attr("collapseIcon", arg)
  fun collapseIcon(arg: Int): Unit = attr("collapseIcon", arg)
  fun collapsible(arg: Boolean): Unit = attr("collapsible", arg)
  fun contentInsetEndWithActions(arg: Int): Unit = attr("contentInsetEndWithActions", arg)
  fun contentInsetStartWithNavigation(arg: Int): Unit = attr("contentInsetStartWithNavigation", arg)
  fun logo(arg: Drawable): Unit = attr("logo", arg)
  fun logo(arg: Int): Unit = attr("logo", arg)
  fun logoDescription(arg: CharSequence): Unit = attr("logoDescription", arg)
  fun logoDescription(arg: Int): Unit = attr("logoDescription", arg)
  fun navigationContentDescription(arg: CharSequence?): Unit = attr("navigationContentDescription",
      arg)
  fun navigationContentDescription(arg: Int): Unit = attr("navigationContentDescription", arg)
  fun navigationIcon(arg: Drawable?): Unit = attr("navigationIcon", arg)
  fun navigationIcon(arg: Int): Unit = attr("navigationIcon", arg)
  fun navigationOnClickListener(arg: View.OnClickListener): Unit = attr("navigationOnClickListener",
      arg)
  fun onMenuItemClick(arg: ((arg0: MenuItem) -> Boolean)?): Unit = attr("onMenuItemClick", arg)
  fun overflowIcon(arg: Drawable?): Unit = attr("overflowIcon", arg)
  fun popupTheme(arg: Int): Unit = attr("popupTheme", arg)
  fun subtitle(arg: CharSequence): Unit = attr("subtitle", arg)
  fun subtitle(arg: Int): Unit = attr("subtitle", arg)
  fun subtitleTextColor(arg: ColorStateList): Unit = attr("subtitleTextColor", arg)
  fun subtitleTextColor(arg: Int): Unit = attr("subtitleTextColor", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  fun title(arg: Int): Unit = attr("title", arg)
  fun titleMarginBottom(arg: Int): Unit = attr("titleMarginBottom", arg)
  fun titleMarginEnd(arg: Int): Unit = attr("titleMarginEnd", arg)
  fun titleMarginStart(arg: Int): Unit = attr("titleMarginStart", arg)
  fun titleMarginTop(arg: Int): Unit = attr("titleMarginTop", arg)
  fun titleTextColor(arg: ColorStateList): Unit = attr("titleTextColor", arg)
  fun titleTextColor(arg: Int): Unit = attr("titleTextColor", arg)
  companion object : ToolbarScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
