@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun frameLayout(configure: FrameLayoutScope.() -> Unit = {}) =
    v<FrameLayout>(configure.bind(FrameLayoutScope))
abstract class FrameLayoutScope : ViewGroupScope() {
  fun foreground(arg: Drawable): Unit = attr("foreground", arg)
  fun foregroundGravity(arg: Int): Unit = attr("foregroundGravity", arg)
  fun measureAllChildren(arg: Boolean): Unit = attr("measureAllChildren", arg)
  companion object : FrameLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
