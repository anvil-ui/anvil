@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.ScrollingTabContainerView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun actionBarContainer(configure: ActionBarContainerScope.() -> Unit = {}) =
    v<ActionBarContainer>(configure.bind(ActionBarContainerScope))
abstract class ActionBarContainerScope : FrameLayoutScope() {
  fun primaryBackground(arg: Drawable): Unit = attr("primaryBackground", arg)
  fun splitBackground(arg: Drawable): Unit = attr("splitBackground", arg)
  fun stackedBackground(arg: Drawable): Unit = attr("stackedBackground", arg)
  fun tabContainer(arg: ScrollingTabContainerView): Unit = attr("tabContainer", arg)
  fun transitioning(arg: Boolean): Unit = attr("transitioning", arg)
  companion object : ActionBarContainerScope() {
    init {
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
