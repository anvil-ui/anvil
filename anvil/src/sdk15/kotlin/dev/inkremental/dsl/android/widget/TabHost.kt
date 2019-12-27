@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.TabHost
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun tabHost(configure: TabHostScope.() -> Unit = {}) = v<TabHost>(configure.bind(TabHostScope))
abstract class TabHostScope : FrameLayoutScope() {
  fun currentTab(arg: Int): Unit = attr("currentTab", arg)
  fun currentTabByTag(arg: String): Unit = attr("currentTabByTag", arg)
  fun onTabChanged(arg: ((arg0: String) -> Unit)?): Unit = attr("onTabChanged", arg)
  companion object : TabHostScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
