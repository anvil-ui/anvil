@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.RatingBar
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun ratingBar(configure: RatingBarScope.() -> Unit = {}) =
    v<RatingBar>(configure.bind(RatingBarScope))
abstract class RatingBarScope : AbsSeekBarScope() {
  fun isIndicator(arg: Boolean): Unit = attr("isIndicator", arg)
  fun numStars(arg: Int): Unit = attr("numStars", arg)
  fun onRatingBarChange(arg: ((
    arg0: RatingBar,
    arg1: Float,
    arg2: Boolean
  ) -> Unit)?): Unit = attr("onRatingBarChange", arg)
  fun rating(arg: Float): Unit = attr("rating", arg)
  fun stepSize(arg: Float): Unit = attr("stepSize", arg)
  companion object : RatingBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
