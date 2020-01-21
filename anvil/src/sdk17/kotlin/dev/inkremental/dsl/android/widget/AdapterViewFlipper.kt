@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.AdapterViewFlipper
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun adapterViewFlipper(configure: AdapterViewFlipperScope.() -> Unit = {}) =
    v<AdapterViewFlipper>(configure.bind(AdapterViewFlipperScope))
abstract class AdapterViewFlipperScope : AdapterViewAnimatorScope() {
  fun autoStart(arg: Boolean): Unit = attr("autoStart", arg)
  fun flipInterval(arg: Int): Unit = attr("flipInterval", arg)
  companion object : AdapterViewFlipperScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
