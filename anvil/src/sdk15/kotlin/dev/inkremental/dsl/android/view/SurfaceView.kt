@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.view.SurfaceView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun surfaceView(configure: SurfaceViewScope.() -> Unit = {}) =
    v<SurfaceView>(configure.bind(SurfaceViewScope))
abstract class SurfaceViewScope : ViewScope() {
  fun zOrderMediaOverlay(arg: Boolean): Unit = attr("zOrderMediaOverlay", arg)
  fun zOrderOnTop(arg: Boolean): Unit = attr("zOrderOnTop", arg)
  companion object : SurfaceViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
