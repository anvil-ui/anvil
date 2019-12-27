@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.Spinner
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun spinner(configure: SpinnerScope.() -> Unit = {}) = v<Spinner>(configure.bind(SpinnerScope))
abstract class SpinnerScope : AbsSpinnerScope() {
  fun dropDownHorizontalOffset(arg: Int): Unit = attr("dropDownHorizontalOffset", arg)
  fun dropDownVerticalOffset(arg: Int): Unit = attr("dropDownVerticalOffset", arg)
  fun dropDownWidth(arg: Int): Unit = attr("dropDownWidth", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun popupBackgroundDrawable(arg: Drawable): Unit = attr("popupBackgroundDrawable", arg)
  fun popupBackgroundResource(arg: Int): Unit = attr("popupBackgroundResource", arg)
  fun prompt(arg: CharSequence): Unit = attr("prompt", arg)
  fun promptId(arg: Int): Unit = attr("promptId", arg)
  companion object : SpinnerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
