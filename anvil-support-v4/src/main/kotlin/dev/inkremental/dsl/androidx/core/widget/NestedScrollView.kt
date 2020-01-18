@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.core.widget

import androidx.core.widget.NestedScrollView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun nestedScrollView(configure: NestedScrollViewScope.() -> Unit = {}) =
    v<NestedScrollView>(configure.bind(NestedScrollViewScope))
abstract class NestedScrollViewScope : FrameLayoutScope() {
  fun fillViewport(arg: Boolean): Unit = attr("fillViewport", arg)
  fun smoothScrollingEnabled(arg: Boolean): Unit = attr("smoothScrollingEnabled", arg)
  companion object : NestedScrollViewScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
