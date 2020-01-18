@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun toolbar(configure: ToolbarScope.() -> Unit = {}) = v<Toolbar>(configure.bind(ToolbarScope))
abstract class ToolbarScope : ViewGroupScope() {
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
  fun popupTheme(arg: Int): Unit = attr("popupTheme", arg)
  fun subtitle(arg: CharSequence): Unit = attr("subtitle", arg)
  fun subtitle(arg: Int): Unit = attr("subtitle", arg)
  fun subtitleTextColor(arg: Int): Unit = attr("subtitleTextColor", arg)
  fun title(arg: CharSequence): Unit = attr("title", arg)
  fun title(arg: Int): Unit = attr("title", arg)
  fun titleTextColor(arg: Int): Unit = attr("titleTextColor", arg)
  companion object : ToolbarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
