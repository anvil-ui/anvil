@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ToggleButton
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.CharSequence
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun toggleButton(configure: ToggleButtonScope.() -> Unit = {}) =
    v<ToggleButton>(configure.bind(ToggleButtonScope))
abstract class ToggleButtonScope : CompoundButtonScope() {
  fun textOff(arg: CharSequence): Unit = attr("textOff", arg)
  fun textOn(arg: CharSequence): Unit = attr("textOn", arg)
  companion object : ToggleButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
