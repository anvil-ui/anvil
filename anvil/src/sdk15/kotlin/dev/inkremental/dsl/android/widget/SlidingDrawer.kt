@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.SlidingDrawer
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun slidingDrawer(configure: SlidingDrawerScope.() -> Unit = {}) =
    v<SlidingDrawer>(configure.bind(SlidingDrawerScope))
abstract class SlidingDrawerScope : ViewGroupScope() {
  fun onDrawerClose(arg: (() -> Unit)?): Unit = attr("onDrawerClose", arg)
  fun onDrawerOpen(arg: (() -> Unit)?): Unit = attr("onDrawerOpen", arg)
  fun onDrawerScroll(arg: SlidingDrawer.OnDrawerScrollListener?): Unit = attr("onDrawerScroll", arg)
  companion object : SlidingDrawerScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
