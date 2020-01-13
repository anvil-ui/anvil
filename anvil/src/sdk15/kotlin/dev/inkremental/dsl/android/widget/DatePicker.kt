@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.DatePicker
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun datePicker(configure: DatePickerScope.() -> Unit = {}) =
    v<DatePicker>(configure.bind(DatePickerScope))
abstract class DatePickerScope : FrameLayoutScope() {
  fun calendarViewShown(arg: Boolean): Unit = attr("calendarViewShown", arg)
  fun maxDate(arg: Long): Unit = attr("maxDate", arg)
  fun minDate(arg: Long): Unit = attr("minDate", arg)
  fun spinnersShown(arg: Boolean): Unit = attr("spinnersShown", arg)
  companion object : DatePickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
