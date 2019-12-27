@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.TimePicker
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

fun timePicker(configure: TimePickerScope.() -> Unit = {}) =
    v<TimePicker>(configure.bind(TimePickerScope))
abstract class TimePickerScope : FrameLayoutScope() {
  fun currentHour(arg: Int): Unit = attr("currentHour", arg)
  fun currentMinute(arg: Int): Unit = attr("currentMinute", arg)
  fun is24HourView(arg: Boolean): Unit = attr("is24HourView", arg)
  fun onTimeChanged(arg: ((
    arg0: TimePicker,
    arg1: Int,
    arg2: Int
  ) -> Unit)?): Unit = attr("onTimeChanged", arg)
  companion object : TimePickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
