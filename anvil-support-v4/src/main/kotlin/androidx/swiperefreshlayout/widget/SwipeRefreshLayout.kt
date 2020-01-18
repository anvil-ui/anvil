@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.swiperefreshlayout.widget

import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.IntArray
import kotlin.Suppress
import kotlin.Unit

fun swipeRefreshLayout(configure: SwipeRefreshLayoutScope.() -> Unit = {}) =
    v<SwipeRefreshLayout>(configure.bind(SwipeRefreshLayoutScope))
abstract class SwipeRefreshLayoutScope : ViewGroupScope() {
  fun colorSchemeColors(arg: IntArray): Unit = attr("colorSchemeColors", arg)
  fun colorSchemeResources(arg: IntArray): Unit = attr("colorSchemeResources", arg)
  fun distanceToTriggerSync(arg: Int): Unit = attr("distanceToTriggerSync", arg)
  fun onChildScrollUpCallback(arg: SwipeRefreshLayout.OnChildScrollUpCallback?): Unit =
      attr("onChildScrollUpCallback", arg)
  fun onRefresh(arg: (() -> Unit)?): Unit = attr("onRefresh", arg)
  fun progressBackgroundColorSchemeColor(arg: Int): Unit =
      attr("progressBackgroundColorSchemeColor", arg)
  fun progressBackgroundColorSchemeResource(arg: Int): Unit =
      attr("progressBackgroundColorSchemeResource", arg)
  fun refreshing(arg: Boolean): Unit = attr("refreshing", arg)
  fun size(arg: Int): Unit = attr("size", arg)
  fun slingshotDistance(arg: Int): Unit = attr("slingshotDistance", arg)
  companion object : SwipeRefreshLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
