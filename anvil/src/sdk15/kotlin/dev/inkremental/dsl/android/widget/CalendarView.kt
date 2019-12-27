@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.CalendarView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun calendarView(configure: CalendarViewScope.() -> Unit = {}) =
    v<CalendarView>(configure.bind(CalendarViewScope))
abstract class CalendarViewScope : FrameLayoutScope() {
  fun date(arg: Long): Unit = attr("date", arg)
  fun firstDayOfWeek(arg: Int): Unit = attr("firstDayOfWeek", arg)
  fun maxDate(arg: Long): Unit = attr("maxDate", arg)
  fun minDate(arg: Long): Unit = attr("minDate", arg)
  fun onDateChange(arg: ((
    arg0: CalendarView,
    arg1: Int,
    arg2: Int,
    arg3: Int
  ) -> Unit)?): Unit = attr("onDateChange", arg)
  fun showWeekNumber(arg: Boolean): Unit = attr("showWeekNumber", arg)
  companion object : CalendarViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
