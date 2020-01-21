@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.AbsSpinner
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun absSpinner(configure: AbsSpinnerScope.() -> Unit = {}) =
    v<AbsSpinner>(configure.bind(AbsSpinnerScope))
abstract class AbsSpinnerScope : AdapterViewScope() {
  companion object : AbsSpinnerScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
