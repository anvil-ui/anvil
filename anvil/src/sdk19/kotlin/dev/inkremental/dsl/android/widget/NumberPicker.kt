@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.NumberPicker
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Array
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun numberPicker(configure: NumberPickerScope.() -> Unit = {}) =
    v<NumberPicker>(configure.bind(NumberPickerScope))
abstract class NumberPickerScope : LinearLayoutScope() {
  fun displayedValues(arg: Array<String>): Unit = attr("displayedValues", arg)
  fun formatter(arg: NumberPicker.Formatter): Unit = attr("formatter", arg)
  fun maxValue(arg: Int): Unit = attr("maxValue", arg)
  fun minValue(arg: Int): Unit = attr("minValue", arg)
  fun onLongPressUpdateInterval(arg: Long): Unit = attr("onLongPressUpdateInterval", arg)
  fun onScroll(arg: ((arg0: NumberPicker, arg1: Int) -> Unit)?): Unit = attr("onScroll", arg)
  fun onValueChanged(arg: ((
    arg0: NumberPicker,
    arg1: Int,
    arg2: Int
  ) -> Unit)?): Unit = attr("onValueChanged", arg)
  fun value(arg: Int): Unit = attr("value", arg)
  fun wrapSelectorWheel(arg: Boolean): Unit = attr("wrapSelectorWheel", arg)
  companion object : NumberPickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
