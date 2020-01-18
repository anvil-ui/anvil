@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.animation.LayoutTransition
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LayoutAnimationController
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

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
  fun layoutTransition(arg: LayoutTransition): Unit = attr("layoutTransition", arg)
  fun motionEventSplittingEnabled(arg: Boolean): Unit = attr("motionEventSplittingEnabled", arg)
  fun onHierarchyChange(arg: ViewGroup.OnHierarchyChangeListener?): Unit = attr("onHierarchyChange",
      arg)
  fun persistentDrawingCache(arg: Int): Unit = attr("persistentDrawingCache", arg)
  companion object : ViewGroupScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
