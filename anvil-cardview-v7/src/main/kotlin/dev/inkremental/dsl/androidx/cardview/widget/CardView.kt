@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.cardview.widget

import android.content.res.ColorStateList
import androidx.cardview.widget.CardView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.Dip
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.androidx.cardview.CardViewv7Setter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun cardView(configure: CardViewScope.() -> Unit = {}) = v<CardView>(configure.bind(CardViewScope))
abstract class CardViewScope : FrameLayoutScope() {
  fun cardBackgroundColor(arg: ColorStateList?): Unit = attr("cardBackgroundColor", arg)
  fun cardBackgroundColor(arg: Int): Unit = attr("cardBackgroundColor", arg)
  fun cardElevation(arg: Dip): Unit = attr("cardElevation", arg.value)
  fun maxCardElevation(arg: Dip): Unit = attr("maxCardElevation", arg.value)
  fun preventCornerOverlap(arg: Boolean): Unit = attr("preventCornerOverlap", arg)
  fun radius(arg: Dip): Unit = attr("radius", arg.value)
  fun useCompatPadding(arg: Boolean): Unit = attr("useCompatPadding", arg)
  companion object : CardViewScope() {
    init {
      Inkremental.registerAttributeSetter(CardViewv7Setter)
    }
  }
}
