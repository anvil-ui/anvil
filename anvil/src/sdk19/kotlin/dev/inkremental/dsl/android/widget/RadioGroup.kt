@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.RadioGroup
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

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
