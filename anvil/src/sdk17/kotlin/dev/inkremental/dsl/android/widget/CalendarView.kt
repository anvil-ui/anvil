@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
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
  fun dateTextAppearance(arg: Int): Unit = attr("dateTextAppearance", arg)
  fun firstDayOfWeek(arg: Int): Unit = attr("firstDayOfWeek", arg)
  fun focusedMonthDateColor(arg: Int): Unit = attr("focusedMonthDateColor", arg)
  fun maxDate(arg: Long): Unit = attr("maxDate", arg)
  fun minDate(arg: Long): Unit = attr("minDate", arg)
  fun onDateChange(arg: ((
    arg0: CalendarView,
    arg1: Int,
    arg2: Int,
    arg3: Int
  ) -> Unit)?): Unit = attr("onDateChange", arg)
  fun selectedDateVerticalBar(arg: Drawable): Unit = attr("selectedDateVerticalBar", arg)
  fun selectedDateVerticalBar(arg: Int): Unit = attr("selectedDateVerticalBar", arg)
  fun selectedWeekBackgroundColor(arg: Int): Unit = attr("selectedWeekBackgroundColor", arg)
  fun showWeekNumber(arg: Boolean): Unit = attr("showWeekNumber", arg)
  fun shownWeekCount(arg: Int): Unit = attr("shownWeekCount", arg)
  fun unfocusedMonthDateColor(arg: Int): Unit = attr("unfocusedMonthDateColor", arg)
  fun weekDayTextAppearance(arg: Int): Unit = attr("weekDayTextAppearance", arg)
  fun weekNumberColor(arg: Int): Unit = attr("weekNumberColor", arg)
  fun weekSeparatorLineColor(arg: Int): Unit = attr("weekSeparatorLineColor", arg)
  companion object : CalendarViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
