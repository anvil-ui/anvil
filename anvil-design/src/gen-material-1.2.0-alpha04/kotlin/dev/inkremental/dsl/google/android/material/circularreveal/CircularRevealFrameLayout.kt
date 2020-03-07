@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.circularreveal

import android.graphics.drawable.Drawable
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.google.android.material.circularreveal.CircularRevealWidget
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun circularRevealFrameLayout(configure: CircularRevealFrameLayoutScope.() -> Unit = {}) =
    v<CircularRevealFrameLayout>(configure.bind(CircularRevealFrameLayoutScope))
abstract class CircularRevealFrameLayoutScope : FrameLayoutScope() {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealFrameLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
