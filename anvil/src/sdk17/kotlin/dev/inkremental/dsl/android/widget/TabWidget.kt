@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.TabWidget
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

fun tabWidget(configure: TabWidgetScope.() -> Unit = {}) =
    v<TabWidget>(configure.bind(TabWidgetScope))
abstract class TabWidgetScope : LinearLayoutScope() {
  fun currentTab(arg: Int): Unit = attr("currentTab", arg)
  fun dividerDrawable(arg: Int): Unit = attr("dividerDrawable", arg)
  fun leftStripDrawable(arg: Drawable?): Unit = attr("leftStripDrawable", arg)
  fun leftStripDrawable(arg: Int): Unit = attr("leftStripDrawable", arg)
  fun rightStripDrawable(arg: Drawable?): Unit = attr("rightStripDrawable", arg)
  fun rightStripDrawable(arg: Int): Unit = attr("rightStripDrawable", arg)
  fun stripEnabled(arg: Boolean): Unit = attr("stripEnabled", arg)
  companion object : TabWidgetScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
