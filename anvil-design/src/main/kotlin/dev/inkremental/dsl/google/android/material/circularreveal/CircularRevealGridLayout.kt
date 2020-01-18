@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.circularreveal

import android.graphics.drawable.Drawable
import com.google.android.material.circularreveal.CircularRevealGridLayout
import com.google.android.material.circularreveal.CircularRevealWidget
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.GridLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun circularRevealGridLayout(configure: CircularRevealGridLayoutScope.() -> Unit = {}) =
    v<CircularRevealGridLayout>(configure.bind(CircularRevealGridLayoutScope))
abstract class CircularRevealGridLayoutScope : GridLayoutScope() {
  fun circularRevealOverlayDrawable(arg: Drawable?): Unit = attr("circularRevealOverlayDrawable",
      arg)
  fun circularRevealScrimColor(arg: Int): Unit = attr("circularRevealScrimColor", arg)
  fun revealInfo(arg: CircularRevealWidget.RevealInfo?): Unit = attr("revealInfo", arg)
  companion object : CircularRevealGridLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
