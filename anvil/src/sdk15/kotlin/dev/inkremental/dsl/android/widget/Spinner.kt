@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.Spinner
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun spinner(configure: SpinnerScope.() -> Unit = {}) = v<Spinner>(configure.bind(SpinnerScope))
abstract class SpinnerScope : AbsSpinnerScope() {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun prompt(arg: CharSequence): Unit = attr("prompt", arg)
  fun promptId(arg: Int): Unit = attr("promptId", arg)
  companion object : SpinnerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
