@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.Chronometer
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun chronometer(configure: ChronometerScope.() -> Unit = {}) =
    v<Chronometer>(configure.bind(ChronometerScope))
abstract class ChronometerScope : TextViewScope() {
  fun base(arg: Long): Unit = attr("base", arg)
  fun format(arg: String): Unit = attr("format", arg)
  fun onChronometerTick(arg: ((arg0: Chronometer) -> Unit)?): Unit = attr("onChronometerTick", arg)
  companion object : ChronometerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
