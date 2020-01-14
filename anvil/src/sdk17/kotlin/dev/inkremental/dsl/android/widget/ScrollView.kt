@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ScrollView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun scrollView(configure: ScrollViewScope.() -> Unit = {}) =
    v<ScrollView>(configure.bind(ScrollViewScope))
abstract class ScrollViewScope : FrameLayoutScope() {
  fun fillViewport(arg: Boolean): Unit = attr("fillViewport", arg)
  fun smoothScrollingEnabled(arg: Boolean): Unit = attr("smoothScrollingEnabled", arg)
  companion object : ScrollViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
