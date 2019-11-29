@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package trikita.anvil

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.FragmentBreadCrumbs
import android.app.FragmentManager
import android.app.MediaRouteButton
import android.app.SearchableInfo
import android.appwidget.AppWidgetHostView
import android.content.Intent
import android.content.res.ColorStateList
import android.gesture.Gesture
import android.gesture.GestureOverlayView
import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.SurfaceTexture
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.inputmethodservice.ExtractEditText
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.GLSurfaceView
import android.text.Editable
import android.text.InputFilter
import android.text.Spannable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.KeyListener
import android.text.method.MovementMethod
import android.text.method.TransformationMethod
import android.view.ActionMode
import android.view.ContextMenu
import android.view.DragEvent
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.TextureView
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.ExtractedText
import android.webkit.DownloadListener
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AbsListView
import android.widget.AbsSeekBar
import android.widget.AbsSpinner
import android.widget.AbsoluteLayout
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterViewAnimator
import android.widget.AdapterViewFlipper
import android.widget.AnalogClock
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.Chronometer
import android.widget.CompoundButton
import android.widget.CursorAdapter
import android.widget.DatePicker
import android.widget.DialerFilter
import android.widget.DigitalClock
import android.widget.EditText
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.FrameLayout
import android.widget.Gallery
import android.widget.GridLayout
import android.widget.GridView
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.MediaController
import android.widget.MultiAutoCompleteTextView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.QuickContactBadge
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Scroller
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.SlidingDrawer
import android.widget.Space
import android.widget.Spinner
import android.widget.StackView
import android.widget.Switch
import android.widget.TabHost
import android.widget.TabWidget
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextClock
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.TimePicker
import android.widget.ToggleButton
import android.widget.TwoLineListItem
import android.widget.VideoView
import android.widget.ViewAnimator
import android.widget.ViewFlipper
import android.widget.ViewSwitcher
import android.widget.ZoomButton
import android.widget.ZoomControls
import java.util.Locale
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Function
import kotlin.Int
import kotlin.IntArray
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun fragmentBreadCrumbs(configure: FragmentBreadCrumbsScope.() -> Unit = {}) =
    v<FragmentBreadCrumbs>(configure.bind(FragmentBreadCrumbsScope))
