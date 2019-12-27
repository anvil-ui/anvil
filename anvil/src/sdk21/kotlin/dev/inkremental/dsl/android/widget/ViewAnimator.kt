@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.view.animation.Animation
import android.widget.ViewAnimator
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

fun viewAnimator(configure: ViewAnimatorScope.() -> Unit = {}) =
    v<ViewAnimator>(configure.bind(ViewAnimatorScope))
abstract class ViewAnimatorScope : FrameLayoutScope() {
  fun animateFirstView(arg: Boolean): Unit = attr("animateFirstView", arg)
  fun displayedChild(arg: Int): Unit = attr("displayedChild", arg)
  fun inAnimation(arg: Animation): Unit = attr("inAnimation", arg)
  fun outAnimation(arg: Animation): Unit = attr("outAnimation", arg)
  companion object : ViewAnimatorScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
