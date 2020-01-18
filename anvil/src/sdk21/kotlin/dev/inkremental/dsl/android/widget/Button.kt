@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.Button
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun button(configure: ButtonScope.() -> Unit = {}) = v<Button>(configure.bind(ButtonScope))
abstract class ButtonScope : TextViewScope() {
  companion object : ButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
