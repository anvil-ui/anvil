@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.AbsoluteLayout
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun absoluteLayout(configure: AbsoluteLayoutScope.() -> Unit = {}) =
    v<AbsoluteLayout>(configure.bind(AbsoluteLayoutScope))
abstract class AbsoluteLayoutScope : ViewGroupScope() {
  companion object : AbsoluteLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
