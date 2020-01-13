@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.coordinatorlayout.widget

import android.graphics.drawable.Drawable
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun coordinatorLayout(configure: CoordinatorLayoutScope.() -> Unit = {}) =
    v<CoordinatorLayout>(configure.bind(CoordinatorLayoutScope))
abstract class CoordinatorLayoutScope : ViewGroupScope() {
  fun statusBarBackground(arg: Drawable?): Unit = attr("statusBarBackground", arg)
  fun statusBarBackgroundColor(arg: Int): Unit = attr("statusBarBackgroundColor", arg)
  fun statusBarBackgroundResource(arg: Int): Unit = attr("statusBarBackgroundResource", arg)
  companion object : CoordinatorLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
