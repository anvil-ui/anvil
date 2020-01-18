@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ViewSwitcher
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun viewSwitcher(configure: ViewSwitcherScope.() -> Unit = {}) =
    v<ViewSwitcher>(configure.bind(ViewSwitcherScope))
abstract class ViewSwitcherScope : ViewAnimatorScope() {
  fun factory(arg: ViewSwitcher.ViewFactory): Unit = attr("factory", arg)
  companion object : ViewSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
