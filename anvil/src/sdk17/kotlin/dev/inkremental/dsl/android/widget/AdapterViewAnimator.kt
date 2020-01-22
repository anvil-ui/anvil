@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.animation.ObjectAnimator
import android.content.Intent
import android.widget.AdapterViewAnimator
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

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
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
