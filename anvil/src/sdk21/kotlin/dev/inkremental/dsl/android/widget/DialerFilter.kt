@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.text.TextWatcher
import android.widget.DialerFilter
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun dialerFilter(configure: DialerFilterScope.() -> Unit = {}) =
    v<DialerFilter>(configure.bind(DialerFilterScope))
abstract class DialerFilterScope : RelativeLayoutScope() {
  fun digitsWatcher(arg: TextWatcher): Unit = attr("digitsWatcher", arg)
  fun filterWatcher(arg: TextWatcher): Unit = attr("filterWatcher", arg)
  fun lettersWatcher(arg: TextWatcher): Unit = attr("lettersWatcher", arg)
  fun mode(arg: Int): Unit = attr("mode", arg)
  companion object : DialerFilterScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
