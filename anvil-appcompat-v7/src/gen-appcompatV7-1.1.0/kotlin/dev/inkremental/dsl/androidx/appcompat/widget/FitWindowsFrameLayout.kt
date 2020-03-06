@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.graphics.Rect
import androidx.appcompat.widget.FitWindowsFrameLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppcompatV7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun fitWindowsFrameLayout(configure: FitWindowsFrameLayoutScope.() -> Unit = {}) =
    v<FitWindowsFrameLayout>(configure.bind(FitWindowsFrameLayoutScope))
abstract class FitWindowsFrameLayoutScope : FrameLayoutScope() {
  fun onFitSystemWindows(arg: ((arg0: Rect) -> Unit)?): Unit = attr("onFitSystemWindows", arg)
  companion object : FitWindowsFrameLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(AppcompatV7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
