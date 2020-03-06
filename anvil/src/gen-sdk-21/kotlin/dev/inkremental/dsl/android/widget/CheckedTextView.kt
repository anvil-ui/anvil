@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.CheckedTextView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun checkedTextView(configure: CheckedTextViewScope.() -> Unit = {}) =
    v<CheckedTextView>(configure.bind(CheckedTextViewScope))
abstract class CheckedTextViewScope : TextViewScope() {
  fun checkMarkDrawable(arg: Drawable?): Unit = attr("checkMarkDrawable", arg)
  fun checkMarkDrawable(arg: Int): Unit = attr("checkMarkDrawable", arg)
  fun checkMarkTintList(arg: ColorStateList?): Unit = attr("checkMarkTintList", arg)
  fun checkMarkTintMode(arg: PorterDuff.Mode?): Unit = attr("checkMarkTintMode", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  companion object : CheckedTextViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
