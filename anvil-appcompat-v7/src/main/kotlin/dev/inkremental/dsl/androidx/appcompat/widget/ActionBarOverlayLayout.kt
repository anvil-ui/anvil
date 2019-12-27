@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.graphics.drawable.Drawable
import android.view.Window
import androidx.appcompat.widget.ActionBarOverlayLayout
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

fun actionBarOverlayLayout(configure: ActionBarOverlayLayoutScope.() -> Unit = {}) =
    v<ActionBarOverlayLayout>(configure.bind(ActionBarOverlayLayoutScope))
abstract class ActionBarOverlayLayoutScope : ViewGroupScope() {
  fun actionBarHideOffset(arg: Int): Unit = attr("actionBarHideOffset", arg)
  fun actionBarVisibilityCallback(arg: ActionBarOverlayLayout.ActionBarVisibilityCallback): Unit =
      attr("actionBarVisibilityCallback", arg)
  fun hasNonEmbeddedTabs(arg: Boolean): Unit = attr("hasNonEmbeddedTabs", arg)
  fun hideOnContentScrollEnabled(arg: Boolean): Unit = attr("hideOnContentScrollEnabled", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun icon(arg: Int): Unit = attr("icon", arg)
  fun logo(arg: Int): Unit = attr("logo", arg)
  fun overlayMode(arg: Boolean): Unit = attr("overlayMode", arg)
  fun showingForActionMode(arg: Boolean): Unit = attr("showingForActionMode", arg)
  fun uiOptions(arg: Int): Unit = attr("uiOptions", arg)
  fun windowCallback(arg: Window.Callback): Unit = attr("windowCallback", arg)
  fun windowTitle(arg: CharSequence): Unit = attr("windowTitle", arg)
  companion object : ActionBarOverlayLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
