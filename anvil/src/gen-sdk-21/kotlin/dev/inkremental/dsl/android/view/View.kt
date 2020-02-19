@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.animation.StateListAnimator
import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.ContextMenu
import android.view.DragEvent
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowInsets
import android.view.animation.Animation
import dev.inkremental.Inkremental
import dev.inkremental.RootViewScope
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Any
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun view(configure: ViewScope.() -> Unit = {}) = v<View>(configure.bind(ViewScope))
abstract class ViewScope : RootViewScope() {
  fun accessibilityDelegate(arg: View.AccessibilityDelegate?): Unit = attr("accessibilityDelegate",
      arg)
  fun accessibilityLiveRegion(arg: Int): Unit = attr("accessibilityLiveRegion", arg)
  fun activated(arg: Boolean): Unit = attr("activated", arg)
  fun alpha(arg: Float): Unit = attr("alpha", arg)
  fun animation(arg: Animation): Unit = attr("animation", arg)
  fun background(arg: Drawable): Unit = attr("background", arg)
  fun backgroundColor(arg: Int): Unit = attr("backgroundColor", arg)
  fun backgroundResource(arg: Int): Unit = attr("backgroundResource", arg)
  fun backgroundTintList(arg: ColorStateList?): Unit = attr("backgroundTintList", arg)
  fun backgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("backgroundTintMode", arg)
  fun bottom(arg: Int): Unit = attr("bottom", arg)
  fun cameraDistance(arg: Float): Unit = attr("cameraDistance", arg)
  fun clickable(arg: Boolean): Unit = attr("clickable", arg)
  fun clipBounds(arg: Rect): Unit = attr("clipBounds", arg)
  fun clipToOutline(arg: Boolean): Unit = attr("clipToOutline", arg)
  fun contentDescription(arg: CharSequence): Unit = attr("contentDescription", arg)
  fun drawingCacheBackgroundColor(arg: Int): Unit = attr("drawingCacheBackgroundColor", arg)
  fun drawingCacheEnabled(arg: Boolean): Unit = attr("drawingCacheEnabled", arg)
  fun drawingCacheQuality(arg: Int): Unit = attr("drawingCacheQuality", arg)
  fun duplicateParentStateEnabled(arg: Boolean): Unit = attr("duplicateParentStateEnabled", arg)
  fun enabled(arg: Boolean): Unit = attr("enabled", arg)
  fun fadingEdgeLength(arg: Int): Unit = attr("fadingEdgeLength", arg)
  fun filterTouchesWhenObscured(arg: Boolean): Unit = attr("filterTouchesWhenObscured", arg)
  fun fitsSystemWindows(arg: Boolean): Unit = attr("fitsSystemWindows", arg)
  fun focusable(arg: Boolean): Unit = attr("focusable", arg)
  fun focusableInTouchMode(arg: Boolean): Unit = attr("focusableInTouchMode", arg)
  fun hapticFeedbackEnabled(arg: Boolean): Unit = attr("hapticFeedbackEnabled", arg)
  fun hasTransientState(arg: Boolean): Unit = attr("hasTransientState", arg)
  fun horizontalFadingEdgeEnabled(arg: Boolean): Unit = attr("horizontalFadingEdgeEnabled", arg)
  fun horizontalScrollBarEnabled(arg: Boolean): Unit = attr("horizontalScrollBarEnabled", arg)
  fun hovered(arg: Boolean): Unit = attr("hovered", arg)
  fun id(arg: Int): Unit = attr("id", arg)
  fun importantForAccessibility(arg: Int): Unit = attr("importantForAccessibility", arg)
  fun keepScreenOn(arg: Boolean): Unit = attr("keepScreenOn", arg)
  fun labelFor(arg: Int): Unit = attr("labelFor", arg)
  fun layerPaint(arg: Paint?): Unit = attr("layerPaint", arg)
  fun layoutDirection(arg: Int): Unit = attr("layoutDirection", arg)
  fun layoutParams(arg: ViewGroup.LayoutParams): Unit = attr("layoutParams", arg)
  fun left(arg: Int): Unit = attr("left", arg)
  fun longClickable(arg: Boolean): Unit = attr("longClickable", arg)
  fun minimumHeight(arg: Int): Unit = attr("minimumHeight", arg)
  fun minimumWidth(arg: Int): Unit = attr("minimumWidth", arg)
  fun nestedScrollingEnabled(arg: Boolean): Unit = attr("nestedScrollingEnabled", arg)
  fun nextFocusDownId(arg: Int): Unit = attr("nextFocusDownId", arg)
  fun nextFocusForwardId(arg: Int): Unit = attr("nextFocusForwardId", arg)
  fun nextFocusLeftId(arg: Int): Unit = attr("nextFocusLeftId", arg)
  fun nextFocusRightId(arg: Int): Unit = attr("nextFocusRightId", arg)
  fun nextFocusUpId(arg: Int): Unit = attr("nextFocusUpId", arg)
  fun onApplyWindowInsets(arg: ((arg0: View, arg1: WindowInsets) -> WindowInsets)?): Unit =
      attr("onApplyWindowInsets", arg)
  fun onClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onClick", arg)
  fun onCreateContextMenu(arg: ((
    arg0: ContextMenu,
    arg1: View,
    arg2: ContextMenu.ContextMenuInfo
  ) -> Unit)?): Unit = attr("onCreateContextMenu", arg)
  fun onDrag(arg: ((arg0: View, arg1: DragEvent) -> Boolean)?): Unit = attr("onDrag", arg)
  fun onFocusChange(arg: ((arg0: View, arg1: Boolean) -> Unit)?): Unit = attr("onFocusChange", arg)
  fun onGenericMotion(arg: ((arg0: View, arg1: MotionEvent) -> Boolean)?): Unit =
      attr("onGenericMotion", arg)
  fun onHover(arg: ((arg0: View, arg1: MotionEvent) -> Boolean)?): Unit = attr("onHover", arg)
  fun onKey(arg: ((
    arg0: View,
    arg1: Int,
    arg2: KeyEvent
  ) -> Boolean)?): Unit = attr("onKey", arg)
  fun onLongClick(arg: ((arg0: View) -> Boolean)?): Unit = attr("onLongClick", arg)
  fun onSystemUiVisibilityChange(arg: ((arg0: Int) -> Unit)?): Unit =
      attr("onSystemUiVisibilityChange", arg)
  fun onTouch(arg: ((arg0: View, arg1: MotionEvent) -> Boolean)?): Unit = attr("onTouch", arg)
  fun outlineProvider(arg: ViewOutlineProvider): Unit = attr("outlineProvider", arg)
  fun overScrollMode(arg: Int): Unit = attr("overScrollMode", arg)
  fun pivotX(arg: Float): Unit = attr("pivotX", arg)
  fun pivotY(arg: Float): Unit = attr("pivotY", arg)
  fun pressed(arg: Boolean): Unit = attr("pressed", arg)
  fun right(arg: Int): Unit = attr("right", arg)
  fun rotation(arg: Float): Unit = attr("rotation", arg)
  fun rotationX(arg: Float): Unit = attr("rotationX", arg)
  fun rotationY(arg: Float): Unit = attr("rotationY", arg)
  fun saveEnabled(arg: Boolean): Unit = attr("saveEnabled", arg)
  fun saveFromParentEnabled(arg: Boolean): Unit = attr("saveFromParentEnabled", arg)
  fun scaleX(arg: Float): Unit = attr("scaleX", arg)
  fun scaleY(arg: Float): Unit = attr("scaleY", arg)
  fun scrollBarDefaultDelayBeforeFade(arg: Int): Unit = attr("scrollBarDefaultDelayBeforeFade", arg)
  fun scrollBarFadeDuration(arg: Int): Unit = attr("scrollBarFadeDuration", arg)
  fun scrollBarSize(arg: Int): Unit = attr("scrollBarSize", arg)
  fun scrollBarStyle(arg: Int): Unit = attr("scrollBarStyle", arg)
  fun scrollContainer(arg: Boolean): Unit = attr("scrollContainer", arg)
  fun scrollX(arg: Int): Unit = attr("scrollX", arg)
  fun scrollY(arg: Int): Unit = attr("scrollY", arg)
  fun scrollbarFadingEnabled(arg: Boolean): Unit = attr("scrollbarFadingEnabled", arg)
  fun selected(arg: Boolean): Unit = attr("selected", arg)
  fun soundEffectsEnabled(arg: Boolean): Unit = attr("soundEffectsEnabled", arg)
  fun stateListAnimator(arg: StateListAnimator): Unit = attr("stateListAnimator", arg)
  fun systemUiVisibility(arg: Int): Unit = attr("systemUiVisibility", arg)
  fun tag(arg: Any): Unit = attr("tag", arg)
  fun textAlignment(arg: Int): Unit = attr("textAlignment", arg)
  fun textDirection(arg: Int): Unit = attr("textDirection", arg)
  fun top(arg: Int): Unit = attr("top", arg)
  fun touchDelegate(arg: TouchDelegate): Unit = attr("touchDelegate", arg)
  fun transitionName(arg: String): Unit = attr("transitionName", arg)
  fun translationX(arg: Float): Unit = attr("translationX", arg)
  fun translationY(arg: Float): Unit = attr("translationY", arg)
  fun translationZ(arg: Float): Unit = attr("translationZ", arg)
  fun verticalFadingEdgeEnabled(arg: Boolean): Unit = attr("verticalFadingEdgeEnabled", arg)
  fun verticalScrollBarEnabled(arg: Boolean): Unit = attr("verticalScrollBarEnabled", arg)
  fun verticalScrollbarPosition(arg: Int): Unit = attr("verticalScrollbarPosition", arg)
  fun visibility(arg: Int): Unit = attr("visibility", arg)
  fun willNotCacheDrawing(arg: Boolean): Unit = attr("willNotCacheDrawing", arg)
  fun willNotDraw(arg: Boolean): Unit = attr("willNotDraw", arg)
  fun x(arg: Float): Unit = attr("x", arg)
  fun y(arg: Float): Unit = attr("y", arg)
  fun z(arg: Float): Unit = attr("z", arg)
  companion object : ViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
