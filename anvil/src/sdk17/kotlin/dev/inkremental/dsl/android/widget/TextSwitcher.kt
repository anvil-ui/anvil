@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.TextSwitcher
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.CharSequence
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun textSwitcher(configure: TextSwitcherScope.() -> Unit = {}) =
    v<TextSwitcher>(configure.bind(TextSwitcherScope))
abstract class TextSwitcherScope : ViewSwitcherScope() {
  fun currentText(arg: CharSequence): Unit = attr("currentText", arg)
  fun text(arg: CharSequence): Unit = attr("text", arg)
  companion object : TextSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
