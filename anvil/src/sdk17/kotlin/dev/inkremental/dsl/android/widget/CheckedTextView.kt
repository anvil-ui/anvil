@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.CheckedTextView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun checkedTextView(configure: CheckedTextViewScope.() -> Unit = {}) =
    v<CheckedTextView>(configure.bind(CheckedTextViewScope))
abstract class CheckedTextViewScope : TextViewScope() {
  fun checkMarkDrawable(arg: Drawable?): Unit = attr("checkMarkDrawable", arg)
  fun checkMarkDrawable(arg: Int): Unit = attr("checkMarkDrawable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  companion object : CheckedTextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
