@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun linearLayout(configure: LinearLayoutScope.() -> Unit = {}) =
    v<LinearLayout>(configure.bind(LinearLayoutScope))
abstract class LinearLayoutScope : ViewGroupScope() {
  fun baselineAligned(arg: Boolean): Unit = attr("baselineAligned", arg)
  fun baselineAlignedChildIndex(arg: Int): Unit = attr("baselineAlignedChildIndex", arg)
  fun dividerDrawable(arg: Drawable): Unit = attr("dividerDrawable", arg)
  fun dividerPadding(arg: Int): Unit = attr("dividerPadding", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalGravity(arg: Int): Unit = attr("horizontalGravity", arg)
  fun measureWithLargestChildEnabled(arg: Boolean): Unit = attr("measureWithLargestChildEnabled",
      arg)
  fun orientation(arg: Int): Unit = attr("orientation", arg)
  fun showDividers(arg: Int): Unit = attr("showDividers", arg)
  fun verticalGravity(arg: Int): Unit = attr("verticalGravity", arg)
  fun weightSum(arg: Float): Unit = attr("weightSum", arg)
  companion object : LinearLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
