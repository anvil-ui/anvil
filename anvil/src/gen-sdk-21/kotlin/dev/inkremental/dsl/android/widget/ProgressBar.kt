@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.animation.Interpolator
import android.widget.ProgressBar
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.ColorState
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun progressBar(configure: ProgressBarScope.() -> Unit = {}) =
    v<ProgressBar>(configure.bind(ProgressBarScope))
abstract class ProgressBarScope : ViewScope() {
  fun indeterminate(arg: Boolean): Unit = attr("indeterminate", arg)
  fun indeterminateDrawable(arg: Drawable): Unit = attr("indeterminateDrawable", arg)
  fun indeterminateDrawableTiled(arg: Drawable): Unit = attr("indeterminateDrawableTiled", arg)
  fun indeterminateTintList(arg: ColorState?): Unit = attr("indeterminateTintList", arg?.value)
  fun indeterminateTintMode(arg: PorterDuff.Mode?): Unit = attr("indeterminateTintMode", arg)
  fun interpolator(arg: Interpolator): Unit = attr("interpolator", arg)
  fun max(arg: Int): Unit = attr("max", arg)
  fun progress(arg: Int): Unit = attr("progress", arg)
  fun progressBackgroundTintList(arg: ColorState?): Unit = attr("progressBackgroundTintList",
      arg?.value)
  fun progressBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("progressBackgroundTintMode",
      arg)
  fun progressDrawable(arg: Drawable): Unit = attr("progressDrawable", arg)
  fun progressDrawableTiled(arg: Drawable): Unit = attr("progressDrawableTiled", arg)
  fun progressTintList(arg: ColorState?): Unit = attr("progressTintList", arg?.value)
  fun progressTintMode(arg: PorterDuff.Mode?): Unit = attr("progressTintMode", arg)
  fun secondaryProgress(arg: Int): Unit = attr("secondaryProgress", arg)
  fun secondaryProgressTintList(arg: ColorState?): Unit = attr("secondaryProgressTintList",
      arg?.value)
  fun secondaryProgressTintMode(arg: PorterDuff.Mode?): Unit = attr("secondaryProgressTintMode",
      arg)
  companion object : ProgressBarScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
