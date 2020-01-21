@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.view.animation.Interpolator
import android.widget.ProgressBar
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
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
  fun interpolator(arg: Interpolator): Unit = attr("interpolator", arg)
  fun max(arg: Int): Unit = attr("max", arg)
  fun progress(arg: Int): Unit = attr("progress", arg)
  fun progressDrawable(arg: Drawable): Unit = attr("progressDrawable", arg)
  fun secondaryProgress(arg: Int): Unit = attr("secondaryProgress", arg)
  companion object : ProgressBarScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
