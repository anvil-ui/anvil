@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.slidingpanelayout.widget

import android.graphics.drawable.Drawable
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun slidingPaneLayout(configure: SlidingPaneLayoutScope.() -> Unit = {}) =
    v<SlidingPaneLayout>(configure.bind(SlidingPaneLayoutScope))
abstract class SlidingPaneLayoutScope : ViewGroupScope() {
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
      Inkremental.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
