@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.AbsSeekBar
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

fun absSeekBar(configure: AbsSeekBarScope.() -> Unit = {}) =
    v<AbsSeekBar>(configure.bind(AbsSeekBarScope))
abstract class AbsSeekBarScope : ProgressBarScope() {
  fun keyProgressIncrement(arg: Int): Unit = attr("keyProgressIncrement", arg)
  fun splitTrack(arg: Boolean): Unit = attr("splitTrack", arg)
  fun thumb(arg: Drawable): Unit = attr("thumb", arg)
  fun thumbOffset(arg: Int): Unit = attr("thumbOffset", arg)
  fun thumbTintList(arg: ColorStateList?): Unit = attr("thumbTintList", arg)
  fun thumbTintMode(arg: PorterDuff.Mode?): Unit = attr("thumbTintMode", arg)
  companion object : AbsSeekBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
