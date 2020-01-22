@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.gesture

import android.gesture.Gesture
import android.gesture.GestureOverlayView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit

fun gestureOverlayView(configure: GestureOverlayViewScope.() -> Unit = {}) =
    v<GestureOverlayView>(configure.bind(GestureOverlayViewScope))
abstract class GestureOverlayViewScope : FrameLayoutScope() {
  fun eventsInterceptionEnabled(arg: Boolean): Unit = attr("eventsInterceptionEnabled", arg)
  fun fadeEnabled(arg: Boolean): Unit = attr("fadeEnabled", arg)
  fun fadeOffset(arg: Long): Unit = attr("fadeOffset", arg)
  fun gesture(arg: Gesture): Unit = attr("gesture", arg)
  fun gestureColor(arg: Int): Unit = attr("gestureColor", arg)
  fun gestureStrokeAngleThreshold(arg: Float): Unit = attr("gestureStrokeAngleThreshold", arg)
  fun gestureStrokeLengthThreshold(arg: Float): Unit = attr("gestureStrokeLengthThreshold", arg)
  fun gestureStrokeSquarenessTreshold(arg: Float): Unit = attr("gestureStrokeSquarenessTreshold",
      arg)
  fun gestureStrokeType(arg: Int): Unit = attr("gestureStrokeType", arg)
  fun gestureStrokeWidth(arg: Float): Unit = attr("gestureStrokeWidth", arg)
  fun gestureVisible(arg: Boolean): Unit = attr("gestureVisible", arg)
  fun orientation(arg: Int): Unit = attr("orientation", arg)
  fun uncertainGestureColor(arg: Int): Unit = attr("uncertainGestureColor", arg)
  companion object : GestureOverlayViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
