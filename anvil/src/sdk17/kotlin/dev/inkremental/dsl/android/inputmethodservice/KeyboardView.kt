@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.inputmethodservice

import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.view.View
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewScope
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun keyboardView(configure: KeyboardViewScope.() -> Unit = {}) =
    v<KeyboardView>(configure.bind(KeyboardViewScope))
abstract class KeyboardViewScope : ViewScope() {
  fun keyboard(arg: Keyboard): Unit = attr("keyboard", arg)
  fun onKeyboardAction(arg: KeyboardView.OnKeyboardActionListener?): Unit = attr("onKeyboardAction",
      arg)
  fun popupParent(arg: View): Unit = attr("popupParent", arg)
  fun previewEnabled(arg: Boolean): Unit = attr("previewEnabled", arg)
  fun proximityCorrectionEnabled(arg: Boolean): Unit = attr("proximityCorrectionEnabled", arg)
  fun shifted(arg: Boolean): Unit = attr("shifted", arg)
  fun verticalCorrection(arg: Int): Unit = attr("verticalCorrection", arg)
  companion object : KeyboardViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
