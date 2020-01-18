@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.inputmethodservice

import android.inputmethodservice.ExtractEditText
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.widget.EditTextScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun extractEditText(configure: ExtractEditTextScope.() -> Unit = {}) =
    v<ExtractEditText>(configure.bind(ExtractEditTextScope))
abstract class ExtractEditTextScope : EditTextScope() {
  companion object : ExtractEditTextScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
