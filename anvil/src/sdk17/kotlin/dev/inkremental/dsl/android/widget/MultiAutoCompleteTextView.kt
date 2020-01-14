@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.MultiAutoCompleteTextView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun multiAutoCompleteTextView(configure: MultiAutoCompleteTextViewScope.() -> Unit = {}) =
    v<MultiAutoCompleteTextView>(configure.bind(MultiAutoCompleteTextViewScope))
abstract class MultiAutoCompleteTextViewScope : AutoCompleteTextViewScope() {
  fun tokenizer(arg: MultiAutoCompleteTextView.Tokenizer): Unit = attr("tokenizer", arg)
  companion object : MultiAutoCompleteTextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
