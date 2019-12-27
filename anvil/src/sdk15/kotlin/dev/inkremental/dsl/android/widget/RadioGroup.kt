@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.RadioGroup
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun radioGroup(configure: RadioGroupScope.() -> Unit = {}) =
    v<RadioGroup>(configure.bind(RadioGroupScope))
abstract class RadioGroupScope : LinearLayoutScope() {
  fun onCheckedChange(arg: ((arg0: RadioGroup, arg1: Int) -> Unit)?): Unit = attr("onCheckedChange",
      arg)
  companion object : RadioGroupScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
