@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.AnalogClock
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun analogClock(configure: AnalogClockScope.() -> Unit = {}) =
    v<AnalogClock>(configure.bind(AnalogClockScope))
abstract class AnalogClockScope : ViewScope() {
  companion object : AnalogClockScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
