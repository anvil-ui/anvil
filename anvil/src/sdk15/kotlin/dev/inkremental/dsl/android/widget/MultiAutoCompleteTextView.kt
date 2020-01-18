@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.MultiAutoCompleteTextView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

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
