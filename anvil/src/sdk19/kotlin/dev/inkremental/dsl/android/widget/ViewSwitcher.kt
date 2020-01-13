@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ViewSwitcher
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

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
