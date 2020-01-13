@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.cardview.widget

import android.content.res.ColorStateList
import androidx.cardview.widget.CardView
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.androidx.cardview.CardViewv7Setter
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun cardView(configure: CardViewScope.() -> Unit = {}) = v<CardView>(configure.bind(CardViewScope))
abstract class CardViewScope : FrameLayoutScope() {
  fun cardBackgroundColor(arg: ColorStateList?): Unit = attr("cardBackgroundColor", arg)
  fun cardBackgroundColor(arg: Int): Unit = attr("cardBackgroundColor", arg)
  fun cardElevation(arg: Float): Unit = attr("cardElevation", arg)
  fun maxCardElevation(arg: Float): Unit = attr("maxCardElevation", arg)
  fun preventCornerOverlap(arg: Boolean): Unit = attr("preventCornerOverlap", arg)
  fun radius(arg: Float): Unit = attr("radius", arg)
  fun useCompatPadding(arg: Boolean): Unit = attr("useCompatPadding", arg)
  companion object : CardViewScope() {
    init {
      Anvil.registerAttributeSetter(CardViewv7Setter)
    }
  }
}
