@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package trikita.anvil

import android.graphics.drawable.Drawable
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager.widget.PagerTitleStrip
import androidx.viewpager.widget.ViewPager
import kotlin.Any
import kotlin.Boolean
import kotlin.Float
import kotlin.Function
import kotlin.Int
import kotlin.IntArray
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun coordinatorLayout(configure: CoordinatorLayoutScope.() -> Unit = {}) =
    v<CoordinatorLayout>(configure.bind(CoordinatorLayoutScope))
abstract class CoordinatorLayoutScope {
  fun statusBarBackground(arg: Drawable?): Unit = attr("statusBarBackground", arg)
  fun statusBarBackgroundColor(arg: Int): Unit = attr("statusBarBackgroundColor", arg)
  fun statusBarBackgroundResource(arg: Int): Unit = attr("statusBarBackgroundResource", arg)
  companion object : CoordinatorLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun contentLoadingProgressBar(configure: ContentLoadingProgressBarScope.() -> Unit = {}) =
    v<ContentLoadingProgressBar>(configure.bind(ContentLoadingProgressBarScope))
abstract class ContentLoadingProgressBarScope {
  companion object : ContentLoadingProgressBarScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun nestedScrollView(configure: NestedScrollViewScope.() -> Unit = {}) =
    v<NestedScrollView>(configure.bind(NestedScrollViewScope))
abstract class NestedScrollViewScope {
  fun fillViewport(arg: Boolean): Unit = attr("fillViewport", arg)
  fun smoothScrollingEnabled(arg: Boolean): Unit = attr("smoothScrollingEnabled", arg)
  companion object : NestedScrollViewScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun drawerLayout(configure: DrawerLayoutScope.() -> Unit = {}) =
    v<DrawerLayout>(configure.bind(DrawerLayoutScope))
abstract class DrawerLayoutScope {
  fun drawerElevation(arg: Float): Unit = attr("drawerElevation", arg)
  fun drawerLockMode(arg: Int): Unit = attr("drawerLockMode", arg)
  fun scrimColor(arg: Int): Unit = attr("scrimColor", arg)
  fun statusBarBackground(arg: Drawable?): Unit = attr("statusBarBackground", arg)
  fun statusBarBackground(arg: Int): Unit = attr("statusBarBackground", arg)
  fun statusBarBackgroundColor(arg: Int): Unit = attr("statusBarBackgroundColor", arg)
  companion object : DrawerLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun slidingPaneLayout(configure: SlidingPaneLayoutScope.() -> Unit = {}) =
    v<SlidingPaneLayout>(configure.bind(SlidingPaneLayoutScope))
abstract class SlidingPaneLayoutScope {
  fun coveredFadeColor(arg: Int): Unit = attr("coveredFadeColor", arg)
  fun panelSlideListener(arg: SlidingPaneLayout.PanelSlideListener?): Unit =
      attr("panelSlideListener", arg)
  fun parallaxDistance(arg: Int): Unit = attr("parallaxDistance", arg)
  fun shadowDrawableLeft(arg: Drawable?): Unit = attr("shadowDrawableLeft", arg)
  fun shadowDrawableRight(arg: Drawable?): Unit = attr("shadowDrawableRight", arg)
  fun shadowResourceLeft(arg: Int): Unit = attr("shadowResourceLeft", arg)
  fun shadowResourceRight(arg: Int): Unit = attr("shadowResourceRight", arg)
  fun sliderFadeColor(arg: Int): Unit = attr("sliderFadeColor", arg)
  companion object : SlidingPaneLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun swipeRefreshLayout(configure: SwipeRefreshLayoutScope.() -> Unit = {}) =
    v<SwipeRefreshLayout>(configure.bind(SwipeRefreshLayoutScope))
abstract class SwipeRefreshLayoutScope {
  fun colorSchemeColors(arg: IntArray): Unit = attr("colorSchemeColors", arg)
  fun colorSchemeResources(arg: IntArray): Unit = attr("colorSchemeResources", arg)
  fun distanceToTriggerSync(arg: Int): Unit = attr("distanceToTriggerSync", arg)
  fun onChildScrollUpCallback(arg: SwipeRefreshLayout.OnChildScrollUpCallback?): Unit =
      attr("onChildScrollUpCallback", arg)
  fun onRefresh(arg: (() -> Unit)?): Unit = attr("onRefresh", arg)
  fun progressBackgroundColorSchemeColor(arg: Int): Unit =
      attr("progressBackgroundColorSchemeColor", arg)
  fun progressBackgroundColorSchemeResource(arg: Int): Unit =
      attr("progressBackgroundColorSchemeResource", arg)
  fun refreshing(arg: Boolean): Unit = attr("refreshing", arg)
  fun size(arg: Int): Unit = attr("size", arg)
  fun slingshotDistance(arg: Int): Unit = attr("slingshotDistance", arg)
  companion object : SwipeRefreshLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun pagerTabStrip(configure: PagerTabStripScope.() -> Unit = {}) =
    v<PagerTabStrip>(configure.bind(PagerTabStripScope))
abstract class PagerTabStripScope : PagerTitleStripScope() {
  fun drawFullUnderline(arg: Boolean): Unit = attr("drawFullUnderline", arg)
  fun tabIndicatorColor(arg: Int): Unit = attr("tabIndicatorColor", arg)
  fun tabIndicatorColorResource(arg: Int): Unit = attr("tabIndicatorColorResource", arg)
  companion object : PagerTabStripScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun pagerTitleStrip(configure: PagerTitleStripScope.() -> Unit = {}) =
    v<PagerTitleStrip>(configure.bind(PagerTitleStripScope))
abstract class PagerTitleStripScope {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun nonPrimaryAlpha(arg: Float): Unit = attr("nonPrimaryAlpha", arg)
  fun textColor(arg: Int): Unit = attr("textColor", arg)
  fun textSpacing(arg: Int): Unit = attr("textSpacing", arg)
  companion object : PagerTitleStripScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

fun viewPager(configure: ViewPagerScope.() -> Unit = {}) =
    v<ViewPager>(configure.bind(ViewPagerScope))
abstract class ViewPagerScope {
  fun adapter(arg: PagerAdapter): Unit = attr("adapter", arg)
  fun currentItem(arg: Int): Unit = attr("currentItem", arg)
  fun offscreenPageLimit(arg: Int): Unit = attr("offscreenPageLimit", arg)
  fun pageMargin(arg: Int): Unit = attr("pageMargin", arg)
  fun pageMarginDrawable(arg: Drawable?): Unit = attr("pageMarginDrawable", arg)
  fun pageMarginDrawable(arg: Int): Unit = attr("pageMarginDrawable", arg)
  companion object : ViewPagerScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}

/**
 * DSL for creating views and settings their attributes.
 * This file has been generated by
 * {@code gradle generateSupportCoreUiDSL}
 * It contains views and their setters from the library support-core-ui.
 * Please, don't edit it manually unless for debugging.
 */
object SupportCoreUiSetter : Anvil.AttributeSetter<Any?> {
  override fun set(
    v: View,
    name: String,
    arg: Any?,
    old: Any?
  ): Boolean = when (name) {
    "statusBarBackground" -> when {
      v is CoordinatorLayout && arg is Drawable? -> {
        v.setStatusBarBackground(arg as Drawable)
        true
      }
      v is DrawerLayout && arg is Drawable? -> {
        v.setStatusBarBackground(arg as Drawable)
        true
      }
      v is DrawerLayout && arg is Int -> {
        v.setStatusBarBackground(arg)
        true
      }
      else -> false
    }
    "statusBarBackgroundColor" -> when {
      v is CoordinatorLayout && arg is Int -> {
        v.setStatusBarBackgroundColor(arg)
        true
      }
      v is DrawerLayout && arg is Int -> {
        v.setStatusBarBackgroundColor(arg)
        true
      }
      else -> false
    }
    "statusBarBackgroundResource" -> when {
      v is CoordinatorLayout && arg is Int -> {
        v.setStatusBarBackgroundResource(arg)
        true
      }
      else -> false
    }
    "fillViewport" -> when {
      v is NestedScrollView && arg is Boolean -> {
        v.setFillViewport(arg)
        true
      }
      else -> false
    }
    "smoothScrollingEnabled" -> when {
      v is NestedScrollView && arg is Boolean -> {
        v.setSmoothScrollingEnabled(arg)
        true
      }
      else -> false
    }
    "drawerElevation" -> when {
      v is DrawerLayout && arg is Float -> {
        v.setDrawerElevation(arg)
        true
      }
      else -> false
    }
    "drawerLockMode" -> when {
      v is DrawerLayout && arg is Int -> {
        v.setDrawerLockMode(arg)
        true
      }
      else -> false
    }
    "scrimColor" -> when {
      v is DrawerLayout && arg is Int -> {
        v.setScrimColor(arg)
        true
      }
      else -> false
    }
    "coveredFadeColor" -> when {
      v is SlidingPaneLayout && arg is Int -> {
        v.setCoveredFadeColor(arg)
        true
      }
      else -> false
    }
    "panelSlideListener" -> when {
      v is SlidingPaneLayout && arg is SlidingPaneLayout.PanelSlideListener? -> {
        v.setPanelSlideListener(arg as SlidingPaneLayout.PanelSlideListener)
        true
      }
      else -> false
    }
    "parallaxDistance" -> when {
      v is SlidingPaneLayout && arg is Int -> {
        v.setParallaxDistance(arg)
        true
      }
      else -> false
    }
    "shadowDrawableLeft" -> when {
      v is SlidingPaneLayout && arg is Drawable? -> {
        v.setShadowDrawableLeft(arg as Drawable)
        true
      }
      else -> false
    }
    "shadowDrawableRight" -> when {
      v is SlidingPaneLayout && arg is Drawable? -> {
        v.setShadowDrawableRight(arg as Drawable)
        true
      }
      else -> false
    }
    "shadowResourceLeft" -> when {
      v is SlidingPaneLayout && arg is Int -> {
        v.setShadowResourceLeft(arg)
        true
      }
      else -> false
    }
    "shadowResourceRight" -> when {
      v is SlidingPaneLayout && arg is Int -> {
        v.setShadowResourceRight(arg)
        true
      }
      else -> false
    }
    "sliderFadeColor" -> when {
      v is SlidingPaneLayout && arg is Int -> {
        v.setSliderFadeColor(arg)
        true
      }
      else -> false
    }
    "colorSchemeColors" -> when {
      v is SwipeRefreshLayout && arg is IntArray -> {
        v.setColorSchemeColors(*arg)
        true
      }
      else -> false
    }
    "colorSchemeResources" -> when {
      v is SwipeRefreshLayout && arg is IntArray -> {
        v.setColorSchemeResources(*arg)
        true
      }
      else -> false
    }
    "distanceToTriggerSync" -> when {
      v is SwipeRefreshLayout && arg is Int -> {
        v.setDistanceToTriggerSync(arg)
        true
      }
      else -> false
    }
    "onChildScrollUpCallback" -> when {
      v is SwipeRefreshLayout && arg is SwipeRefreshLayout.OnChildScrollUpCallback? -> {
        v.setOnChildScrollUpCallback(arg as SwipeRefreshLayout.OnChildScrollUpCallback)
        true
      }
      else -> false
    }
    "onRefresh" -> when {
      v is SwipeRefreshLayout -> when {
        arg == null -> {
          v.setOnRefreshListener(null as? SwipeRefreshLayout.OnRefreshListener?)
          true
        }
        arg is Function<*> -> {
          arg as (() -> Unit)?
          v.setOnRefreshListener {  ->
            arg().also { Anvil.render() }
          }
          true
        }
        else -> false
      }
      else -> false
    }
    "progressBackgroundColorSchemeColor" -> when {
      v is SwipeRefreshLayout && arg is Int -> {
        v.setProgressBackgroundColorSchemeColor(arg)
        true
      }
      else -> false
    }
    "progressBackgroundColorSchemeResource" -> when {
      v is SwipeRefreshLayout && arg is Int -> {
        v.setProgressBackgroundColorSchemeResource(arg)
        true
      }
      else -> false
    }
    "refreshing" -> when {
      v is SwipeRefreshLayout && arg is Boolean -> {
        v.setRefreshing(arg)
        true
      }
      else -> false
    }
    "size" -> when {
      v is SwipeRefreshLayout && arg is Int -> {
        v.setSize(arg)
        true
      }
      else -> false
    }
    "slingshotDistance" -> when {
      v is SwipeRefreshLayout && arg is Int -> {
        v.setSlingshotDistance(arg)
        true
      }
      else -> false
    }
    "drawFullUnderline" -> when {
      v is PagerTabStrip && arg is Boolean -> {
        v.setDrawFullUnderline(arg)
        true
      }
      else -> false
    }
    "tabIndicatorColor" -> when {
      v is PagerTabStrip && arg is Int -> {
        v.setTabIndicatorColor(arg)
        true
      }
      else -> false
    }
    "tabIndicatorColorResource" -> when {
      v is PagerTabStrip && arg is Int -> {
        v.setTabIndicatorColorResource(arg)
        true
      }
      else -> false
    }
    "gravity" -> when {
      v is PagerTitleStrip && arg is Int -> {
        v.setGravity(arg)
        true
      }
      else -> false
    }
    "nonPrimaryAlpha" -> when {
      v is PagerTitleStrip && arg is Float -> {
        v.setNonPrimaryAlpha(arg)
        true
      }
      else -> false
    }
    "textColor" -> when {
      v is PagerTitleStrip && arg is Int -> {
        v.setTextColor(arg)
        true
      }
      else -> false
    }
    "textSpacing" -> when {
      v is PagerTitleStrip && arg is Int -> {
        v.setTextSpacing(arg)
        true
      }
      else -> false
    }
    "adapter" -> when {
      v is ViewPager && arg is PagerAdapter -> {
        v.setAdapter(arg)
        true
      }
      else -> false
    }
    "currentItem" -> when {
      v is ViewPager && arg is Int -> {
        v.setCurrentItem(arg)
        true
      }
      else -> false
    }
    "offscreenPageLimit" -> when {
      v is ViewPager && arg is Int -> {
        v.setOffscreenPageLimit(arg)
        true
      }
      else -> false
    }
    "pageMargin" -> when {
      v is ViewPager && arg is Int -> {
        v.setPageMargin(arg)
        true
      }
      else -> false
    }
    "pageMarginDrawable" -> when {
      v is ViewPager && arg is Drawable? -> {
        v.setPageMarginDrawable(arg as Drawable)
        true
      }
      v is ViewPager && arg is Int -> {
        v.setPageMarginDrawable(arg)
        true
      }
      else -> false
    }
    else -> false
  }
}
