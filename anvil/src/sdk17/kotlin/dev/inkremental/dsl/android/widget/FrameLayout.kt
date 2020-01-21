@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun frameLayout(configure: FrameLayoutScope.() -> Unit = {}) =
    v<FrameLayout>(configure.bind(FrameLayoutScope))
abstract class FrameLayoutScope : ViewGroupScope() {
  fun foreground(arg: Drawable): Unit = attr("foreground", arg)
  fun foregroundGravity(arg: Int): Unit = attr("foregroundGravity", arg)
  fun measureAllChildren(arg: Boolean): Unit = attr("measureAllChildren", arg)
  companion object : FrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
