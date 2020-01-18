@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit

fun adapterView(configure: AdapterViewScope.() -> Unit = {}) =
    v<AdapterView<*>>(configure.bind(AdapterViewScope))
abstract class AdapterViewScope : ViewGroupScope() {
  fun adapter(arg: Adapter): Unit = attr("adapter", arg)
  fun emptyView(arg: View): Unit = attr("emptyView", arg)
  fun onItemClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Unit)?): Unit = attr("onItemClick", arg)
  fun onItemLongClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Boolean)?): Unit = attr("onItemLongClick", arg)
  fun onItemSelected(arg: AdapterView.OnItemSelectedListener?): Unit = attr("onItemSelected", arg)
  fun selection(arg: Int): Unit = attr("selection", arg)
  companion object : AdapterViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