abstract class FragmentBreadCrumbsScope : ViewGroupScope() {
  fun activity(arg: Activity): Unit = attr("activity", arg)
  fun maxVisible(arg: Int): Unit = attr("maxVisible", arg)
  fun onBreadCrumbClick(arg: ((arg0: FragmentManager.BackStackEntry, arg1: Int) -> Boolean)?): Unit
      = attr("onBreadCrumbClick", arg)
  companion object : FragmentBreadCrumbsScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun mediaRouteButton(configure: MediaRouteButtonScope.() -> Unit = {}) =
    v<MediaRouteButton>(configure.bind(MediaRouteButtonScope))
abstract class MediaRouteButtonScope : ViewScope() {
  fun extendedSettingsClickListener(arg: View.OnClickListener): Unit =
      attr("extendedSettingsClickListener", arg)
  fun routeTypes(arg: Int): Unit = attr("routeTypes", arg)
  companion object : MediaRouteButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun appWidgetHostView(configure: AppWidgetHostViewScope.() -> Unit = {}) =
    v<AppWidgetHostView>(configure.bind(AppWidgetHostViewScope))
abstract class AppWidgetHostViewScope : FrameLayoutScope() {
  companion object : AppWidgetHostViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

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
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun extractEditText(configure: ExtractEditTextScope.() -> Unit = {}) =
    v<ExtractEditText>(configure.bind(ExtractEditTextScope))
abstract class ExtractEditTextScope : EditTextScope() {
  companion object : ExtractEditTextScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun keyboardView(configure: KeyboardViewScope.() -> Unit = {}) =
    v<KeyboardView>(configure.bind(KeyboardViewScope))
abstract class KeyboardViewScope : ViewScope() {
  fun keyboard(arg: Keyboard): Unit = attr("keyboard", arg)
  fun onKeyboardAction(arg: KeyboardView.OnKeyboardActionListener?): Unit = attr("onKeyboardAction",
      arg)
  fun popupParent(arg: View): Unit = attr("popupParent", arg)
  fun previewEnabled(arg: Boolean): Unit = attr("previewEnabled", arg)
  fun proximityCorrectionEnabled(arg: Boolean): Unit = attr("proximityCorrectionEnabled", arg)
  fun shifted(arg: Boolean): Unit = attr("shifted", arg)
  fun verticalCorrection(arg: Int): Unit = attr("verticalCorrection", arg)
  companion object : KeyboardViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun gLSurfaceView(configure: GLSurfaceViewScope.() -> Unit = {}) =
    v<GLSurfaceView>(configure.bind(GLSurfaceViewScope))
abstract class GLSurfaceViewScope : SurfaceViewScope() {
  fun debugFlags(arg: Int): Unit = attr("debugFlags", arg)
  fun eGLConfigChooser(arg: Boolean): Unit = attr("eGLConfigChooser", arg)
  fun eGLConfigChooser(arg: GLSurfaceView.EGLConfigChooser): Unit = attr("eGLConfigChooser", arg)
  fun eGLContextClientVersion(arg: Int): Unit = attr("eGLContextClientVersion", arg)
  fun eGLContextFactory(arg: GLSurfaceView.EGLContextFactory): Unit = attr("eGLContextFactory", arg)
  fun eGLWindowSurfaceFactory(arg: GLSurfaceView.EGLWindowSurfaceFactory): Unit =
      attr("eGLWindowSurfaceFactory", arg)
  fun gLWrapper(arg: GLSurfaceView.GLWrapper): Unit = attr("gLWrapper", arg)
  fun preserveEGLContextOnPause(arg: Boolean): Unit = attr("preserveEGLContextOnPause", arg)
  fun renderMode(arg: Int): Unit = attr("renderMode", arg)
  fun renderer(arg: GLSurfaceView.Renderer): Unit = attr("renderer", arg)
  companion object : GLSurfaceViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun surfaceView(configure: SurfaceViewScope.() -> Unit = {}) =
    v<SurfaceView>(configure.bind(SurfaceViewScope))
abstract class SurfaceViewScope : ViewScope() {
  fun secure(arg: Boolean): Unit = attr("secure", arg)
  fun zOrderMediaOverlay(arg: Boolean): Unit = attr("zOrderMediaOverlay", arg)
  fun zOrderOnTop(arg: Boolean): Unit = attr("zOrderOnTop", arg)
  companion object : SurfaceViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun textureView(configure: TextureViewScope.() -> Unit = {}) =
    v<TextureView>(configure.bind(TextureViewScope))
abstract class TextureViewScope : ViewScope() {
  fun opaque(arg: Boolean): Unit = attr("opaque", arg)
  fun surfaceTexture(arg: SurfaceTexture): Unit = attr("surfaceTexture", arg)
  fun surfaceTextureListener(arg: TextureView.SurfaceTextureListener): Unit =
      attr("surfaceTextureListener", arg)
  fun transform(arg: Matrix): Unit = attr("transform", arg)
  companion object : TextureViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun view(configure: ViewScope.() -> Unit = {}) = v<View>(configure.bind(ViewScope))
abstract class ViewScope {
  fun accessibilityDelegate(arg: View.AccessibilityDelegate?): Unit = attr("accessibilityDelegate",
      arg)
  fun accessibilityLiveRegion(arg: Int): Unit = attr("accessibilityLiveRegion", arg)
  fun activated(arg: Boolean): Unit = attr("activated", arg)
  fun alpha(arg: Float): Unit = attr("alpha", arg)
  fun animation(arg: Animation): Unit = attr("animation", arg)
  fun background(arg: Drawable): Unit = attr("background", arg)
  fun backgroundColor(arg: Int): Unit = attr("backgroundColor", arg)
  fun backgroundResource(arg: Int): Unit = attr("backgroundResource", arg)
  fun bottom(arg: Int): Unit = attr("bottom", arg)
  fun cameraDistance(arg: Float): Unit = attr("cameraDistance", arg)
  fun clickable(arg: Boolean): Unit = attr("clickable", arg)
  fun clipBounds(arg: Rect): Unit = attr("clipBounds", arg)
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
  fun nextFocusDownId(arg: Int): Unit = attr("nextFocusDownId", arg)
  fun nextFocusForwardId(arg: Int): Unit = attr("nextFocusForwardId", arg)
  fun nextFocusLeftId(arg: Int): Unit = attr("nextFocusLeftId", arg)
  fun nextFocusRightId(arg: Int): Unit = attr("nextFocusRightId", arg)
  fun nextFocusUpId(arg: Int): Unit = attr("nextFocusUpId", arg)
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
  fun systemUiVisibility(arg: Int): Unit = attr("systemUiVisibility", arg)
  fun tag(arg: Any): Unit = attr("tag", arg)
  fun textAlignment(arg: Int): Unit = attr("textAlignment", arg)
  fun textDirection(arg: Int): Unit = attr("textDirection", arg)
  fun top(arg: Int): Unit = attr("top", arg)
  fun touchDelegate(arg: TouchDelegate): Unit = attr("touchDelegate", arg)
  fun translationX(arg: Float): Unit = attr("translationX", arg)
  fun translationY(arg: Float): Unit = attr("translationY", arg)
  fun verticalFadingEdgeEnabled(arg: Boolean): Unit = attr("verticalFadingEdgeEnabled", arg)
  fun verticalScrollBarEnabled(arg: Boolean): Unit = attr("verticalScrollBarEnabled", arg)
  fun verticalScrollbarPosition(arg: Int): Unit = attr("verticalScrollbarPosition", arg)
  fun visibility(arg: Int): Unit = attr("visibility", arg)
  fun willNotCacheDrawing(arg: Boolean): Unit = attr("willNotCacheDrawing", arg)
  fun willNotDraw(arg: Boolean): Unit = attr("willNotDraw", arg)
  fun x(arg: Float): Unit = attr("x", arg)
  fun y(arg: Float): Unit = attr("y", arg)
  companion object : ViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun viewGroup(configure: ViewGroupScope.() -> Unit = {}) =
    v<ViewGroup>(configure.bind(ViewGroupScope))
abstract class ViewGroupScope : ViewScope() {
  fun addStatesFromChildren(arg: Boolean): Unit = attr("addStatesFromChildren", arg)
  fun alwaysDrawnWithCacheEnabled(arg: Boolean): Unit = attr("alwaysDrawnWithCacheEnabled", arg)
  fun animationCacheEnabled(arg: Boolean): Unit = attr("animationCacheEnabled", arg)
  fun clipChildren(arg: Boolean): Unit = attr("clipChildren", arg)
  fun clipToPadding(arg: Boolean): Unit = attr("clipToPadding", arg)
  fun descendantFocusability(arg: Int): Unit = attr("descendantFocusability", arg)
  fun layoutAnimation(arg: LayoutAnimationController): Unit = attr("layoutAnimation", arg)
  fun layoutAnimationListener(arg: Animation.AnimationListener): Unit =
      attr("layoutAnimationListener", arg)
  fun layoutMode(arg: Int): Unit = attr("layoutMode", arg)
  fun layoutTransition(arg: LayoutTransition): Unit = attr("layoutTransition", arg)
  fun motionEventSplittingEnabled(arg: Boolean): Unit = attr("motionEventSplittingEnabled", arg)
  fun onHierarchyChange(arg: ViewGroup.OnHierarchyChangeListener?): Unit = attr("onHierarchyChange",
      arg)
  fun persistentDrawingCache(arg: Int): Unit = attr("persistentDrawingCache", arg)
  companion object : ViewGroupScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun viewStub(configure: ViewStubScope.() -> Unit = {}) = v<ViewStub>(configure.bind(ViewStubScope))
abstract class ViewStubScope : ViewScope() {
  fun inflatedId(arg: Int): Unit = attr("inflatedId", arg)
  fun layoutInflater(arg: LayoutInflater): Unit = attr("layoutInflater", arg)
  fun layoutResource(arg: Int): Unit = attr("layoutResource", arg)
  fun onInflate(arg: ((arg0: ViewStub, arg1: View) -> Unit)?): Unit = attr("onInflate", arg)
  companion object : ViewStubScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun webView(configure: WebViewScope.() -> Unit = {}) = v<WebView>(configure.bind(WebViewScope))
abstract class WebViewScope : AbsoluteLayoutScope() {
  fun downloadListener(arg: DownloadListener): Unit = attr("downloadListener", arg)
  fun findListener(arg: WebView.FindListener): Unit = attr("findListener", arg)
  fun horizontalScrollbarOverlay(arg: Boolean): Unit = attr("horizontalScrollbarOverlay", arg)
  fun initialScale(arg: Int): Unit = attr("initialScale", arg)
  fun networkAvailable(arg: Boolean): Unit = attr("networkAvailable", arg)
  fun verticalScrollbarOverlay(arg: Boolean): Unit = attr("verticalScrollbarOverlay", arg)
  fun webChromeClient(arg: WebChromeClient): Unit = attr("webChromeClient", arg)
  fun webViewClient(arg: WebViewClient): Unit = attr("webViewClient", arg)
  companion object : WebViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun absListView(configure: AbsListViewScope.() -> Unit = {}) =
    v<AbsListView>(configure.bind(AbsListViewScope))
abstract class AbsListViewScope : AdapterViewScope() {
  fun cacheColorHint(arg: Int): Unit = attr("cacheColorHint", arg)
  fun choiceMode(arg: Int): Unit = attr("choiceMode", arg)
  fun drawSelectorOnTop(arg: Boolean): Unit = attr("drawSelectorOnTop", arg)
  fun fastScrollAlwaysVisible(arg: Boolean): Unit = attr("fastScrollAlwaysVisible", arg)
  fun fastScrollEnabled(arg: Boolean): Unit = attr("fastScrollEnabled", arg)
  fun filterText(arg: String): Unit = attr("filterText", arg)
  fun friction(arg: Float): Unit = attr("friction", arg)
  fun multiChoiceModeListener(arg: AbsListView.MultiChoiceModeListener): Unit =
      attr("multiChoiceModeListener", arg)
  fun onScroll(arg: AbsListView.OnScrollListener?): Unit = attr("onScroll", arg)
  fun recyclerListener(arg: AbsListView.RecyclerListener): Unit = attr("recyclerListener", arg)
  fun remoteViewsAdapter(arg: Intent): Unit = attr("remoteViewsAdapter", arg)
  fun scrollingCacheEnabled(arg: Boolean): Unit = attr("scrollingCacheEnabled", arg)
  fun selector(arg: Drawable): Unit = attr("selector", arg)
  fun selector(arg: Int): Unit = attr("selector", arg)
  fun smoothScrollbarEnabled(arg: Boolean): Unit = attr("smoothScrollbarEnabled", arg)
  fun stackFromBottom(arg: Boolean): Unit = attr("stackFromBottom", arg)
  fun textFilterEnabled(arg: Boolean): Unit = attr("textFilterEnabled", arg)
  fun transcriptMode(arg: Int): Unit = attr("transcriptMode", arg)
  fun velocityScale(arg: Float): Unit = attr("velocityScale", arg)
  companion object : AbsListViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun absSeekBar(configure: AbsSeekBarScope.() -> Unit = {}) =
    v<AbsSeekBar>(configure.bind(AbsSeekBarScope))
abstract class AbsSeekBarScope : ProgressBarScope() {
  fun keyProgressIncrement(arg: Int): Unit = attr("keyProgressIncrement", arg)
  fun thumb(arg: Drawable): Unit = attr("thumb", arg)
  fun thumbOffset(arg: Int): Unit = attr("thumbOffset", arg)
  companion object : AbsSeekBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun absSpinner(configure: AbsSpinnerScope.() -> Unit = {}) =
    v<AbsSpinner>(configure.bind(AbsSpinnerScope))
abstract class AbsSpinnerScope : AdapterViewScope() {
  companion object : AbsSpinnerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun absoluteLayout(configure: AbsoluteLayoutScope.() -> Unit = {}) =
    v<AbsoluteLayout>(configure.bind(AbsoluteLayoutScope))
abstract class AbsoluteLayoutScope : ViewGroupScope() {
  companion object : AbsoluteLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun adapterView(configure: AdapterViewScope.() -> Unit = {}) =
    v<AdapterView<*>>(configure.bind(AdapterViewScope))
abstract class AdapterViewScope : ViewGroupScope() {
  fun adapter(arg: Adapter): Unit = attr("adapter", arg)
  fun emptyView(arg: View): Unit = attr("emptyView", arg)
  fun onItemClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Unit)?): Unit = attr("onItemClick", arg)
  fun onItemLongClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Boolean)?): Unit = attr("onItemLongClick", arg)
  fun onItemSelected(arg: AdapterView.OnItemSelectedListener?): Unit = attr("onItemSelected", arg)
  fun selection(arg: Int): Unit = attr("selection", arg)
  companion object : AdapterViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun adapterViewAnimator(configure: AdapterViewAnimatorScope.() -> Unit = {}) =
    v<AdapterViewAnimator>(configure.bind(AdapterViewAnimatorScope))
abstract class AdapterViewAnimatorScope : AdapterViewScope() {
  fun animateFirstView(arg: Boolean): Unit = attr("animateFirstView", arg)
  fun displayedChild(arg: Int): Unit = attr("displayedChild", arg)
  fun inAnimation(arg: ObjectAnimator): Unit = attr("inAnimation", arg)
  fun outAnimation(arg: ObjectAnimator): Unit = attr("outAnimation", arg)
  fun remoteViewsAdapter(arg: Intent): Unit = attr("remoteViewsAdapter", arg)
  companion object : AdapterViewAnimatorScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun adapterViewFlipper(configure: AdapterViewFlipperScope.() -> Unit = {}) =
    v<AdapterViewFlipper>(configure.bind(AdapterViewFlipperScope))
abstract class AdapterViewFlipperScope : AdapterViewAnimatorScope() {
  fun autoStart(arg: Boolean): Unit = attr("autoStart", arg)
  fun flipInterval(arg: Int): Unit = attr("flipInterval", arg)
  companion object : AdapterViewFlipperScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun analogClock(configure: AnalogClockScope.() -> Unit = {}) =
    v<AnalogClock>(configure.bind(AnalogClockScope))
abstract class AnalogClockScope : ViewScope() {
  companion object : AnalogClockScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun autoCompleteTextView(configure: AutoCompleteTextViewScope.() -> Unit = {}) =
    v<AutoCompleteTextView>(configure.bind(AutoCompleteTextViewScope))
abstract class AutoCompleteTextViewScope : EditTextScope() {
  fun completionHint(arg: CharSequence): Unit = attr("completionHint", arg)
  fun dropDownAnchor(arg: Int): Unit = attr("dropDownAnchor", arg)
  fun dropDownBackgroundDrawable(arg: Drawable): Unit = attr("dropDownBackgroundDrawable", arg)
  fun dropDownBackgroundResource(arg: Int): Unit = attr("dropDownBackgroundResource", arg)
  fun dropDownHeight(arg: Int): Unit = attr("dropDownHeight", arg)
  fun dropDownHorizontalOffset(arg: Int): Unit = attr("dropDownHorizontalOffset", arg)
  fun dropDownVerticalOffset(arg: Int): Unit = attr("dropDownVerticalOffset", arg)
  fun dropDownWidth(arg: Int): Unit = attr("dropDownWidth", arg)
  fun listSelection(arg: Int): Unit = attr("listSelection", arg)
  fun onDismiss(arg: (() -> Unit)?): Unit = attr("onDismiss", arg)
  fun onItemClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Unit)?): Unit = attr("onItemClick", arg)
  fun onItemSelected(arg: AdapterView.OnItemSelectedListener?): Unit = attr("onItemSelected", arg)
  fun threshold(arg: Int): Unit = attr("threshold", arg)
  fun validator(arg: AutoCompleteTextView.Validator): Unit = attr("validator", arg)
  companion object : AutoCompleteTextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun button(configure: ButtonScope.() -> Unit = {}) = v<Button>(configure.bind(ButtonScope))
abstract class ButtonScope : TextViewScope() {
  companion object : ButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun calendarView(configure: CalendarViewScope.() -> Unit = {}) =
    v<CalendarView>(configure.bind(CalendarViewScope))
abstract class CalendarViewScope : FrameLayoutScope() {
  fun date(arg: Long): Unit = attr("date", arg)
  fun dateTextAppearance(arg: Int): Unit = attr("dateTextAppearance", arg)
  fun firstDayOfWeek(arg: Int): Unit = attr("firstDayOfWeek", arg)
  fun focusedMonthDateColor(arg: Int): Unit = attr("focusedMonthDateColor", arg)
  fun maxDate(arg: Long): Unit = attr("maxDate", arg)
  fun minDate(arg: Long): Unit = attr("minDate", arg)
  fun onDateChange(arg: ((
    arg0: CalendarView,
    arg1: Int,
    arg2: Int,
    arg3: Int
  ) -> Unit)?): Unit = attr("onDateChange", arg)
  fun selectedDateVerticalBar(arg: Drawable): Unit = attr("selectedDateVerticalBar", arg)
  fun selectedDateVerticalBar(arg: Int): Unit = attr("selectedDateVerticalBar", arg)
  fun selectedWeekBackgroundColor(arg: Int): Unit = attr("selectedWeekBackgroundColor", arg)
  fun showWeekNumber(arg: Boolean): Unit = attr("showWeekNumber", arg)
  fun shownWeekCount(arg: Int): Unit = attr("shownWeekCount", arg)
  fun unfocusedMonthDateColor(arg: Int): Unit = attr("unfocusedMonthDateColor", arg)
  fun weekDayTextAppearance(arg: Int): Unit = attr("weekDayTextAppearance", arg)
  fun weekNumberColor(arg: Int): Unit = attr("weekNumberColor", arg)
  fun weekSeparatorLineColor(arg: Int): Unit = attr("weekSeparatorLineColor", arg)
  companion object : CalendarViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun checkBox(configure: CheckBoxScope.() -> Unit = {}) = v<CheckBox>(configure.bind(CheckBoxScope))
abstract class CheckBoxScope : CompoundButtonScope() {
  companion object : CheckBoxScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun checkedTextView(configure: CheckedTextViewScope.() -> Unit = {}) =
    v<CheckedTextView>(configure.bind(CheckedTextViewScope))
abstract class CheckedTextViewScope : TextViewScope() {
  fun checkMarkDrawable(arg: Drawable?): Unit = attr("checkMarkDrawable", arg)
  fun checkMarkDrawable(arg: Int): Unit = attr("checkMarkDrawable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  companion object : CheckedTextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun chronometer(configure: ChronometerScope.() -> Unit = {}) =
    v<Chronometer>(configure.bind(ChronometerScope))
abstract class ChronometerScope : TextViewScope() {
  fun base(arg: Long): Unit = attr("base", arg)
  fun format(arg: String): Unit = attr("format", arg)
  fun onChronometerTick(arg: ((arg0: Chronometer) -> Unit)?): Unit = attr("onChronometerTick", arg)
  companion object : ChronometerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun compoundButton(configure: CompoundButtonScope.() -> Unit = {}) =
    v<CompoundButton>(configure.bind(CompoundButtonScope))
abstract class CompoundButtonScope : ButtonScope() {
  fun buttonDrawable(arg: Drawable?): Unit = attr("buttonDrawable", arg)
  fun buttonDrawable(arg: Int): Unit = attr("buttonDrawable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun onCheckedChange(arg: ((arg0: CompoundButton, arg1: Boolean) -> Unit)?): Unit =
      attr("onCheckedChange", arg)
  companion object : CompoundButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun datePicker(configure: DatePickerScope.() -> Unit = {}) =
    v<DatePicker>(configure.bind(DatePickerScope))
abstract class DatePickerScope : FrameLayoutScope() {
  fun calendarViewShown(arg: Boolean): Unit = attr("calendarViewShown", arg)
  fun maxDate(arg: Long): Unit = attr("maxDate", arg)
  fun minDate(arg: Long): Unit = attr("minDate", arg)
  fun spinnersShown(arg: Boolean): Unit = attr("spinnersShown", arg)
  companion object : DatePickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun dialerFilter(configure: DialerFilterScope.() -> Unit = {}) =
    v<DialerFilter>(configure.bind(DialerFilterScope))
abstract class DialerFilterScope : RelativeLayoutScope() {
  fun digitsWatcher(arg: TextWatcher): Unit = attr("digitsWatcher", arg)
  fun filterWatcher(arg: TextWatcher): Unit = attr("filterWatcher", arg)
  fun lettersWatcher(arg: TextWatcher): Unit = attr("lettersWatcher", arg)
  fun mode(arg: Int): Unit = attr("mode", arg)
  companion object : DialerFilterScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun digitalClock(configure: DigitalClockScope.() -> Unit = {}) =
    v<DigitalClock>(configure.bind(DigitalClockScope))
abstract class DigitalClockScope : TextViewScope() {
  companion object : DigitalClockScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun editText(configure: EditTextScope.() -> Unit = {}) = v<EditText>(configure.bind(EditTextScope))
abstract class EditTextScope : TextViewScope() {
  fun selection(arg: Int): Unit = attr("selection", arg)
  companion object : EditTextScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun expandableListView(configure: ExpandableListViewScope.() -> Unit = {}) =
    v<ExpandableListView>(configure.bind(ExpandableListViewScope))
abstract class ExpandableListViewScope : ListViewScope() {
  fun adapter(arg: ExpandableListAdapter): Unit = attr("adapter", arg)
  fun childDivider(arg: Drawable): Unit = attr("childDivider", arg)
  fun childIndicator(arg: Drawable): Unit = attr("childIndicator", arg)
  fun groupIndicator(arg: Drawable): Unit = attr("groupIndicator", arg)
  fun onChildClick(arg: ((
    arg0: ExpandableListView,
    arg1: View,
    arg2: Int,
    arg3: Int,
    arg4: Long
  ) -> Boolean)?): Unit = attr("onChildClick", arg)
  fun onGroupClick(arg: ((
    arg0: ExpandableListView,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Boolean)?): Unit = attr("onGroupClick", arg)
  fun onGroupCollapse(arg: ((arg0: Int) -> Unit)?): Unit = attr("onGroupCollapse", arg)
  fun onGroupExpand(arg: ((arg0: Int) -> Unit)?): Unit = attr("onGroupExpand", arg)
  fun selectedGroup(arg: Int): Unit = attr("selectedGroup", arg)
  companion object : ExpandableListViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun frameLayout(configure: FrameLayoutScope.() -> Unit = {}) =
    v<FrameLayout>(configure.bind(FrameLayoutScope))
abstract class FrameLayoutScope : ViewGroupScope() {
  fun foreground(arg: Drawable): Unit = attr("foreground", arg)
  fun foregroundGravity(arg: Int): Unit = attr("foregroundGravity", arg)
  fun measureAllChildren(arg: Boolean): Unit = attr("measureAllChildren", arg)
  companion object : FrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun gallery(configure: GalleryScope.() -> Unit = {}) = v<Gallery>(configure.bind(GalleryScope))
abstract class GalleryScope : AbsSpinnerScope() {
  fun animationDuration(arg: Int): Unit = attr("animationDuration", arg)
  fun callbackDuringFling(arg: Boolean): Unit = attr("callbackDuringFling", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun spacing(arg: Int): Unit = attr("spacing", arg)
  fun unselectedAlpha(arg: Float): Unit = attr("unselectedAlpha", arg)
  companion object : GalleryScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun gridLayout(configure: GridLayoutScope.() -> Unit = {}) =
    v<GridLayout>(configure.bind(GridLayoutScope))
abstract class GridLayoutScope : ViewGroupScope() {
  fun alignmentMode(arg: Int): Unit = attr("alignmentMode", arg)
  fun columnCount(arg: Int): Unit = attr("columnCount", arg)
  fun columnOrderPreserved(arg: Boolean): Unit = attr("columnOrderPreserved", arg)
  fun orientation(arg: Int): Unit = attr("orientation", arg)
  fun rowCount(arg: Int): Unit = attr("rowCount", arg)
  fun rowOrderPreserved(arg: Boolean): Unit = attr("rowOrderPreserved", arg)
  fun useDefaultMargins(arg: Boolean): Unit = attr("useDefaultMargins", arg)
  companion object : GridLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun gridView(configure: GridViewScope.() -> Unit = {}) = v<GridView>(configure.bind(GridViewScope))
abstract class GridViewScope : AbsListViewScope() {
  fun columnWidth(arg: Int): Unit = attr("columnWidth", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalSpacing(arg: Int): Unit = attr("horizontalSpacing", arg)
  fun numColumns(arg: Int): Unit = attr("numColumns", arg)
  fun stretchMode(arg: Int): Unit = attr("stretchMode", arg)
  fun verticalSpacing(arg: Int): Unit = attr("verticalSpacing", arg)
  companion object : GridViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun horizontalScrollView(configure: HorizontalScrollViewScope.() -> Unit = {}) =
    v<HorizontalScrollView>(configure.bind(HorizontalScrollViewScope))
abstract class HorizontalScrollViewScope : FrameLayoutScope() {
  fun fillViewport(arg: Boolean): Unit = attr("fillViewport", arg)
  fun smoothScrollingEnabled(arg: Boolean): Unit = attr("smoothScrollingEnabled", arg)
  companion object : HorizontalScrollViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun imageButton(configure: ImageButtonScope.() -> Unit = {}) =
    v<ImageButton>(configure.bind(ImageButtonScope))
abstract class ImageButtonScope : ImageViewScope() {
  companion object : ImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun imageSwitcher(configure: ImageSwitcherScope.() -> Unit = {}) =
    v<ImageSwitcher>(configure.bind(ImageSwitcherScope))
abstract class ImageSwitcherScope : ViewSwitcherScope() {
  fun imageDrawable(arg: Drawable): Unit = attr("imageDrawable", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageURI(arg: Uri): Unit = attr("imageURI", arg)
  companion object : ImageSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun imageView(configure: ImageViewScope.() -> Unit = {}) =
    v<ImageView>(configure.bind(ImageViewScope))
abstract class ImageViewScope : ViewScope() {
  fun adjustViewBounds(arg: Boolean): Unit = attr("adjustViewBounds", arg)
  fun baseline(arg: Int): Unit = attr("baseline", arg)
  fun baselineAlignBottom(arg: Boolean): Unit = attr("baselineAlignBottom", arg)
  fun colorFilter(arg: ColorFilter): Unit = attr("colorFilter", arg)
  fun colorFilter(arg: Int): Unit = attr("colorFilter", arg)
  fun cropToPadding(arg: Boolean): Unit = attr("cropToPadding", arg)
  fun imageAlpha(arg: Int): Unit = attr("imageAlpha", arg)
  fun imageBitmap(arg: Bitmap): Unit = attr("imageBitmap", arg)
  fun imageDrawable(arg: Drawable?): Unit = attr("imageDrawable", arg)
  fun imageLevel(arg: Int): Unit = attr("imageLevel", arg)
  fun imageMatrix(arg: Matrix): Unit = attr("imageMatrix", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageURI(arg: Uri?): Unit = attr("imageURI", arg)
  fun maxHeight(arg: Int): Unit = attr("maxHeight", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun scaleType(arg: ImageView.ScaleType): Unit = attr("scaleType", arg)
  companion object : ImageViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

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
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun listView(configure: ListViewScope.() -> Unit = {}) = v<ListView>(configure.bind(ListViewScope))
abstract class ListViewScope : AbsListViewScope() {
  fun divider(arg: Drawable?): Unit = attr("divider", arg)
  fun dividerHeight(arg: Int): Unit = attr("dividerHeight", arg)
  fun footerDividersEnabled(arg: Boolean): Unit = attr("footerDividersEnabled", arg)
  fun headerDividersEnabled(arg: Boolean): Unit = attr("headerDividersEnabled", arg)
  fun itemsCanFocus(arg: Boolean): Unit = attr("itemsCanFocus", arg)
  fun overscrollFooter(arg: Drawable): Unit = attr("overscrollFooter", arg)
  fun overscrollHeader(arg: Drawable): Unit = attr("overscrollHeader", arg)
  companion object : ListViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun mediaController(configure: MediaControllerScope.() -> Unit = {}) =
    v<MediaController>(configure.bind(MediaControllerScope))
abstract class MediaControllerScope : FrameLayoutScope() {
  fun anchorView(arg: View): Unit = attr("anchorView", arg)
  fun mediaPlayer(arg: MediaController.MediaPlayerControl): Unit = attr("mediaPlayer", arg)
  companion object : MediaControllerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun multiAutoCompleteTextView(configure: MultiAutoCompleteTextViewScope.() -> Unit = {}) =
    v<MultiAutoCompleteTextView>(configure.bind(MultiAutoCompleteTextViewScope))
abstract class MultiAutoCompleteTextViewScope : AutoCompleteTextViewScope() {
  fun tokenizer(arg: MultiAutoCompleteTextView.Tokenizer): Unit = attr("tokenizer", arg)
  companion object : MultiAutoCompleteTextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun numberPicker(configure: NumberPickerScope.() -> Unit = {}) =
    v<NumberPicker>(configure.bind(NumberPickerScope))
abstract class NumberPickerScope : LinearLayoutScope() {
  fun displayedValues(arg: Array<String>): Unit = attr("displayedValues", arg)
  fun formatter(arg: NumberPicker.Formatter): Unit = attr("formatter", arg)
  fun maxValue(arg: Int): Unit = attr("maxValue", arg)
  fun minValue(arg: Int): Unit = attr("minValue", arg)
  fun onLongPressUpdateInterval(arg: Long): Unit = attr("onLongPressUpdateInterval", arg)
  fun onScroll(arg: ((arg0: NumberPicker, arg1: Int) -> Unit)?): Unit = attr("onScroll", arg)
  fun onValueChanged(arg: ((
    arg0: NumberPicker,
    arg1: Int,
    arg2: Int
  ) -> Unit)?): Unit = attr("onValueChanged", arg)
  fun value(arg: Int): Unit = attr("value", arg)
  fun wrapSelectorWheel(arg: Boolean): Unit = attr("wrapSelectorWheel", arg)
  companion object : NumberPickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun progressBar(configure: ProgressBarScope.() -> Unit = {}) =
    v<ProgressBar>(configure.bind(ProgressBarScope))
abstract class ProgressBarScope : ViewScope() {
  fun indeterminate(arg: Boolean): Unit = attr("indeterminate", arg)
  fun indeterminateDrawable(arg: Drawable): Unit = attr("indeterminateDrawable", arg)
  fun interpolator(arg: Interpolator): Unit = attr("interpolator", arg)
  fun max(arg: Int): Unit = attr("max", arg)
  fun progress(arg: Int): Unit = attr("progress", arg)
  fun progressDrawable(arg: Drawable): Unit = attr("progressDrawable", arg)
  fun secondaryProgress(arg: Int): Unit = attr("secondaryProgress", arg)
  companion object : ProgressBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun quickContactBadge(configure: QuickContactBadgeScope.() -> Unit = {}) =
    v<QuickContactBadge>(configure.bind(QuickContactBadgeScope))
abstract class QuickContactBadgeScope : ImageViewScope() {
  fun excludeMimes(arg: Array<String>): Unit = attr("excludeMimes", arg)
  fun mode(arg: Int): Unit = attr("mode", arg)
  companion object : QuickContactBadgeScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun radioButton(configure: RadioButtonScope.() -> Unit = {}) =
    v<RadioButton>(configure.bind(RadioButtonScope))
abstract class RadioButtonScope : CompoundButtonScope() {
  companion object : RadioButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun radioGroup(configure: RadioGroupScope.() -> Unit = {}) =
    v<RadioGroup>(configure.bind(RadioGroupScope))
abstract class RadioGroupScope : LinearLayoutScope() {
  fun onCheckedChange(arg: ((arg0: RadioGroup, arg1: Int) -> Unit)?): Unit = attr("onCheckedChange",
      arg)
  companion object : RadioGroupScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

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
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun relativeLayout(configure: RelativeLayoutScope.() -> Unit = {}) =
    v<RelativeLayout>(configure.bind(RelativeLayoutScope))
abstract class RelativeLayoutScope : ViewGroupScope() {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalGravity(arg: Int): Unit = attr("horizontalGravity", arg)
  fun ignoreGravity(arg: Int): Unit = attr("ignoreGravity", arg)
  fun verticalGravity(arg: Int): Unit = attr("verticalGravity", arg)
  companion object : RelativeLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun scrollView(configure: ScrollViewScope.() -> Unit = {}) =
    v<ScrollView>(configure.bind(ScrollViewScope))
abstract class ScrollViewScope : FrameLayoutScope() {
  fun fillViewport(arg: Boolean): Unit = attr("fillViewport", arg)
  fun smoothScrollingEnabled(arg: Boolean): Unit = attr("smoothScrollingEnabled", arg)
  companion object : ScrollViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun searchView(configure: SearchViewScope.() -> Unit = {}) =
    v<SearchView>(configure.bind(SearchViewScope))
abstract class SearchViewScope : LinearLayoutScope() {
  fun iconified(arg: Boolean): Unit = attr("iconified", arg)
  fun iconifiedByDefault(arg: Boolean): Unit = attr("iconifiedByDefault", arg)
  fun imeOptions(arg: Int): Unit = attr("imeOptions", arg)
  fun inputType(arg: Int): Unit = attr("inputType", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun onClose(arg: (() -> Boolean)?): Unit = attr("onClose", arg)
  fun onQueryTextFocusChange(arg: ((arg0: View, arg1: Boolean) -> Unit)?): Unit =
      attr("onQueryTextFocusChange", arg)
  fun onQueryText(arg: SearchView.OnQueryTextListener?): Unit = attr("onQueryText", arg)
  fun onSearchClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onSearchClick", arg)
  fun onSuggestion(arg: SearchView.OnSuggestionListener?): Unit = attr("onSuggestion", arg)
  fun queryHint(arg: CharSequence?): Unit = attr("queryHint", arg)
  fun queryRefinementEnabled(arg: Boolean): Unit = attr("queryRefinementEnabled", arg)
  fun searchableInfo(arg: SearchableInfo): Unit = attr("searchableInfo", arg)
  fun submitButtonEnabled(arg: Boolean): Unit = attr("submitButtonEnabled", arg)
  fun suggestionsAdapter(arg: CursorAdapter): Unit = attr("suggestionsAdapter", arg)
  companion object : SearchViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun seekBar(configure: SeekBarScope.() -> Unit = {}) = v<SeekBar>(configure.bind(SeekBarScope))
abstract class SeekBarScope : AbsSeekBarScope() {
  fun onSeekBarChange(arg: SeekBar.OnSeekBarChangeListener?): Unit = attr("onSeekBarChange", arg)
  companion object : SeekBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun slidingDrawer(configure: SlidingDrawerScope.() -> Unit = {}) =
    v<SlidingDrawer>(configure.bind(SlidingDrawerScope))
abstract class SlidingDrawerScope : ViewGroupScope() {
  fun onDrawerClose(arg: (() -> Unit)?): Unit = attr("onDrawerClose", arg)
  fun onDrawerOpen(arg: (() -> Unit)?): Unit = attr("onDrawerOpen", arg)
  fun onDrawerScroll(arg: SlidingDrawer.OnDrawerScrollListener?): Unit = attr("onDrawerScroll", arg)
  companion object : SlidingDrawerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun space(configure: SpaceScope.() -> Unit = {}) = v<Space>(configure.bind(SpaceScope))
abstract class SpaceScope : ViewScope() {
  companion object : SpaceScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun spinner(configure: SpinnerScope.() -> Unit = {}) = v<Spinner>(configure.bind(SpinnerScope))
abstract class SpinnerScope : AbsSpinnerScope() {
  fun dropDownHorizontalOffset(arg: Int): Unit = attr("dropDownHorizontalOffset", arg)
  fun dropDownVerticalOffset(arg: Int): Unit = attr("dropDownVerticalOffset", arg)
  fun dropDownWidth(arg: Int): Unit = attr("dropDownWidth", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun popupBackgroundDrawable(arg: Drawable): Unit = attr("popupBackgroundDrawable", arg)
  fun popupBackgroundResource(arg: Int): Unit = attr("popupBackgroundResource", arg)
  fun prompt(arg: CharSequence): Unit = attr("prompt", arg)
  fun promptId(arg: Int): Unit = attr("promptId", arg)
  companion object : SpinnerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun stackView(configure: StackViewScope.() -> Unit = {}) =
    v<StackView>(configure.bind(StackViewScope))
abstract class StackViewScope : AdapterViewAnimatorScope() {
  companion object : StackViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun switchView(configure: SwitchViewScope.() -> Unit = {}) =
    v<Switch>(configure.bind(SwitchViewScope))
abstract class SwitchViewScope : CompoundButtonScope() {
  fun switchMinWidth(arg: Int): Unit = attr("switchMinWidth", arg)
  fun switchPadding(arg: Int): Unit = attr("switchPadding", arg)
  fun switchTypeface(arg: Typeface): Unit = attr("switchTypeface", arg)
  fun textOff(arg: CharSequence): Unit = attr("textOff", arg)
  fun textOn(arg: CharSequence): Unit = attr("textOn", arg)
  fun thumbDrawable(arg: Drawable): Unit = attr("thumbDrawable", arg)
  fun thumbResource(arg: Int): Unit = attr("thumbResource", arg)
  fun thumbTextPadding(arg: Int): Unit = attr("thumbTextPadding", arg)
  fun trackDrawable(arg: Drawable): Unit = attr("trackDrawable", arg)
  fun trackResource(arg: Int): Unit = attr("trackResource", arg)
  companion object : SwitchViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun tabHost(configure: TabHostScope.() -> Unit = {}) = v<TabHost>(configure.bind(TabHostScope))
abstract class TabHostScope : FrameLayoutScope() {
  fun currentTab(arg: Int): Unit = attr("currentTab", arg)
  fun currentTabByTag(arg: String): Unit = attr("currentTabByTag", arg)
  fun onTabChanged(arg: ((arg0: String) -> Unit)?): Unit = attr("onTabChanged", arg)
  companion object : TabHostScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun tabWidget(configure: TabWidgetScope.() -> Unit = {}) =
    v<TabWidget>(configure.bind(TabWidgetScope))
abstract class TabWidgetScope : LinearLayoutScope() {
  fun currentTab(arg: Int): Unit = attr("currentTab", arg)
  fun dividerDrawable(arg: Int): Unit = attr("dividerDrawable", arg)
  fun leftStripDrawable(arg: Drawable?): Unit = attr("leftStripDrawable", arg)
  fun leftStripDrawable(arg: Int): Unit = attr("leftStripDrawable", arg)
  fun rightStripDrawable(arg: Drawable?): Unit = attr("rightStripDrawable", arg)
  fun rightStripDrawable(arg: Int): Unit = attr("rightStripDrawable", arg)
  fun stripEnabled(arg: Boolean): Unit = attr("stripEnabled", arg)
  companion object : TabWidgetScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun tableLayout(configure: TableLayoutScope.() -> Unit = {}) =
    v<TableLayout>(configure.bind(TableLayoutScope))
abstract class TableLayoutScope : LinearLayoutScope() {
  fun shrinkAllColumns(arg: Boolean): Unit = attr("shrinkAllColumns", arg)
  fun stretchAllColumns(arg: Boolean): Unit = attr("stretchAllColumns", arg)
  companion object : TableLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun tableRow(configure: TableRowScope.() -> Unit = {}) = v<TableRow>(configure.bind(TableRowScope))
abstract class TableRowScope : LinearLayoutScope() {
  companion object : TableRowScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun textClock(configure: TextClockScope.() -> Unit = {}) =
    v<TextClock>(configure.bind(TextClockScope))
abstract class TextClockScope : TextViewScope() {
  fun format12Hour(arg: CharSequence): Unit = attr("format12Hour", arg)
  fun format24Hour(arg: CharSequence): Unit = attr("format24Hour", arg)
  fun timeZone(arg: String): Unit = attr("timeZone", arg)
  companion object : TextClockScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun textSwitcher(configure: TextSwitcherScope.() -> Unit = {}) =
    v<TextSwitcher>(configure.bind(TextSwitcherScope))
abstract class TextSwitcherScope : ViewSwitcherScope() {
  fun currentText(arg: CharSequence): Unit = attr("currentText", arg)
  fun text(arg: CharSequence): Unit = attr("text", arg)
  companion object : TextSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun textView(configure: TextViewScope.() -> Unit = {}) = v<TextView>(configure.bind(TextViewScope))
abstract class TextViewScope : ViewScope() {
  fun allCaps(arg: Boolean): Unit = attr("allCaps", arg)
  fun autoLinkMask(arg: Int): Unit = attr("autoLinkMask", arg)
  fun compoundDrawablePadding(arg: Int): Unit = attr("compoundDrawablePadding", arg)
  fun cursorVisible(arg: Boolean): Unit = attr("cursorVisible", arg)
  fun customSelectionActionModeCallback(arg: ActionMode.Callback): Unit =
      attr("customSelectionActionModeCallback", arg)
  fun editableFactory(arg: Editable.Factory): Unit = attr("editableFactory", arg)
  fun ellipsize(arg: TextUtils.TruncateAt): Unit = attr("ellipsize", arg)
  fun ems(arg: Int): Unit = attr("ems", arg)
  fun error(arg: CharSequence): Unit = attr("error", arg)
  fun extractedText(arg: ExtractedText): Unit = attr("extractedText", arg)
  fun filters(arg: Array<InputFilter>): Unit = attr("filters", arg)
  fun freezesText(arg: Boolean): Unit = attr("freezesText", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun height(arg: Int): Unit = attr("height", arg)
  fun highlightColor(arg: Int): Unit = attr("highlightColor", arg)
  fun hint(arg: CharSequence): Unit = attr("hint", arg)
  fun hint(arg: Int): Unit = attr("hint", arg)
  fun hintTextColor(arg: Int): Unit = attr("hintTextColor", arg)
  fun hintTextColor(arg: ColorStateList): Unit = attr("hintTextColor", arg)
  fun horizontallyScrolling(arg: Boolean): Unit = attr("horizontallyScrolling", arg)
  fun imeOptions(arg: Int): Unit = attr("imeOptions", arg)
  fun includeFontPadding(arg: Boolean): Unit = attr("includeFontPadding", arg)
  fun inputType(arg: Int): Unit = attr("inputType", arg)
  fun keyListener(arg: KeyListener): Unit = attr("keyListener", arg)
  fun lines(arg: Int): Unit = attr("lines", arg)
  fun linkTextColor(arg: Int): Unit = attr("linkTextColor", arg)
  fun linkTextColor(arg: ColorStateList): Unit = attr("linkTextColor", arg)
  fun linksClickable(arg: Boolean): Unit = attr("linksClickable", arg)
  fun marqueeRepeatLimit(arg: Int): Unit = attr("marqueeRepeatLimit", arg)
  fun maxEms(arg: Int): Unit = attr("maxEms", arg)
  fun maxHeight(arg: Int): Unit = attr("maxHeight", arg)
  fun maxLines(arg: Int): Unit = attr("maxLines", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun minEms(arg: Int): Unit = attr("minEms", arg)
  fun minHeight(arg: Int): Unit = attr("minHeight", arg)
  fun minLines(arg: Int): Unit = attr("minLines", arg)
  fun minWidth(arg: Int): Unit = attr("minWidth", arg)
  fun movementMethod(arg: MovementMethod): Unit = attr("movementMethod", arg)
  fun onEditorAction(arg: ((
    arg0: TextView,
    arg1: Int,
    arg2: KeyEvent
  ) -> Boolean)?): Unit = attr("onEditorAction", arg)
  fun paintFlags(arg: Int): Unit = attr("paintFlags", arg)
  fun privateImeOptions(arg: String): Unit = attr("privateImeOptions", arg)
  fun rawInputType(arg: Int): Unit = attr("rawInputType", arg)
  fun scroller(arg: Scroller): Unit = attr("scroller", arg)
  fun selectAllOnFocus(arg: Boolean): Unit = attr("selectAllOnFocus", arg)
  fun singleLine(arg: Boolean): Unit = attr("singleLine", arg)
  fun spannableFactory(arg: Spannable.Factory): Unit = attr("spannableFactory", arg)
  fun text(arg: Int): Unit = attr("text", arg)
  fun textColor(arg: ColorStateList): Unit = attr("textColor", arg)
  fun textColor(arg: Int): Unit = attr("textColor", arg)
  fun textIsSelectable(arg: Boolean): Unit = attr("textIsSelectable", arg)
  fun textKeepState(arg: CharSequence): Unit = attr("textKeepState", arg)
  fun textLocale(arg: Locale): Unit = attr("textLocale", arg)
  fun textScaleX(arg: Float): Unit = attr("textScaleX", arg)
  fun transformationMethod(arg: TransformationMethod): Unit = attr("transformationMethod", arg)
  fun typeface(arg: Typeface?): Unit = attr("typeface", arg)
  fun width(arg: Int): Unit = attr("width", arg)
  companion object : TextViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun timePicker(configure: TimePickerScope.() -> Unit = {}) =
    v<TimePicker>(configure.bind(TimePickerScope))
abstract class TimePickerScope : FrameLayoutScope() {
  fun currentHour(arg: Int): Unit = attr("currentHour", arg)
  fun currentMinute(arg: Int): Unit = attr("currentMinute", arg)
  fun is24HourView(arg: Boolean): Unit = attr("is24HourView", arg)
  fun onTimeChanged(arg: ((
    arg0: TimePicker,
    arg1: Int,
    arg2: Int
  ) -> Unit)?): Unit = attr("onTimeChanged", arg)
  companion object : TimePickerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun toggleButton(configure: ToggleButtonScope.() -> Unit = {}) =
    v<ToggleButton>(configure.bind(ToggleButtonScope))
abstract class ToggleButtonScope : CompoundButtonScope() {
  fun textOff(arg: CharSequence): Unit = attr("textOff", arg)
  fun textOn(arg: CharSequence): Unit = attr("textOn", arg)
  companion object : ToggleButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun twoLineListItem(configure: TwoLineListItemScope.() -> Unit = {}) =
    v<TwoLineListItem>(configure.bind(TwoLineListItemScope))
abstract class TwoLineListItemScope : RelativeLayoutScope() {
  companion object : TwoLineListItemScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun videoView(configure: VideoViewScope.() -> Unit = {}) =
    v<VideoView>(configure.bind(VideoViewScope))
abstract class VideoViewScope : SurfaceViewScope() {
  fun mediaController(arg: MediaController): Unit = attr("mediaController", arg)
  fun onCompletion(arg: ((arg0: MediaPlayer) -> Unit)?): Unit = attr("onCompletion", arg)
  fun onError(arg: ((
    arg0: MediaPlayer,
    arg1: Int,
    arg2: Int
  ) -> Boolean)?): Unit = attr("onError", arg)
  fun onInfo(arg: ((
    arg0: MediaPlayer,
    arg1: Int,
    arg2: Int
  ) -> Boolean)?): Unit = attr("onInfo", arg)
  fun onPrepared(arg: ((arg0: MediaPlayer) -> Unit)?): Unit = attr("onPrepared", arg)
  fun videoPath(arg: String): Unit = attr("videoPath", arg)
  fun videoURI(arg: Uri): Unit = attr("videoURI", arg)
  companion object : VideoViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun viewAnimator(configure: ViewAnimatorScope.() -> Unit = {}) =
    v<ViewAnimator>(configure.bind(ViewAnimatorScope))
abstract class ViewAnimatorScope : FrameLayoutScope() {
  fun animateFirstView(arg: Boolean): Unit = attr("animateFirstView", arg)
  fun displayedChild(arg: Int): Unit = attr("displayedChild", arg)
  fun inAnimation(arg: Animation): Unit = attr("inAnimation", arg)
  fun outAnimation(arg: Animation): Unit = attr("outAnimation", arg)
  companion object : ViewAnimatorScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun viewFlipper(configure: ViewFlipperScope.() -> Unit = {}) =
    v<ViewFlipper>(configure.bind(ViewFlipperScope))
abstract class ViewFlipperScope : ViewAnimatorScope() {
  fun autoStart(arg: Boolean): Unit = attr("autoStart", arg)
  fun flipInterval(arg: Int): Unit = attr("flipInterval", arg)
  companion object : ViewFlipperScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun viewSwitcher(configure: ViewSwitcherScope.() -> Unit = {}) =
    v<ViewSwitcher>(configure.bind(ViewSwitcherScope))
abstract class ViewSwitcherScope : ViewAnimatorScope() {
  fun factory(arg: ViewSwitcher.ViewFactory): Unit = attr("factory", arg)
  companion object : ViewSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun zoomButton(configure: ZoomButtonScope.() -> Unit = {}) =
    v<ZoomButton>(configure.bind(ZoomButtonScope))
abstract class ZoomButtonScope : ImageButtonScope() {
  fun zoomSpeed(arg: Long): Unit = attr("zoomSpeed", arg)
  companion object : ZoomButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

fun zoomControls(configure: ZoomControlsScope.() -> Unit = {}) =
    v<ZoomControls>(configure.bind(ZoomControlsScope))
abstract class ZoomControlsScope : LinearLayoutScope() {
  fun isZoomInEnabled(arg: Boolean): Unit = attr("isZoomInEnabled", arg)
  fun isZoomOutEnabled(arg: Boolean): Unit = attr("isZoomOutEnabled", arg)
  fun onZoomInClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onZoomInClick", arg)
  fun onZoomOutClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onZoomOutClick", arg)
  fun zoomSpeed(arg: Long): Unit = attr("zoomSpeed", arg)
  companion object : ZoomControlsScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)}
  }
}

/**
 * DSL for creating views and settings their attributes.
 * This file has been generated by
 * {@code gradle generateSDK19DSL}
 * It contains views and their setters from API level 19.
 * Please, don't edit it manually unless for debugging.
 */
object SdkSetter : Anvil.AttributeSetter<Any?> {
  init {
    Anvil.registerAttributeSetter(this)
    Anvil.registerAttributeSetter(CustomDslSetter)
  }

  override fun set(
    v: View,
    name: String,
    arg: Any?,
    old: Any?
  ): Boolean = when (name) {
    "activity" -> when {
      v is FragmentBreadCrumbs && arg is Activity -> {
        v.setActivity(arg)
        true
      }
      else -> false
    }
    "maxVisible" -> when {
      v is FragmentBreadCrumbs && arg is Int -> {
        v.setMaxVisible(arg)
        true
      }
      else -> false
    }
    "onBreadCrumbClick" -> when {
      v is FragmentBreadCrumbs -> when {
        arg == null -> {
          v.setOnBreadCrumbClickListener(null as? FragmentBreadCrumbs.OnBreadCrumbClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: FragmentManager.BackStackEntry, arg1: Int) -> Boolean
          v.setOnBreadCrumbClickListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "extendedSettingsClickListener" -> when {
      v is MediaRouteButton && arg is View.OnClickListener -> {
        v.setExtendedSettingsClickListener(arg)
        true
      }
      else -> false
    }
    "routeTypes" -> when {
      v is MediaRouteButton && arg is Int -> {
        v.setRouteTypes(arg)
        true
      }
      else -> false
    }
    "eventsInterceptionEnabled" -> when {
      v is GestureOverlayView && arg is Boolean -> {
        v.setEventsInterceptionEnabled(arg)
        true
      }
      else -> false
    }
    "fadeEnabled" -> when {
      v is GestureOverlayView && arg is Boolean -> {
        v.setFadeEnabled(arg)
        true
      }
      else -> false
    }
    "fadeOffset" -> when {
      v is GestureOverlayView && arg is Long -> {
        v.setFadeOffset(arg)
        true
      }
      else -> false
    }
    "gesture" -> when {
      v is GestureOverlayView && arg is Gesture -> {
        v.setGesture(arg)
        true
      }
      else -> false
    }
    "gestureColor" -> when {
      v is GestureOverlayView && arg is Int -> {
        v.setGestureColor(arg)
        true
      }
      else -> false
    }
    "gestureStrokeAngleThreshold" -> when {
      v is GestureOverlayView && arg is Float -> {
        v.setGestureStrokeAngleThreshold(arg)
        true
      }
      else -> false
    }
    "gestureStrokeLengthThreshold" -> when {
      v is GestureOverlayView && arg is Float -> {
        v.setGestureStrokeLengthThreshold(arg)
        true
      }
      else -> false
    }
    "gestureStrokeSquarenessTreshold" -> when {
      v is GestureOverlayView && arg is Float -> {
        v.setGestureStrokeSquarenessTreshold(arg)
        true
      }
      else -> false
    }
    "gestureStrokeType" -> when {
      v is GestureOverlayView && arg is Int -> {
        v.setGestureStrokeType(arg)
        true
      }
      else -> false
    }
    "gestureStrokeWidth" -> when {
      v is GestureOverlayView && arg is Float -> {
        v.setGestureStrokeWidth(arg)
        true
      }
      else -> false
    }
    "gestureVisible" -> when {
      v is GestureOverlayView && arg is Boolean -> {
        v.setGestureVisible(arg)
        true
      }
      else -> false
    }
    "orientation" -> when {
      v is GestureOverlayView && arg is Int -> {
        v.setOrientation(arg)
        true
      }
      v is GridLayout && arg is Int -> {
        v.setOrientation(arg)
        true
      }
      v is LinearLayout && arg is Int -> {
        v.setOrientation(arg)
        true
      }
      else -> false
    }
    "uncertainGestureColor" -> when {
      v is GestureOverlayView && arg is Int -> {
        v.setUncertainGestureColor(arg)
        true
      }
      else -> false
    }
    "keyboard" -> when {
      v is KeyboardView && arg is Keyboard -> {
        v.setKeyboard(arg)
        true
      }
      else -> false
    }
    "onKeyboardAction" -> when {
      v is KeyboardView -> when {
        arg == null -> {
          v.setOnKeyboardActionListener(null as? KeyboardView.OnKeyboardActionListener?)
          true
        }
        arg is KeyboardView.OnKeyboardActionListener -> {
          v.setOnKeyboardActionListener(object : KeyboardView.OnKeyboardActionListener {
            override fun onKey(arg0: Int, arg1: IntArray): Unit = arg.onKey(arg0, arg1).also {
                Anvil.render() }

            override fun onPress(arg0: Int): Unit = arg.onPress(arg0).also { Anvil.render() }

            override fun onRelease(arg0: Int): Unit = arg.onRelease(arg0).also { Anvil.render() }

            override fun onText(arg0: CharSequence): Unit = arg.onText(arg0).also { Anvil.render() }

            override fun swipeDown(): Unit = arg.swipeDown().also { Anvil.render() }

            override fun swipeLeft(): Unit = arg.swipeLeft().also { Anvil.render() }

            override fun swipeRight(): Unit = arg.swipeRight().also { Anvil.render() }

            override fun swipeUp(): Unit = arg.swipeUp().also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "popupParent" -> when {
      v is KeyboardView && arg is View -> {
        v.setPopupParent(arg)
        true
      }
      else -> false
    }
    "previewEnabled" -> when {
      v is KeyboardView && arg is Boolean -> {
        v.setPreviewEnabled(arg)
        true
      }
      else -> false
    }
    "proximityCorrectionEnabled" -> when {
      v is KeyboardView && arg is Boolean -> {
        v.setProximityCorrectionEnabled(arg)
        true
      }
      else -> false
    }
    "shifted" -> when {
      v is KeyboardView && arg is Boolean -> {
        v.setShifted(arg)
        true
      }
      else -> false
    }
    "verticalCorrection" -> when {
      v is KeyboardView && arg is Int -> {
        v.setVerticalCorrection(arg)
        true
      }
      else -> false
    }
    "debugFlags" -> when {
      v is GLSurfaceView && arg is Int -> {
        v.setDebugFlags(arg)
        true
      }
      else -> false
    }
    "eGLConfigChooser" -> when {
      v is GLSurfaceView && arg is Boolean -> {
        v.setEGLConfigChooser(arg)
        true
      }
      v is GLSurfaceView && arg is GLSurfaceView.EGLConfigChooser -> {
        v.setEGLConfigChooser(arg)
        true
      }
      else -> false
    }
    "eGLContextClientVersion" -> when {
      v is GLSurfaceView && arg is Int -> {
        v.setEGLContextClientVersion(arg)
        true
      }
      else -> false
    }
    "eGLContextFactory" -> when {
      v is GLSurfaceView && arg is GLSurfaceView.EGLContextFactory -> {
        v.setEGLContextFactory(arg)
        true
      }
      else -> false
    }
    "eGLWindowSurfaceFactory" -> when {
      v is GLSurfaceView && arg is GLSurfaceView.EGLWindowSurfaceFactory -> {
        v.setEGLWindowSurfaceFactory(arg)
        true
      }
      else -> false
    }
    "gLWrapper" -> when {
      v is GLSurfaceView && arg is GLSurfaceView.GLWrapper -> {
        v.setGLWrapper(arg)
        true
      }
      else -> false
    }
    "preserveEGLContextOnPause" -> when {
      v is GLSurfaceView && arg is Boolean -> {
        v.setPreserveEGLContextOnPause(arg)
        true
      }
      else -> false
    }
    "renderMode" -> when {
      v is GLSurfaceView && arg is Int -> {
        v.setRenderMode(arg)
        true
      }
      else -> false
    }
    "renderer" -> when {
      v is GLSurfaceView && arg is GLSurfaceView.Renderer -> {
        v.setRenderer(arg)
        true
      }
      else -> false
    }
    "secure" -> when {
      v is SurfaceView && arg is Boolean -> {
        v.setSecure(arg)
        true
      }
      else -> false
    }
    "zOrderMediaOverlay" -> when {
      v is SurfaceView && arg is Boolean -> {
        v.setZOrderMediaOverlay(arg)
        true
      }
      else -> false
    }
    "zOrderOnTop" -> when {
      v is SurfaceView && arg is Boolean -> {
        v.setZOrderOnTop(arg)
        true
      }
      else -> false
    }
    "opaque" -> when {
      v is TextureView && arg is Boolean -> {
        v.setOpaque(arg)
        true
      }
      else -> false
    }
    "surfaceTexture" -> when {
      v is TextureView && arg is SurfaceTexture -> {
        v.setSurfaceTexture(arg)
        true
      }
      else -> false
    }
    "surfaceTextureListener" -> when {
      v is TextureView && arg is TextureView.SurfaceTextureListener -> {
        v.setSurfaceTextureListener(arg)
        true
      }
      else -> false
    }
    "transform" -> when {
      v is TextureView && arg is Matrix -> {
        v.setTransform(arg)
        true
      }
      else -> false
    }
    "accessibilityDelegate" -> when {
      (arg == null || arg is View.AccessibilityDelegate) -> {
        v.setAccessibilityDelegate(arg as View.AccessibilityDelegate)
        true
      }
      else -> false
    }
    "accessibilityLiveRegion" -> when {
      arg is Int -> {
        v.setAccessibilityLiveRegion(arg)
        true
      }
      else -> false
    }
    "activated" -> when {
      arg is Boolean -> {
        v.setActivated(arg)
        true
      }
      else -> false
    }
    "alpha" -> when {
      arg is Float -> {
        v.setAlpha(arg)
        true
      }
      else -> false
    }
    "animation" -> when {
      arg is Animation -> {
        v.setAnimation(arg)
        true
      }
      else -> false
    }
    "background" -> when {
      arg is Drawable -> {
        v.setBackground(arg)
        true
      }
      else -> false
    }
    "backgroundColor" -> when {
      arg is Int -> {
        v.setBackgroundColor(arg)
        true
      }
      else -> false
    }
    "backgroundResource" -> when {
      arg is Int -> {
        v.setBackgroundResource(arg)
        true
      }
      else -> false
    }
    "bottom" -> when {
      arg is Int -> {
        v.setBottom(arg)
        true
      }
      else -> false
    }
    "cameraDistance" -> when {
      arg is Float -> {
        v.setCameraDistance(arg)
        true
      }
      else -> false
    }
    "clickable" -> when {
      arg is Boolean -> {
        v.setClickable(arg)
        true
      }
      else -> false
    }
    "clipBounds" -> when {
      arg is Rect -> {
        v.setClipBounds(arg)
        true
      }
      else -> false
    }
    "contentDescription" -> when {
      arg is CharSequence -> {
        v.setContentDescription(arg)
        true
      }
      else -> false
    }
    "drawingCacheBackgroundColor" -> when {
      arg is Int -> {
        v.setDrawingCacheBackgroundColor(arg)
        true
      }
      else -> false
    }
    "drawingCacheEnabled" -> when {
      arg is Boolean -> {
        v.setDrawingCacheEnabled(arg)
        true
      }
      else -> false
    }
    "drawingCacheQuality" -> when {
      arg is Int -> {
        v.setDrawingCacheQuality(arg)
        true
      }
      else -> false
    }
    "duplicateParentStateEnabled" -> when {
      arg is Boolean -> {
        v.setDuplicateParentStateEnabled(arg)
        true
      }
      else -> false
    }
    "enabled" -> when {
      arg is Boolean -> {
        v.setEnabled(arg)
        true
      }
      else -> false
    }
    "fadingEdgeLength" -> when {
      arg is Int -> {
        v.setFadingEdgeLength(arg)
        true
      }
      else -> false
    }
    "filterTouchesWhenObscured" -> when {
      arg is Boolean -> {
        v.setFilterTouchesWhenObscured(arg)
        true
      }
      else -> false
    }
    "fitsSystemWindows" -> when {
      arg is Boolean -> {
        v.setFitsSystemWindows(arg)
        true
      }
      else -> false
    }
    "focusable" -> when {
      arg is Boolean -> {
        v.setFocusable(arg)
        true
      }
      else -> false
    }
    "focusableInTouchMode" -> when {
      arg is Boolean -> {
        v.setFocusableInTouchMode(arg)
        true
      }
      else -> false
    }
    "hapticFeedbackEnabled" -> when {
      arg is Boolean -> {
        v.setHapticFeedbackEnabled(arg)
        true
      }
      else -> false
    }
    "hasTransientState" -> when {
      arg is Boolean -> {
        v.setHasTransientState(arg)
        true
      }
      else -> false
    }
    "horizontalFadingEdgeEnabled" -> when {
      arg is Boolean -> {
        v.setHorizontalFadingEdgeEnabled(arg)
        true
      }
      else -> false
    }
    "horizontalScrollBarEnabled" -> when {
      arg is Boolean -> {
        v.setHorizontalScrollBarEnabled(arg)
        true
      }
      else -> false
    }
    "hovered" -> when {
      arg is Boolean -> {
        v.setHovered(arg)
        true
      }
      else -> false
    }
    "id" -> when {
      arg is Int -> {
        v.setId(arg)
        true
      }
      else -> false
    }
    "importantForAccessibility" -> when {
      arg is Int -> {
        v.setImportantForAccessibility(arg)
        true
      }
      else -> false
    }
    "keepScreenOn" -> when {
      arg is Boolean -> {
        v.setKeepScreenOn(arg)
        true
      }
      else -> false
    }
    "labelFor" -> when {
      arg is Int -> {
        v.setLabelFor(arg)
        true
      }
      else -> false
    }
    "layerPaint" -> when {
      (arg == null || arg is Paint) -> {
        v.setLayerPaint(arg as Paint)
        true
      }
      else -> false
    }
    "layoutDirection" -> when {
      arg is Int -> {
        v.setLayoutDirection(arg)
        true
      }
      else -> false
    }
    "layoutParams" -> when {
      arg is ViewGroup.LayoutParams -> {
        v.setLayoutParams(arg)
        true
      }
      else -> false
    }
    "left" -> when {
      arg is Int -> {
        v.setLeft(arg)
        true
      }
      else -> false
    }
    "longClickable" -> when {
      arg is Boolean -> {
        v.setLongClickable(arg)
        true
      }
      else -> false
    }
    "minimumHeight" -> when {
      arg is Int -> {
        v.setMinimumHeight(arg)
        true
      }
      else -> false
    }
    "minimumWidth" -> when {
      arg is Int -> {
        v.setMinimumWidth(arg)
        true
      }
      else -> false
    }
    "nextFocusDownId" -> when {
      arg is Int -> {
        v.setNextFocusDownId(arg)
        true
      }
      else -> false
    }
    "nextFocusForwardId" -> when {
      arg is Int -> {
        v.setNextFocusForwardId(arg)
        true
      }
      else -> false
    }
    "nextFocusLeftId" -> when {
      arg is Int -> {
        v.setNextFocusLeftId(arg)
        true
      }
      else -> false
    }
    "nextFocusRightId" -> when {
      arg is Int -> {
        v.setNextFocusRightId(arg)
        true
      }
      else -> false
    }
    "nextFocusUpId" -> when {
      arg is Int -> {
        v.setNextFocusUpId(arg)
        true
      }
      else -> false
    }
    "onClick" -> when {
      arg == null -> {
        v.setOnClickListener(null as? View.OnClickListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View) -> Unit
        v.setOnClickListener { arg0 ->
          arg(arg0).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onCreateContextMenu" -> when {
      arg == null -> {
        v.setOnCreateContextMenuListener(null as? View.OnCreateContextMenuListener?)
        true
      }
      arg is Function<*> -> {
        arg as (
          arg0: ContextMenu,
          arg1: View,
          arg2: ContextMenu.ContextMenuInfo
        ) -> Unit
        v.setOnCreateContextMenuListener { arg0, arg1, arg2 ->
          arg(arg0, arg1, arg2).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onDrag" -> when {
      arg == null -> {
        v.setOnDragListener(null as? View.OnDragListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View, arg1: DragEvent) -> Boolean
        v.setOnDragListener { arg0, arg1 ->
          arg(arg0, arg1).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onFocusChange" -> when {
      arg == null -> {
        v.setOnFocusChangeListener(null as? View.OnFocusChangeListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View, arg1: Boolean) -> Unit
        v.setOnFocusChangeListener { arg0, arg1 ->
          arg(arg0, arg1).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onGenericMotion" -> when {
      arg == null -> {
        v.setOnGenericMotionListener(null as? View.OnGenericMotionListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View, arg1: MotionEvent) -> Boolean
        v.setOnGenericMotionListener { arg0, arg1 ->
          arg(arg0, arg1).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onHover" -> when {
      arg == null -> {
        v.setOnHoverListener(null as? View.OnHoverListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View, arg1: MotionEvent) -> Boolean
        v.setOnHoverListener { arg0, arg1 ->
          arg(arg0, arg1).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onKey" -> when {
      arg == null -> {
        v.setOnKeyListener(null as? View.OnKeyListener?)
        true
      }
      arg is Function<*> -> {
        arg as (
          arg0: View,
          arg1: Int,
          arg2: KeyEvent
        ) -> Boolean
        v.setOnKeyListener { arg0, arg1, arg2 ->
          arg(arg0, arg1, arg2).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onLongClick" -> when {
      arg == null -> {
        v.setOnLongClickListener(null as? View.OnLongClickListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View) -> Boolean
        v.setOnLongClickListener { arg0 ->
          arg(arg0).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onSystemUiVisibilityChange" -> when {
      arg == null -> {
        v.setOnSystemUiVisibilityChangeListener(null as? View.OnSystemUiVisibilityChangeListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: Int) -> Unit
        v.setOnSystemUiVisibilityChangeListener { arg0 ->
          arg(arg0).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "onTouch" -> when {
      arg == null -> {
        v.setOnTouchListener(null as? View.OnTouchListener?)
        true
      }
      arg is Function<*> -> {
        arg as (arg0: View, arg1: MotionEvent) -> Boolean
        v.setOnTouchListener { arg0, arg1 ->
          arg(arg0, arg1).also { Anvil.render() }
        }
        true
      }
      else -> false
    }
    "overScrollMode" -> when {
      arg is Int -> {
        v.setOverScrollMode(arg)
        true
      }
      else -> false
    }
    "pivotX" -> when {
      arg is Float -> {
        v.setPivotX(arg)
        true
      }
      else -> false
    }
    "pivotY" -> when {
      arg is Float -> {
        v.setPivotY(arg)
        true
      }
      else -> false
    }
    "pressed" -> when {
      arg is Boolean -> {
        v.setPressed(arg)
        true
      }
      else -> false
    }
    "right" -> when {
      arg is Int -> {
        v.setRight(arg)
        true
      }
      else -> false
    }
    "rotation" -> when {
      arg is Float -> {
        v.setRotation(arg)
        true
      }
      else -> false
    }
    "rotationX" -> when {
      arg is Float -> {
        v.setRotationX(arg)
        true
      }
      else -> false
    }
    "rotationY" -> when {
      arg is Float -> {
        v.setRotationY(arg)
        true
      }
      else -> false
    }
    "saveEnabled" -> when {
      arg is Boolean -> {
        v.setSaveEnabled(arg)
        true
      }
      else -> false
    }
    "saveFromParentEnabled" -> when {
      arg is Boolean -> {
        v.setSaveFromParentEnabled(arg)
        true
      }
      else -> false
    }
    "scaleX" -> when {
      arg is Float -> {
        v.setScaleX(arg)
        true
      }
      else -> false
    }
    "scaleY" -> when {
      arg is Float -> {
        v.setScaleY(arg)
        true
      }
      else -> false
    }
    "scrollBarDefaultDelayBeforeFade" -> when {
      arg is Int -> {
        v.setScrollBarDefaultDelayBeforeFade(arg)
        true
      }
      else -> false
    }
    "scrollBarFadeDuration" -> when {
      arg is Int -> {
        v.setScrollBarFadeDuration(arg)
        true
      }
      else -> false
    }
    "scrollBarSize" -> when {
      arg is Int -> {
        v.setScrollBarSize(arg)
        true
      }
      else -> false
    }
    "scrollBarStyle" -> when {
      arg is Int -> {
        v.setScrollBarStyle(arg)
        true
      }
      else -> false
    }
    "scrollContainer" -> when {
      arg is Boolean -> {
        v.setScrollContainer(arg)
        true
      }
      else -> false
    }
    "scrollX" -> when {
      arg is Int -> {
        v.setScrollX(arg)
        true
      }
      else -> false
    }
    "scrollY" -> when {
      arg is Int -> {
        v.setScrollY(arg)
        true
      }
      else -> false
    }
    "scrollbarFadingEnabled" -> when {
      arg is Boolean -> {
        v.setScrollbarFadingEnabled(arg)
        true
      }
      else -> false
    }
    "selected" -> when {
      arg is Boolean -> {
        v.setSelected(arg)
        true
      }
      else -> false
    }
    "soundEffectsEnabled" -> when {
      arg is Boolean -> {
        v.setSoundEffectsEnabled(arg)
        true
      }
      else -> false
    }
    "systemUiVisibility" -> when {
      arg is Int -> {
        v.setSystemUiVisibility(arg)
        true
      }
      else -> false
    }
    "tag" -> when {
      arg is Any -> {
        v.setTag(arg)
        true
      }
      else -> false
    }
    "textAlignment" -> when {
      arg is Int -> {
        v.setTextAlignment(arg)
        true
      }
      else -> false
    }
    "textDirection" -> when {
      arg is Int -> {
        v.setTextDirection(arg)
        true
      }
      else -> false
    }
    "top" -> when {
      arg is Int -> {
        v.setTop(arg)
        true
      }
      else -> false
    }
    "touchDelegate" -> when {
      arg is TouchDelegate -> {
        v.setTouchDelegate(arg)
        true
      }
      else -> false
    }
    "translationX" -> when {
      arg is Float -> {
        v.setTranslationX(arg)
        true
      }
      else -> false
    }
    "translationY" -> when {
      arg is Float -> {
        v.setTranslationY(arg)
        true
      }
      else -> false
    }
    "verticalFadingEdgeEnabled" -> when {
      arg is Boolean -> {
        v.setVerticalFadingEdgeEnabled(arg)
        true
      }
      else -> false
    }
    "verticalScrollBarEnabled" -> when {
      arg is Boolean -> {
        v.setVerticalScrollBarEnabled(arg)
        true
      }
      else -> false
    }
    "verticalScrollbarPosition" -> when {
      arg is Int -> {
        v.setVerticalScrollbarPosition(arg)
        true
      }
      else -> false
    }
    "visibility" -> when {
      arg is Int -> {
        v.setVisibility(arg)
        true
      }
      else -> false
    }
    "willNotCacheDrawing" -> when {
      arg is Boolean -> {
        v.setWillNotCacheDrawing(arg)
        true
      }
      else -> false
    }
    "willNotDraw" -> when {
      arg is Boolean -> {
        v.setWillNotDraw(arg)
        true
      }
      else -> false
    }
    "x" -> when {
      arg is Float -> {
        v.setX(arg)
        true
      }
      else -> false
    }
    "y" -> when {
      arg is Float -> {
        v.setY(arg)
        true
      }
      else -> false
    }
    "addStatesFromChildren" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setAddStatesFromChildren(arg)
        true
      }
      else -> false
    }
    "alwaysDrawnWithCacheEnabled" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setAlwaysDrawnWithCacheEnabled(arg)
        true
      }
      else -> false
    }
    "animationCacheEnabled" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setAnimationCacheEnabled(arg)
        true
      }
      else -> false
    }
    "clipChildren" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setClipChildren(arg)
        true
      }
      else -> false
    }
    "clipToPadding" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setClipToPadding(arg)
        true
      }
      else -> false
    }
    "descendantFocusability" -> when {
      v is ViewGroup && arg is Int -> {
        v.setDescendantFocusability(arg)
        true
      }
      else -> false
    }
    "layoutAnimation" -> when {
      v is ViewGroup && arg is LayoutAnimationController -> {
        v.setLayoutAnimation(arg)
        true
      }
      else -> false
    }
    "layoutAnimationListener" -> when {
      v is ViewGroup && arg is Animation.AnimationListener -> {
        v.setLayoutAnimationListener(arg)
        true
      }
      else -> false
    }
    "layoutMode" -> when {
      v is ViewGroup && arg is Int -> {
        v.setLayoutMode(arg)
        true
      }
      else -> false
    }
    "layoutTransition" -> when {
      v is ViewGroup && arg is LayoutTransition -> {
        v.setLayoutTransition(arg)
        true
      }
      else -> false
    }
    "motionEventSplittingEnabled" -> when {
      v is ViewGroup && arg is Boolean -> {
        v.setMotionEventSplittingEnabled(arg)
        true
      }
      else -> false
    }
    "onHierarchyChange" -> when {
      v is ViewGroup -> when {
        arg == null -> {
          v.setOnHierarchyChangeListener(null as? ViewGroup.OnHierarchyChangeListener?)
          true
        }
        arg is ViewGroup.OnHierarchyChangeListener -> {
          v.setOnHierarchyChangeListener(object : ViewGroup.OnHierarchyChangeListener {
            override fun onChildViewAdded(arg0: View, arg1: View): Unit = arg.onChildViewAdded(arg0,
                arg1).also { Anvil.render() }

            override fun onChildViewRemoved(arg0: View, arg1: View): Unit =
                arg.onChildViewRemoved(arg0, arg1).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "persistentDrawingCache" -> when {
      v is ViewGroup && arg is Int -> {
        v.setPersistentDrawingCache(arg)
        true
      }
      else -> false
    }
    "inflatedId" -> when {
      v is ViewStub && arg is Int -> {
        v.setInflatedId(arg)
        true
      }
      else -> false
    }
    "layoutInflater" -> when {
      v is ViewStub && arg is LayoutInflater -> {
        v.setLayoutInflater(arg)
        true
      }
      else -> false
    }
    "layoutResource" -> when {
      v is ViewStub && arg is Int -> {
        v.setLayoutResource(arg)
        true
      }
      else -> false
    }
    "onInflate" -> when {
      v is ViewStub -> when {
        arg == null -> {
          v.setOnInflateListener(null as? ViewStub.OnInflateListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: ViewStub, arg1: View) -> Unit
          v.setOnInflateListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "downloadListener" -> when {
      v is WebView && arg is DownloadListener -> {
        v.setDownloadListener(arg)
        true
      }
      else -> false
    }
    "findListener" -> when {
      v is WebView && arg is WebView.FindListener -> {
        v.setFindListener(arg)
        true
      }
      else -> false
    }
    "horizontalScrollbarOverlay" -> when {
      v is WebView && arg is Boolean -> {
        v.setHorizontalScrollbarOverlay(arg)
        true
      }
      else -> false
    }
    "initialScale" -> when {
      v is WebView && arg is Int -> {
        v.setInitialScale(arg)
        true
      }
      else -> false
    }
    "networkAvailable" -> when {
      v is WebView && arg is Boolean -> {
        v.setNetworkAvailable(arg)
        true
      }
      else -> false
    }
    "verticalScrollbarOverlay" -> when {
      v is WebView && arg is Boolean -> {
        v.setVerticalScrollbarOverlay(arg)
        true
      }
      else -> false
    }
    "webChromeClient" -> when {
      v is WebView && arg is WebChromeClient -> {
        v.setWebChromeClient(arg)
        true
      }
      else -> false
    }
    "webViewClient" -> when {
      v is WebView && arg is WebViewClient -> {
        v.setWebViewClient(arg)
        true
      }
      else -> false
    }
    "cacheColorHint" -> when {
      v is AbsListView && arg is Int -> {
        v.setCacheColorHint(arg)
        true
      }
      else -> false
    }
    "choiceMode" -> when {
      v is AbsListView && arg is Int -> {
        v.setChoiceMode(arg)
        true
      }
      else -> false
    }
    "drawSelectorOnTop" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setDrawSelectorOnTop(arg)
        true
      }
      else -> false
    }
    "fastScrollAlwaysVisible" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setFastScrollAlwaysVisible(arg)
        true
      }
      else -> false
    }
    "fastScrollEnabled" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setFastScrollEnabled(arg)
        true
      }
      else -> false
    }
    "filterText" -> when {
      v is AbsListView && arg is String -> {
        v.setFilterText(arg)
        true
      }
      else -> false
    }
    "friction" -> when {
      v is AbsListView && arg is Float -> {
        v.setFriction(arg)
        true
      }
      else -> false
    }
    "multiChoiceModeListener" -> when {
      v is AbsListView && arg is AbsListView.MultiChoiceModeListener -> {
        v.setMultiChoiceModeListener(arg)
        true
      }
      else -> false
    }
    "onScroll" -> when {
      v is AbsListView -> when {
        arg == null -> {
          v.setOnScrollListener(null as? AbsListView.OnScrollListener?)
          true
        }
        arg is AbsListView.OnScrollListener -> {
          v.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
              arg0: AbsListView,
              arg1: Int,
              arg2: Int,
              arg3: Int
            ): Unit = arg.onScroll(arg0, arg1, arg2, arg3).also { Anvil.render() }

            override fun onScrollStateChanged(arg0: AbsListView, arg1: Int): Unit =
                arg.onScrollStateChanged(arg0, arg1).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      v is NumberPicker -> when {
        arg == null -> {
          v.setOnScrollListener(null as? NumberPicker.OnScrollListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: NumberPicker, arg1: Int) -> Unit
          v.setOnScrollListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "recyclerListener" -> when {
      v is AbsListView && arg is AbsListView.RecyclerListener -> {
        v.setRecyclerListener(arg)
        true
      }
      else -> false
    }
    "remoteViewsAdapter" -> when {
      v is AbsListView && arg is Intent -> {
        v.setRemoteViewsAdapter(arg)
        true
      }
      v is AdapterViewAnimator && arg is Intent -> {
        v.setRemoteViewsAdapter(arg)
        true
      }
      else -> false
    }
    "scrollingCacheEnabled" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setScrollingCacheEnabled(arg)
        true
      }
      else -> false
    }
    "selector" -> when {
      v is AbsListView && arg is Drawable -> {
        v.setSelector(arg)
        true
      }
      v is AbsListView && arg is Int -> {
        v.setSelector(arg)
        true
      }
      else -> false
    }
    "smoothScrollbarEnabled" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setSmoothScrollbarEnabled(arg)
        true
      }
      else -> false
    }
    "stackFromBottom" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setStackFromBottom(arg)
        true
      }
      else -> false
    }
    "textFilterEnabled" -> when {
      v is AbsListView && arg is Boolean -> {
        v.setTextFilterEnabled(arg)
        true
      }
      else -> false
    }
    "transcriptMode" -> when {
      v is AbsListView && arg is Int -> {
        v.setTranscriptMode(arg)
        true
      }
      else -> false
    }
    "velocityScale" -> when {
      v is AbsListView && arg is Float -> {
        v.setVelocityScale(arg)
        true
      }
      else -> false
    }
    "keyProgressIncrement" -> when {
      v is AbsSeekBar && arg is Int -> {
        v.setKeyProgressIncrement(arg)
        true
      }
      else -> false
    }
    "thumb" -> when {
      v is AbsSeekBar && arg is Drawable -> {
        v.setThumb(arg)
        true
      }
      else -> false
    }
    "thumbOffset" -> when {
      v is AbsSeekBar && arg is Int -> {
        v.setThumbOffset(arg)
        true
      }
      else -> false
    }
    "adapter" -> when {
      v is AdapterView<*> && arg is Adapter -> {
        (v as android.widget.AdapterView<android.widget.Adapter>).setAdapter(arg)
        true
      }
      v is ExpandableListView && arg is ExpandableListAdapter -> {
        v.setAdapter(arg)
        true
      }
      else -> false
    }
    "emptyView" -> when {
      v is AdapterView<*> && arg is View -> {
        (v as android.widget.AdapterView<android.widget.Adapter>).setEmptyView(arg)
        true
      }
      else -> false
    }
    "onItemClick" -> when {
      v is AdapterView<*> -> when {
        arg == null -> {
          (v as android.widget.AdapterView<android.widget.Adapter>).setOnItemClickListener(null as?
              AdapterView.OnItemClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: AdapterView<*>,
            arg1: View,
            arg2: Int,
            arg3: Long
          ) -> Unit
          v.setOnItemClickListener { arg0, arg1, arg2, arg3 ->
            arg(arg0, arg1, arg2, arg3).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      v is AutoCompleteTextView -> when {
        arg == null -> {
          v.setOnItemClickListener(null as? AdapterView.OnItemClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: AdapterView<*>,
            arg1: View,
            arg2: Int,
            arg3: Long
          ) -> Unit
          v.setOnItemClickListener { arg0, arg1, arg2, arg3 ->
            arg(arg0, arg1, arg2, arg3).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onItemLongClick" -> when {
      v is AdapterView<*> -> when {
        arg == null -> {
          (v as android.widget.AdapterView<android.widget.Adapter>).setOnItemLongClickListener(null
              as? AdapterView.OnItemLongClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: AdapterView<*>,
            arg1: View,
            arg2: Int,
            arg3: Long
          ) -> Boolean
          v.setOnItemLongClickListener { arg0, arg1, arg2, arg3 ->
            arg(arg0, arg1, arg2, arg3).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onItemSelected" -> when {
      v is AdapterView<*> -> when {
        arg == null -> {
          (v as android.widget.AdapterView<android.widget.Adapter>).setOnItemSelectedListener(null
              as? AdapterView.OnItemSelectedListener?)
          true
        }
        arg is AdapterView.OnItemSelectedListener -> {
          v.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
              arg0: AdapterView<*>,
              arg1: View,
              arg2: Int,
              arg3: Long
            ): Unit = arg.onItemSelected(arg0, arg1, arg2, arg3).also { Anvil.render() }

            override fun onNothingSelected(arg0: AdapterView<*>): Unit =
                arg.onNothingSelected(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      v is AutoCompleteTextView -> when {
        arg == null -> {
          v.setOnItemSelectedListener(null as? AdapterView.OnItemSelectedListener?)
          true
        }
        arg is AdapterView.OnItemSelectedListener -> {
          v.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
              arg0: AdapterView<*>,
              arg1: View,
              arg2: Int,
              arg3: Long
            ): Unit = arg.onItemSelected(arg0, arg1, arg2, arg3).also { Anvil.render() }

            override fun onNothingSelected(arg0: AdapterView<*>): Unit =
                arg.onNothingSelected(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "selection" -> when {
      v is AdapterView<*> && arg is Int -> {
        (v as android.widget.AdapterView<android.widget.Adapter>).setSelection(arg)
        true
      }
      v is EditText && arg is Int -> {
        v.setSelection(arg)
        true
      }
      else -> false
    }
    "animateFirstView" -> when {
      v is AdapterViewAnimator && arg is Boolean -> {
        v.setAnimateFirstView(arg)
        true
      }
      v is ViewAnimator && arg is Boolean -> {
        v.setAnimateFirstView(arg)
        true
      }
      else -> false
    }
    "displayedChild" -> when {
      v is AdapterViewAnimator && arg is Int -> {
        v.setDisplayedChild(arg)
        true
      }
      v is ViewAnimator && arg is Int -> {
        v.setDisplayedChild(arg)
        true
      }
      else -> false
    }
    "inAnimation" -> when {
      v is ViewAnimator && arg is Animation -> {
        v.setInAnimation(arg)
        true
      }
      v is AdapterViewAnimator && arg is ObjectAnimator -> {
        v.setInAnimation(arg)
        true
      }
      else -> false
    }
    "outAnimation" -> when {
      v is ViewAnimator && arg is Animation -> {
        v.setOutAnimation(arg)
        true
      }
      v is AdapterViewAnimator && arg is ObjectAnimator -> {
        v.setOutAnimation(arg)
        true
      }
      else -> false
    }
    "autoStart" -> when {
      v is AdapterViewFlipper && arg is Boolean -> {
        v.setAutoStart(arg)
        true
      }
      v is ViewFlipper && arg is Boolean -> {
        v.setAutoStart(arg)
        true
      }
      else -> false
    }
    "flipInterval" -> when {
      v is AdapterViewFlipper && arg is Int -> {
        v.setFlipInterval(arg)
        true
      }
      v is ViewFlipper && arg is Int -> {
        v.setFlipInterval(arg)
        true
      }
      else -> false
    }
    "completionHint" -> when {
      v is AutoCompleteTextView && arg is CharSequence -> {
        v.setCompletionHint(arg)
        true
      }
      else -> false
    }
    "dropDownAnchor" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownAnchor(arg)
        true
      }
      else -> false
    }
    "dropDownBackgroundDrawable" -> when {
      v is AutoCompleteTextView && arg is Drawable -> {
        v.setDropDownBackgroundDrawable(arg)
        true
      }
      else -> false
    }
    "dropDownBackgroundResource" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownBackgroundResource(arg)
        true
      }
      else -> false
    }
    "dropDownHeight" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownHeight(arg)
        true
      }
      else -> false
    }
    "dropDownHorizontalOffset" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownHorizontalOffset(arg)
        true
      }
      v is Spinner && arg is Int -> {
        v.setDropDownHorizontalOffset(arg)
        true
      }
      else -> false
    }
    "dropDownVerticalOffset" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownVerticalOffset(arg)
        true
      }
      v is Spinner && arg is Int -> {
        v.setDropDownVerticalOffset(arg)
        true
      }
      else -> false
    }
    "dropDownWidth" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setDropDownWidth(arg)
        true
      }
      v is Spinner && arg is Int -> {
        v.setDropDownWidth(arg)
        true
      }
      else -> false
    }
    "listSelection" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setListSelection(arg)
        true
      }
      else -> false
    }
    "onDismiss" -> when {
      v is AutoCompleteTextView -> when {
        arg == null -> {
          v.setOnDismissListener(null as? AutoCompleteTextView.OnDismissListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Unit
          v.setOnDismissListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "threshold" -> when {
      v is AutoCompleteTextView && arg is Int -> {
        v.setThreshold(arg)
        true
      }
      else -> false
    }
    "validator" -> when {
      v is AutoCompleteTextView && arg is AutoCompleteTextView.Validator -> {
        v.setValidator(arg)
        true
      }
      else -> false
    }
    "date" -> when {
      v is CalendarView && arg is Long -> {
        v.setDate(arg)
        true
      }
      else -> false
    }
    "dateTextAppearance" -> when {
      v is CalendarView && arg is Int -> {
        v.setDateTextAppearance(arg)
        true
      }
      else -> false
    }
    "firstDayOfWeek" -> when {
      v is CalendarView && arg is Int -> {
        v.setFirstDayOfWeek(arg)
        true
      }
      else -> false
    }
    "focusedMonthDateColor" -> when {
      v is CalendarView && arg is Int -> {
        v.setFocusedMonthDateColor(arg)
        true
      }
      else -> false
    }
    "maxDate" -> when {
      v is CalendarView && arg is Long -> {
        v.setMaxDate(arg)
        true
      }
      v is DatePicker && arg is Long -> {
        v.setMaxDate(arg)
        true
      }
      else -> false
    }
    "minDate" -> when {
      v is CalendarView && arg is Long -> {
        v.setMinDate(arg)
        true
      }
      v is DatePicker && arg is Long -> {
        v.setMinDate(arg)
        true
      }
      else -> false
    }
    "onDateChange" -> when {
      v is CalendarView -> when {
        arg == null -> {
          v.setOnDateChangeListener(null as? CalendarView.OnDateChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: CalendarView,
            arg1: Int,
            arg2: Int,
            arg3: Int
          ) -> Unit
          v.setOnDateChangeListener { arg0, arg1, arg2, arg3 ->
            arg(arg0, arg1, arg2, arg3).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "selectedDateVerticalBar" -> when {
      v is CalendarView && arg is Drawable -> {
        v.setSelectedDateVerticalBar(arg)
        true
      }
      v is CalendarView && arg is Int -> {
        v.setSelectedDateVerticalBar(arg)
        true
      }
      else -> false
    }
    "selectedWeekBackgroundColor" -> when {
      v is CalendarView && arg is Int -> {
        v.setSelectedWeekBackgroundColor(arg)
        true
      }
      else -> false
    }
    "showWeekNumber" -> when {
      v is CalendarView && arg is Boolean -> {
        v.setShowWeekNumber(arg)
        true
      }
      else -> false
    }
    "shownWeekCount" -> when {
      v is CalendarView && arg is Int -> {
        v.setShownWeekCount(arg)
        true
      }
      else -> false
    }
    "unfocusedMonthDateColor" -> when {
      v is CalendarView && arg is Int -> {
        v.setUnfocusedMonthDateColor(arg)
        true
      }
      else -> false
    }
    "weekDayTextAppearance" -> when {
      v is CalendarView && arg is Int -> {
        v.setWeekDayTextAppearance(arg)
        true
      }
      else -> false
    }
    "weekNumberColor" -> when {
      v is CalendarView && arg is Int -> {
        v.setWeekNumberColor(arg)
        true
      }
      else -> false
    }
    "weekSeparatorLineColor" -> when {
      v is CalendarView && arg is Int -> {
        v.setWeekSeparatorLineColor(arg)
        true
      }
      else -> false
    }
    "checkMarkDrawable" -> when {
      v is CheckedTextView && (arg == null || arg is Drawable) -> {
        v.setCheckMarkDrawable(arg as Drawable)
        true
      }
      v is CheckedTextView && arg is Int -> {
        v.setCheckMarkDrawable(arg)
        true
      }
      else -> false
    }
    "checked" -> when {
      v is CheckedTextView && arg is Boolean -> {
        v.setChecked(arg)
        true
      }
      v is CompoundButton && arg is Boolean -> {
        v.setChecked(arg)
        true
      }
      else -> false
    }
    "base" -> when {
      v is Chronometer && arg is Long -> {
        v.setBase(arg)
        true
      }
      else -> false
    }
    "format" -> when {
      v is Chronometer && arg is String -> {
        v.setFormat(arg)
        true
      }
      else -> false
    }
    "onChronometerTick" -> when {
      v is Chronometer -> when {
        arg == null -> {
          v.setOnChronometerTickListener(null as? Chronometer.OnChronometerTickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: Chronometer) -> Unit
          v.setOnChronometerTickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "buttonDrawable" -> when {
      v is CompoundButton && (arg == null || arg is Drawable) -> {
        v.setButtonDrawable(arg as Drawable)
        true
      }
      v is CompoundButton && arg is Int -> {
        v.setButtonDrawable(arg)
        true
      }
      else -> false
    }
    "onCheckedChange" -> when {
      v is CompoundButton -> when {
        arg == null -> {
          v.setOnCheckedChangeListener(null as? CompoundButton.OnCheckedChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: CompoundButton, arg1: Boolean) -> Unit
          v.setOnCheckedChangeListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      v is RadioGroup -> when {
        arg == null -> {
          v.setOnCheckedChangeListener(null as? RadioGroup.OnCheckedChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: RadioGroup, arg1: Int) -> Unit
          v.setOnCheckedChangeListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "calendarViewShown" -> when {
      v is DatePicker && arg is Boolean -> {
        v.setCalendarViewShown(arg)
        true
      }
      else -> false
    }
    "spinnersShown" -> when {
      v is DatePicker && arg is Boolean -> {
        v.setSpinnersShown(arg)
        true
      }
      else -> false
    }
    "digitsWatcher" -> when {
      v is DialerFilter && arg is TextWatcher -> {
        v.setDigitsWatcher(arg)
        true
      }
      else -> false
    }
    "filterWatcher" -> when {
      v is DialerFilter && arg is TextWatcher -> {
        v.setFilterWatcher(arg)
        true
      }
      else -> false
    }
    "lettersWatcher" -> when {
      v is DialerFilter && arg is TextWatcher -> {
        v.setLettersWatcher(arg)
        true
      }
      else -> false
    }
    "mode" -> when {
      v is DialerFilter && arg is Int -> {
        v.setMode(arg)
        true
      }
      v is QuickContactBadge && arg is Int -> {
        v.setMode(arg)
        true
      }
      else -> false
    }
    "childDivider" -> when {
      v is ExpandableListView && arg is Drawable -> {
        v.setChildDivider(arg)
        true
      }
      else -> false
    }
    "childIndicator" -> when {
      v is ExpandableListView && arg is Drawable -> {
        v.setChildIndicator(arg)
        true
      }
      else -> false
    }
    "groupIndicator" -> when {
      v is ExpandableListView && arg is Drawable -> {
        v.setGroupIndicator(arg)
        true
      }
      else -> false
    }
    "onChildClick" -> when {
      v is ExpandableListView -> when {
        arg == null -> {
          v.setOnChildClickListener(null as? ExpandableListView.OnChildClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: ExpandableListView,
            arg1: View,
            arg2: Int,
            arg3: Int,
            arg4: Long
          ) -> Boolean
          v.setOnChildClickListener { arg0, arg1, arg2, arg3, arg4 ->
            arg(arg0, arg1, arg2, arg3, arg4).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onGroupClick" -> when {
      v is ExpandableListView -> when {
        arg == null -> {
          v.setOnGroupClickListener(null as? ExpandableListView.OnGroupClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: ExpandableListView,
            arg1: View,
            arg2: Int,
            arg3: Long
          ) -> Boolean
          v.setOnGroupClickListener { arg0, arg1, arg2, arg3 ->
            arg(arg0, arg1, arg2, arg3).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onGroupCollapse" -> when {
      v is ExpandableListView -> when {
        arg == null -> {
          v.setOnGroupCollapseListener(null as? ExpandableListView.OnGroupCollapseListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: Int) -> Unit
          v.setOnGroupCollapseListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onGroupExpand" -> when {
      v is ExpandableListView -> when {
        arg == null -> {
          v.setOnGroupExpandListener(null as? ExpandableListView.OnGroupExpandListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: Int) -> Unit
          v.setOnGroupExpandListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "selectedGroup" -> when {
      v is ExpandableListView && arg is Int -> {
        v.setSelectedGroup(arg)
        true
      }
      else -> false
    }
    "foreground" -> when {
      v is FrameLayout && arg is Drawable -> {
        v.setForeground(arg)
        true
      }
      else -> false
    }
    "foregroundGravity" -> when {
      v is FrameLayout && arg is Int -> {
        v.setForegroundGravity(arg)
        true
      }
      else -> false
    }
    "measureAllChildren" -> when {
      v is FrameLayout && arg is Boolean -> {
        v.setMeasureAllChildren(arg)
        true
      }
      else -> false
    }
    "animationDuration" -> when {
      v is Gallery && arg is Int -> {
        v.setAnimationDuration(arg)
        true
      }
      else -> false
    }
    "callbackDuringFling" -> when {
      v is Gallery && arg is Boolean -> {
        v.setCallbackDuringFling(arg)
        true
      }
      else -> false
    }
    "gravity" -> when {
      v is Gallery && arg is Int -> {
        v.setGravity(arg)
        true
      }
      v is GridView && arg is Int -> {
        v.setGravity(arg)
        true
      }
      v is LinearLayout && arg is Int -> {
        v.setGravity(arg)
        true
      }
      v is RelativeLayout && arg is Int -> {
        v.setGravity(arg)
        true
      }
      v is Spinner && arg is Int -> {
        v.setGravity(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setGravity(arg)
        true
      }
      else -> false
    }
    "spacing" -> when {
      v is Gallery && arg is Int -> {
        v.setSpacing(arg)
        true
      }
      else -> false
    }
    "unselectedAlpha" -> when {
      v is Gallery && arg is Float -> {
        v.setUnselectedAlpha(arg)
        true
      }
      else -> false
    }
    "alignmentMode" -> when {
      v is GridLayout && arg is Int -> {
        v.setAlignmentMode(arg)
        true
      }
      else -> false
    }
    "columnCount" -> when {
      v is GridLayout && arg is Int -> {
        v.setColumnCount(arg)
        true
      }
      else -> false
    }
    "columnOrderPreserved" -> when {
      v is GridLayout && arg is Boolean -> {
        v.setColumnOrderPreserved(arg)
        true
      }
      else -> false
    }
    "rowCount" -> when {
      v is GridLayout && arg is Int -> {
        v.setRowCount(arg)
        true
      }
      else -> false
    }
    "rowOrderPreserved" -> when {
      v is GridLayout && arg is Boolean -> {
        v.setRowOrderPreserved(arg)
        true
      }
      else -> false
    }
    "useDefaultMargins" -> when {
      v is GridLayout && arg is Boolean -> {
        v.setUseDefaultMargins(arg)
        true
      }
      else -> false
    }
    "columnWidth" -> when {
      v is GridView && arg is Int -> {
        v.setColumnWidth(arg)
        true
      }
      else -> false
    }
    "horizontalSpacing" -> when {
      v is GridView && arg is Int -> {
        v.setHorizontalSpacing(arg)
        true
      }
      else -> false
    }
    "numColumns" -> when {
      v is GridView && arg is Int -> {
        v.setNumColumns(arg)
        true
      }
      else -> false
    }
    "stretchMode" -> when {
      v is GridView && arg is Int -> {
        v.setStretchMode(arg)
        true
      }
      else -> false
    }
    "verticalSpacing" -> when {
      v is GridView && arg is Int -> {
        v.setVerticalSpacing(arg)
        true
      }
      else -> false
    }
    "fillViewport" -> when {
      v is HorizontalScrollView && arg is Boolean -> {
        v.setFillViewport(arg)
        true
      }
      v is ScrollView && arg is Boolean -> {
        v.setFillViewport(arg)
        true
      }
      else -> false
    }
    "smoothScrollingEnabled" -> when {
      v is HorizontalScrollView && arg is Boolean -> {
        v.setSmoothScrollingEnabled(arg)
        true
      }
      v is ScrollView && arg is Boolean -> {
        v.setSmoothScrollingEnabled(arg)
        true
      }
      else -> false
    }
    "imageDrawable" -> when {
      v is ImageSwitcher && arg is Drawable -> {
        v.setImageDrawable(arg)
        true
      }
      v is ImageView && (arg == null || arg is Drawable) -> {
        v.setImageDrawable(arg as Drawable)
        true
      }
      else -> false
    }
    "imageResource" -> when {
      v is ImageSwitcher && arg is Int -> {
        v.setImageResource(arg)
        true
      }
      v is ImageView && arg is Int -> {
        v.setImageResource(arg)
        true
      }
      else -> false
    }
    "imageURI" -> when {
      v is ImageSwitcher && arg is Uri -> {
        v.setImageURI(arg)
        true
      }
      v is ImageView && (arg == null || arg is Uri) -> {
        v.setImageURI(arg as Uri)
        true
      }
      else -> false
    }
    "adjustViewBounds" -> when {
      v is ImageView && arg is Boolean -> {
        v.setAdjustViewBounds(arg)
        true
      }
      else -> false
    }
    "baseline" -> when {
      v is ImageView && arg is Int -> {
        v.setBaseline(arg)
        true
      }
      else -> false
    }
    "baselineAlignBottom" -> when {
      v is ImageView && arg is Boolean -> {
        v.setBaselineAlignBottom(arg)
        true
      }
      else -> false
    }
    "colorFilter" -> when {
      v is ImageView && arg is ColorFilter -> {
        v.setColorFilter(arg)
        true
      }
      v is ImageView && arg is Int -> {
        v.setColorFilter(arg)
        true
      }
      else -> false
    }
    "cropToPadding" -> when {
      v is ImageView && arg is Boolean -> {
        v.setCropToPadding(arg)
        true
      }
      else -> false
    }
    "imageAlpha" -> when {
      v is ImageView && arg is Int -> {
        v.setImageAlpha(arg)
        true
      }
      else -> false
    }
    "imageBitmap" -> when {
      v is ImageView && arg is Bitmap -> {
        v.setImageBitmap(arg)
        true
      }
      else -> false
    }
    "imageLevel" -> when {
      v is ImageView && arg is Int -> {
        v.setImageLevel(arg)
        true
      }
      else -> false
    }
    "imageMatrix" -> when {
      v is ImageView && arg is Matrix -> {
        v.setImageMatrix(arg)
        true
      }
      else -> false
    }
    "maxHeight" -> when {
      v is ImageView && arg is Int -> {
        v.setMaxHeight(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setMaxHeight(arg)
        true
      }
      else -> false
    }
    "maxWidth" -> when {
      v is ImageView && arg is Int -> {
        v.setMaxWidth(arg)
        true
      }
      v is SearchView && arg is Int -> {
        v.setMaxWidth(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setMaxWidth(arg)
        true
      }
      else -> false
    }
    "scaleType" -> when {
      v is ImageView && arg is ImageView.ScaleType -> {
        v.setScaleType(arg)
        true
      }
      else -> false
    }
    "baselineAligned" -> when {
      v is LinearLayout && arg is Boolean -> {
        v.setBaselineAligned(arg)
        true
      }
      else -> false
    }
    "baselineAlignedChildIndex" -> when {
      v is LinearLayout && arg is Int -> {
        v.setBaselineAlignedChildIndex(arg)
        true
      }
      else -> false
    }
    "dividerDrawable" -> when {
      v is LinearLayout && arg is Drawable -> {
        v.setDividerDrawable(arg)
        true
      }
      v is TabWidget && arg is Int -> {
        v.setDividerDrawable(arg)
        true
      }
      else -> false
    }
    "dividerPadding" -> when {
      v is LinearLayout && arg is Int -> {
        v.setDividerPadding(arg)
        true
      }
      else -> false
    }
    "horizontalGravity" -> when {
      v is LinearLayout && arg is Int -> {
        v.setHorizontalGravity(arg)
        true
      }
      v is RelativeLayout && arg is Int -> {
        v.setHorizontalGravity(arg)
        true
      }
      else -> false
    }
    "measureWithLargestChildEnabled" -> when {
      v is LinearLayout && arg is Boolean -> {
        v.setMeasureWithLargestChildEnabled(arg)
        true
      }
      else -> false
    }
    "showDividers" -> when {
      v is LinearLayout && arg is Int -> {
        v.setShowDividers(arg)
        true
      }
      else -> false
    }
    "verticalGravity" -> when {
      v is LinearLayout && arg is Int -> {
        v.setVerticalGravity(arg)
        true
      }
      v is RelativeLayout && arg is Int -> {
        v.setVerticalGravity(arg)
        true
      }
      else -> false
    }
    "weightSum" -> when {
      v is LinearLayout && arg is Float -> {
        v.setWeightSum(arg)
        true
      }
      else -> false
    }
    "divider" -> when {
      v is ListView && (arg == null || arg is Drawable) -> {
        v.setDivider(arg as Drawable)
        true
      }
      else -> false
    }
    "dividerHeight" -> when {
      v is ListView && arg is Int -> {
        v.setDividerHeight(arg)
        true
      }
      else -> false
    }
    "footerDividersEnabled" -> when {
      v is ListView && arg is Boolean -> {
        v.setFooterDividersEnabled(arg)
        true
      }
      else -> false
    }
    "headerDividersEnabled" -> when {
      v is ListView && arg is Boolean -> {
        v.setHeaderDividersEnabled(arg)
        true
      }
      else -> false
    }
    "itemsCanFocus" -> when {
      v is ListView && arg is Boolean -> {
        v.setItemsCanFocus(arg)
        true
      }
      else -> false
    }
    "overscrollFooter" -> when {
      v is ListView && arg is Drawable -> {
        v.setOverscrollFooter(arg)
        true
      }
      else -> false
    }
    "overscrollHeader" -> when {
      v is ListView && arg is Drawable -> {
        v.setOverscrollHeader(arg)
        true
      }
      else -> false
    }
    "anchorView" -> when {
      v is MediaController && arg is View -> {
        v.setAnchorView(arg)
        true
      }
      else -> false
    }
    "mediaPlayer" -> when {
      v is MediaController && arg is MediaController.MediaPlayerControl -> {
        v.setMediaPlayer(arg)
        true
      }
      else -> false
    }
    "tokenizer" -> when {
      v is MultiAutoCompleteTextView && arg is MultiAutoCompleteTextView.Tokenizer -> {
        v.setTokenizer(arg)
        true
      }
      else -> false
    }
    "displayedValues" -> when {
      v is NumberPicker && arg is Array<*> -> {
        v.setDisplayedValues(arg as Array<String>)
        true
      }
      else -> false
    }
    "formatter" -> when {
      v is NumberPicker && arg is NumberPicker.Formatter -> {
        v.setFormatter(arg)
        true
      }
      else -> false
    }
    "maxValue" -> when {
      v is NumberPicker && arg is Int -> {
        v.setMaxValue(arg)
        true
      }
      else -> false
    }
    "minValue" -> when {
      v is NumberPicker && arg is Int -> {
        v.setMinValue(arg)
        true
      }
      else -> false
    }
    "onLongPressUpdateInterval" -> when {
      v is NumberPicker && arg is Long -> {
        v.setOnLongPressUpdateInterval(arg)
        true
      }
      else -> false
    }
    "onValueChanged" -> when {
      v is NumberPicker -> when {
        arg == null -> {
          v.setOnValueChangedListener(null as? NumberPicker.OnValueChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: NumberPicker,
            arg1: Int,
            arg2: Int
          ) -> Unit
          v.setOnValueChangedListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "value" -> when {
      v is NumberPicker && arg is Int -> {
        v.setValue(arg)
        true
      }
      else -> false
    }
    "wrapSelectorWheel" -> when {
      v is NumberPicker && arg is Boolean -> {
        v.setWrapSelectorWheel(arg)
        true
      }
      else -> false
    }
    "indeterminate" -> when {
      v is ProgressBar && arg is Boolean -> {
        v.setIndeterminate(arg)
        true
      }
      else -> false
    }
    "indeterminateDrawable" -> when {
      v is ProgressBar && arg is Drawable -> {
        v.setIndeterminateDrawable(arg)
        true
      }
      else -> false
    }
    "interpolator" -> when {
      v is ProgressBar && arg is Interpolator -> {
        v.setInterpolator(arg)
        true
      }
      else -> false
    }
    "max" -> when {
      v is ProgressBar && arg is Int -> {
        v.setMax(arg)
        true
      }
      else -> false
    }
    "progress" -> when {
      v is ProgressBar && arg is Int -> {
        v.setProgress(arg)
        true
      }
      else -> false
    }
    "progressDrawable" -> when {
      v is ProgressBar && arg is Drawable -> {
        v.setProgressDrawable(arg)
        true
      }
      else -> false
    }
    "secondaryProgress" -> when {
      v is ProgressBar && arg is Int -> {
        v.setSecondaryProgress(arg)
        true
      }
      else -> false
    }
    "excludeMimes" -> when {
      v is QuickContactBadge && arg is Array<*> -> {
        v.setExcludeMimes(arg as Array<String>)
        true
      }
      else -> false
    }
    "isIndicator" -> when {
      v is RatingBar && arg is Boolean -> {
        v.setIsIndicator(arg)
        true
      }
      else -> false
    }
    "numStars" -> when {
      v is RatingBar && arg is Int -> {
        v.setNumStars(arg)
        true
      }
      else -> false
    }
    "onRatingBarChange" -> when {
      v is RatingBar -> when {
        arg == null -> {
          v.setOnRatingBarChangeListener(null as? RatingBar.OnRatingBarChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: RatingBar,
            arg1: Float,
            arg2: Boolean
          ) -> Unit
          v.setOnRatingBarChangeListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "rating" -> when {
      v is RatingBar && arg is Float -> {
        v.setRating(arg)
        true
      }
      else -> false
    }
    "stepSize" -> when {
      v is RatingBar && arg is Float -> {
        v.setStepSize(arg)
        true
      }
      else -> false
    }
    "ignoreGravity" -> when {
      v is RelativeLayout && arg is Int -> {
        v.setIgnoreGravity(arg)
        true
      }
      else -> false
    }
    "iconified" -> when {
      v is SearchView && arg is Boolean -> {
        v.setIconified(arg)
        true
      }
      else -> false
    }
    "iconifiedByDefault" -> when {
      v is SearchView && arg is Boolean -> {
        v.setIconifiedByDefault(arg)
        true
      }
      else -> false
    }
    "imeOptions" -> when {
      v is SearchView && arg is Int -> {
        v.setImeOptions(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setImeOptions(arg)
        true
      }
      else -> false
    }
    "inputType" -> when {
      v is SearchView && arg is Int -> {
        v.setInputType(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setInputType(arg)
        true
      }
      else -> false
    }
    "onClose" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnCloseListener(null as? SearchView.OnCloseListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Boolean
          v.setOnCloseListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onQueryTextFocusChange" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnQueryTextFocusChangeListener(null as? View.OnFocusChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View, arg1: Boolean) -> Unit
          v.setOnQueryTextFocusChangeListener { arg0, arg1 ->
            arg(arg0, arg1).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onQueryText" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnQueryTextListener(null as? SearchView.OnQueryTextListener?)
          true
        }
        arg is SearchView.OnQueryTextListener -> {
          v.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(arg0: String): Boolean =
                arg.onQueryTextChange(arg0).also { Anvil.render() }

            override fun onQueryTextSubmit(arg0: String): Boolean =
                arg.onQueryTextSubmit(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "onSearchClick" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnSearchClickListener(null as? View.OnClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View) -> Unit
          v.setOnSearchClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onSuggestion" -> when {
      v is SearchView -> when {
        arg == null -> {
          v.setOnSuggestionListener(null as? SearchView.OnSuggestionListener?)
          true
        }
        arg is SearchView.OnSuggestionListener -> {
          v.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionClick(arg0: Int): Boolean = arg.onSuggestionClick(arg0).also {
                Anvil.render() }

            override fun onSuggestionSelect(arg0: Int): Boolean =
                arg.onSuggestionSelect(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "queryHint" -> when {
      v is SearchView && (arg == null || arg is CharSequence) -> {
        v.setQueryHint(arg as CharSequence)
        true
      }
      else -> false
    }
    "queryRefinementEnabled" -> when {
      v is SearchView && arg is Boolean -> {
        v.setQueryRefinementEnabled(arg)
        true
      }
      else -> false
    }
    "searchableInfo" -> when {
      v is SearchView && arg is SearchableInfo -> {
        v.setSearchableInfo(arg)
        true
      }
      else -> false
    }
    "submitButtonEnabled" -> when {
      v is SearchView && arg is Boolean -> {
        v.setSubmitButtonEnabled(arg)
        true
      }
      else -> false
    }
    "suggestionsAdapter" -> when {
      v is SearchView && arg is CursorAdapter -> {
        v.setSuggestionsAdapter(arg)
        true
      }
      else -> false
    }
    "onSeekBarChange" -> when {
      v is SeekBar -> when {
        arg == null -> {
          v.setOnSeekBarChangeListener(null as? SeekBar.OnSeekBarChangeListener?)
          true
        }
        arg is SeekBar.OnSeekBarChangeListener -> {
          v.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
              arg0: SeekBar,
              arg1: Int,
              arg2: Boolean
            ): Unit = arg.onProgressChanged(arg0, arg1, arg2).also { Anvil.render() }

            override fun onStartTrackingTouch(arg0: SeekBar): Unit =
                arg.onStartTrackingTouch(arg0).also { Anvil.render() }

            override fun onStopTrackingTouch(arg0: SeekBar): Unit =
                arg.onStopTrackingTouch(arg0).also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "onDrawerClose" -> when {
      v is SlidingDrawer -> when {
        arg == null -> {
          v.setOnDrawerCloseListener(null as? SlidingDrawer.OnDrawerCloseListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Unit
          v.setOnDrawerCloseListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onDrawerOpen" -> when {
      v is SlidingDrawer -> when {
        arg == null -> {
          v.setOnDrawerOpenListener(null as? SlidingDrawer.OnDrawerOpenListener?)
          true
        }
        arg is Function<*> -> {
          arg as () -> Unit
          v.setOnDrawerOpenListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onDrawerScroll" -> when {
      v is SlidingDrawer -> when {
        arg == null -> {
          v.setOnDrawerScrollListener(null as? SlidingDrawer.OnDrawerScrollListener?)
          true
        }
        arg is SlidingDrawer.OnDrawerScrollListener -> {
          v.setOnDrawerScrollListener(object : SlidingDrawer.OnDrawerScrollListener {
            override fun onScrollEnded(): Unit = arg.onScrollEnded().also { Anvil.render() }

            override fun onScrollStarted(): Unit = arg.onScrollStarted().also { Anvil.render() }
          })
          true
        }
        else -> false
      }
      else -> false
    }
    "popupBackgroundDrawable" -> when {
      v is Spinner && arg is Drawable -> {
        v.setPopupBackgroundDrawable(arg)
        true
      }
      else -> false
    }
    "popupBackgroundResource" -> when {
      v is Spinner && arg is Int -> {
        v.setPopupBackgroundResource(arg)
        true
      }
      else -> false
    }
    "prompt" -> when {
      v is Spinner && arg is CharSequence -> {
        v.setPrompt(arg)
        true
      }
      else -> false
    }
    "promptId" -> when {
      v is Spinner && arg is Int -> {
        v.setPromptId(arg)
        true
      }
      else -> false
    }
    "switchMinWidth" -> when {
      v is Switch && arg is Int -> {
        v.setSwitchMinWidth(arg)
        true
      }
      else -> false
    }
    "switchPadding" -> when {
      v is Switch && arg is Int -> {
        v.setSwitchPadding(arg)
        true
      }
      else -> false
    }
    "switchTypeface" -> when {
      v is Switch && arg is Typeface -> {
        v.setSwitchTypeface(arg)
        true
      }
      else -> false
    }
    "textOff" -> when {
      v is Switch && arg is CharSequence -> {
        v.setTextOff(arg)
        true
      }
      v is ToggleButton && arg is CharSequence -> {
        v.setTextOff(arg)
        true
      }
      else -> false
    }
    "textOn" -> when {
      v is Switch && arg is CharSequence -> {
        v.setTextOn(arg)
        true
      }
      v is ToggleButton && arg is CharSequence -> {
        v.setTextOn(arg)
        true
      }
      else -> false
    }
    "thumbDrawable" -> when {
      v is Switch && arg is Drawable -> {
        v.setThumbDrawable(arg)
        true
      }
      else -> false
    }
    "thumbResource" -> when {
      v is Switch && arg is Int -> {
        v.setThumbResource(arg)
        true
      }
      else -> false
    }
    "thumbTextPadding" -> when {
      v is Switch && arg is Int -> {
        v.setThumbTextPadding(arg)
        true
      }
      else -> false
    }
    "trackDrawable" -> when {
      v is Switch && arg is Drawable -> {
        v.setTrackDrawable(arg)
        true
      }
      else -> false
    }
    "trackResource" -> when {
      v is Switch && arg is Int -> {
        v.setTrackResource(arg)
        true
      }
      else -> false
    }
    "currentTab" -> when {
      v is TabHost && arg is Int -> {
        v.setCurrentTab(arg)
        true
      }
      v is TabWidget && arg is Int -> {
        v.setCurrentTab(arg)
        true
      }
      else -> false
    }
    "currentTabByTag" -> when {
      v is TabHost && arg is String -> {
        v.setCurrentTabByTag(arg)
        true
      }
      else -> false
    }
    "onTabChanged" -> when {
      v is TabHost -> when {
        arg == null -> {
          v.setOnTabChangedListener(null as? TabHost.OnTabChangeListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: String) -> Unit
          v.setOnTabChangedListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "leftStripDrawable" -> when {
      v is TabWidget && (arg == null || arg is Drawable) -> {
        v.setLeftStripDrawable(arg as Drawable)
        true
      }
      v is TabWidget && arg is Int -> {
        v.setLeftStripDrawable(arg)
        true
      }
      else -> false
    }
    "rightStripDrawable" -> when {
      v is TabWidget && (arg == null || arg is Drawable) -> {
        v.setRightStripDrawable(arg as Drawable)
        true
      }
      v is TabWidget && arg is Int -> {
        v.setRightStripDrawable(arg)
        true
      }
      else -> false
    }
    "stripEnabled" -> when {
      v is TabWidget && arg is Boolean -> {
        v.setStripEnabled(arg)
        true
      }
      else -> false
    }
    "shrinkAllColumns" -> when {
      v is TableLayout && arg is Boolean -> {
        v.setShrinkAllColumns(arg)
        true
      }
      else -> false
    }
    "stretchAllColumns" -> when {
      v is TableLayout && arg is Boolean -> {
        v.setStretchAllColumns(arg)
        true
      }
      else -> false
    }
    "format12Hour" -> when {
      v is TextClock && arg is CharSequence -> {
        v.setFormat12Hour(arg)
        true
      }
      else -> false
    }
    "format24Hour" -> when {
      v is TextClock && arg is CharSequence -> {
        v.setFormat24Hour(arg)
        true
      }
      else -> false
    }
    "timeZone" -> when {
      v is TextClock && arg is String -> {
        v.setTimeZone(arg)
        true
      }
      else -> false
    }
    "currentText" -> when {
      v is TextSwitcher && arg is CharSequence -> {
        v.setCurrentText(arg)
        true
      }
      else -> false
    }
    "text" -> when {
      v is TextSwitcher && arg is CharSequence -> {
        v.setText(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setText(arg)
        true
      }
      else -> false
    }
    "allCaps" -> when {
      v is TextView && arg is Boolean -> {
        v.setAllCaps(arg)
        true
      }
      else -> false
    }
    "autoLinkMask" -> when {
      v is TextView && arg is Int -> {
        v.setAutoLinkMask(arg)
        true
      }
      else -> false
    }
    "compoundDrawablePadding" -> when {
      v is TextView && arg is Int -> {
        v.setCompoundDrawablePadding(arg)
        true
      }
      else -> false
    }
    "cursorVisible" -> when {
      v is TextView && arg is Boolean -> {
        v.setCursorVisible(arg)
        true
      }
      else -> false
    }
    "customSelectionActionModeCallback" -> when {
      v is TextView && arg is ActionMode.Callback -> {
        v.setCustomSelectionActionModeCallback(arg)
        true
      }
      else -> false
    }
    "editableFactory" -> when {
      v is TextView && arg is Editable.Factory -> {
        v.setEditableFactory(arg)
        true
      }
      else -> false
    }
    "ellipsize" -> when {
      v is TextView && arg is TextUtils.TruncateAt -> {
        v.setEllipsize(arg)
        true
      }
      else -> false
    }
    "ems" -> when {
      v is TextView && arg is Int -> {
        v.setEms(arg)
        true
      }
      else -> false
    }
    "error" -> when {
      v is TextView && arg is CharSequence -> {
        v.setError(arg)
        true
      }
      else -> false
    }
    "extractedText" -> when {
      v is TextView && arg is ExtractedText -> {
        v.setExtractedText(arg)
        true
      }
      else -> false
    }
    "filters" -> when {
      v is TextView && arg is Array<*> -> {
        v.setFilters(arg as Array<InputFilter>)
        true
      }
      else -> false
    }
    "freezesText" -> when {
      v is TextView && arg is Boolean -> {
        v.setFreezesText(arg)
        true
      }
      else -> false
    }
    "height" -> when {
      v is TextView && arg is Int -> {
        v.setHeight(arg)
        true
      }
      else -> false
    }
    "highlightColor" -> when {
      v is TextView && arg is Int -> {
        v.setHighlightColor(arg)
        true
      }
      else -> false
    }
    "hint" -> when {
      v is TextView && arg is CharSequence -> {
        v.setHint(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setHint(arg)
        true
      }
      else -> false
    }
    "hintTextColor" -> when {
      v is TextView && arg is ColorStateList -> {
        v.setHintTextColor(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setHintTextColor(arg)
        true
      }
      else -> false
    }
    "horizontallyScrolling" -> when {
      v is TextView && arg is Boolean -> {
        v.setHorizontallyScrolling(arg)
        true
      }
      else -> false
    }
    "includeFontPadding" -> when {
      v is TextView && arg is Boolean -> {
        v.setIncludeFontPadding(arg)
        true
      }
      else -> false
    }
    "keyListener" -> when {
      v is TextView && arg is KeyListener -> {
        v.setKeyListener(arg)
        true
      }
      else -> false
    }
    "lines" -> when {
      v is TextView && arg is Int -> {
        v.setLines(arg)
        true
      }
      else -> false
    }
    "linkTextColor" -> when {
      v is TextView && arg is ColorStateList -> {
        v.setLinkTextColor(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setLinkTextColor(arg)
        true
      }
      else -> false
    }
    "linksClickable" -> when {
      v is TextView && arg is Boolean -> {
        v.setLinksClickable(arg)
        true
      }
      else -> false
    }
    "marqueeRepeatLimit" -> when {
      v is TextView && arg is Int -> {
        v.setMarqueeRepeatLimit(arg)
        true
      }
      else -> false
    }
    "maxEms" -> when {
      v is TextView && arg is Int -> {
        v.setMaxEms(arg)
        true
      }
      else -> false
    }
    "maxLines" -> when {
      v is TextView && arg is Int -> {
        v.setMaxLines(arg)
        true
      }
      else -> false
    }
    "minEms" -> when {
      v is TextView && arg is Int -> {
        v.setMinEms(arg)
        true
      }
      else -> false
    }
    "minHeight" -> when {
      v is TextView && arg is Int -> {
        v.setMinHeight(arg)
        true
      }
      else -> false
    }
    "minLines" -> when {
      v is TextView && arg is Int -> {
        v.setMinLines(arg)
        true
      }
      else -> false
    }
    "minWidth" -> when {
      v is TextView && arg is Int -> {
        v.setMinWidth(arg)
        true
      }
      else -> false
    }
    "movementMethod" -> when {
      v is TextView && arg is MovementMethod -> {
        v.setMovementMethod(arg)
        true
      }
      else -> false
    }
    "onEditorAction" -> when {
      v is TextView -> when {
        arg == null -> {
          v.setOnEditorActionListener(null as? TextView.OnEditorActionListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: TextView,
            arg1: Int,
            arg2: KeyEvent
          ) -> Boolean
          v.setOnEditorActionListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "paintFlags" -> when {
      v is TextView && arg is Int -> {
        v.setPaintFlags(arg)
        true
      }
      else -> false
    }
    "privateImeOptions" -> when {
      v is TextView && arg is String -> {
        v.setPrivateImeOptions(arg)
        true
      }
      else -> false
    }
    "rawInputType" -> when {
      v is TextView && arg is Int -> {
        v.setRawInputType(arg)
        true
      }
      else -> false
    }
    "scroller" -> when {
      v is TextView && arg is Scroller -> {
        v.setScroller(arg)
        true
      }
      else -> false
    }
    "selectAllOnFocus" -> when {
      v is TextView && arg is Boolean -> {
        v.setSelectAllOnFocus(arg)
        true
      }
      else -> false
    }
    "singleLine" -> when {
      v is TextView && arg is Boolean -> {
        v.setSingleLine(arg)
        true
      }
      else -> false
    }
    "spannableFactory" -> when {
      v is TextView && arg is Spannable.Factory -> {
        v.setSpannableFactory(arg)
        true
      }
      else -> false
    }
    "textColor" -> when {
      v is TextView && arg is ColorStateList -> {
        v.setTextColor(arg)
        true
      }
      v is TextView && arg is Int -> {
        v.setTextColor(arg)
        true
      }
      else -> false
    }
    "textIsSelectable" -> when {
      v is TextView && arg is Boolean -> {
        v.setTextIsSelectable(arg)
        true
      }
      else -> false
    }
    "textKeepState" -> when {
      v is TextView && arg is CharSequence -> {
        v.setTextKeepState(arg)
        true
      }
      else -> false
    }
    "textLocale" -> when {
      v is TextView && arg is Locale -> {
        v.setTextLocale(arg)
        true
      }
      else -> false
    }
    "textScaleX" -> when {
      v is TextView && arg is Float -> {
        v.setTextScaleX(arg)
        true
      }
      else -> false
    }
    "transformationMethod" -> when {
      v is TextView && arg is TransformationMethod -> {
        v.setTransformationMethod(arg)
        true
      }
      else -> false
    }
    "typeface" -> when {
      v is TextView && (arg == null || arg is Typeface) -> {
        v.setTypeface(arg as Typeface)
        true
      }
      else -> false
    }
    "width" -> when {
      v is TextView && arg is Int -> {
        v.setWidth(arg)
        true
      }
      else -> false
    }
    "currentHour" -> when {
      v is TimePicker && arg is Int -> {
        v.setCurrentHour(arg)
        true
      }
      else -> false
    }
    "currentMinute" -> when {
      v is TimePicker && arg is Int -> {
        v.setCurrentMinute(arg)
        true
      }
      else -> false
    }
    "is24HourView" -> when {
      v is TimePicker && arg is Boolean -> {
        v.setIs24HourView(arg)
        true
      }
      else -> false
    }
    "onTimeChanged" -> when {
      v is TimePicker -> when {
        arg == null -> {
          v.setOnTimeChangedListener(null as? TimePicker.OnTimeChangedListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: TimePicker,
            arg1: Int,
            arg2: Int
          ) -> Unit
          v.setOnTimeChangedListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "mediaController" -> when {
      v is VideoView && arg is MediaController -> {
        v.setMediaController(arg)
        true
      }
      else -> false
    }
    "onCompletion" -> when {
      v is VideoView -> when {
        arg == null -> {
          v.setOnCompletionListener(null as? MediaPlayer.OnCompletionListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: MediaPlayer) -> Unit
          v.setOnCompletionListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onError" -> when {
      v is VideoView -> when {
        arg == null -> {
          v.setOnErrorListener(null as? MediaPlayer.OnErrorListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: MediaPlayer,
            arg1: Int,
            arg2: Int
          ) -> Boolean
          v.setOnErrorListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onInfo" -> when {
      v is VideoView -> when {
        arg == null -> {
          v.setOnInfoListener(null as? MediaPlayer.OnInfoListener?)
          true
        }
        arg is Function<*> -> {
          arg as (
            arg0: MediaPlayer,
            arg1: Int,
            arg2: Int
          ) -> Boolean
          v.setOnInfoListener { arg0, arg1, arg2 ->
            arg(arg0, arg1, arg2).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onPrepared" -> when {
      v is VideoView -> when {
        arg == null -> {
          v.setOnPreparedListener(null as? MediaPlayer.OnPreparedListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: MediaPlayer) -> Unit
          v.setOnPreparedListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "videoPath" -> when {
      v is VideoView && arg is String -> {
        v.setVideoPath(arg)
        true
      }
      else -> false
    }
    "videoURI" -> when {
      v is VideoView && arg is Uri -> {
        v.setVideoURI(arg)
        true
      }
      else -> false
    }
    "factory" -> when {
      v is ViewSwitcher && arg is ViewSwitcher.ViewFactory -> {
        v.setFactory(arg)
        true
      }
      else -> false
    }
    "zoomSpeed" -> when {
      v is ZoomButton && arg is Long -> {
        v.setZoomSpeed(arg)
        true
      }
      v is ZoomControls && arg is Long -> {
        v.setZoomSpeed(arg)
        true
      }
      else -> false
    }
    "isZoomInEnabled" -> when {
      v is ZoomControls && arg is Boolean -> {
        v.setIsZoomInEnabled(arg)
        true
      }
      else -> false
    }
    "isZoomOutEnabled" -> when {
      v is ZoomControls && arg is Boolean -> {
        v.setIsZoomOutEnabled(arg)
        true
      }
      else -> false
    }
    "onZoomInClick" -> when {
      v is ZoomControls -> when {
        arg == null -> {
          v.setOnZoomInClickListener(null as? View.OnClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View) -> Unit
          v.setOnZoomInClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "onZoomOutClick" -> when {
      v is ZoomControls -> when {
        arg == null -> {
          v.setOnZoomOutClickListener(null as? View.OnClickListener?)
          true
        }
        arg is Function<*> -> {
          arg as (arg0: View) -> Unit
          v.setOnZoomOutClickListener { arg0 ->
            arg(arg0).also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    else -> false
  }
}
