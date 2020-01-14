@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.animation.ObjectAnimator
import android.content.Intent
import android.widget.AdapterViewAnimator
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

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
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
