@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.graphics.Rect
import androidx.appcompat.widget.FitWindowsLinearLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppcompatV7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun fitWindowsLinearLayout(configure: FitWindowsLinearLayoutScope.() -> Unit = {}) =
    v<FitWindowsLinearLayout>(configure.bind(FitWindowsLinearLayoutScope))
abstract class FitWindowsLinearLayoutScope : LinearLayoutScope() {
  fun onFitSystemWindows(arg: ((arg0: Rect) -> Unit)?): Unit = attr("onFitSystemWindows", arg)
  companion object : FitWindowsLinearLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(AppcompatV7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
