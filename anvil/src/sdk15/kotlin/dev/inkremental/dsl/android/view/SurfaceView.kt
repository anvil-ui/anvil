@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.view.SurfaceView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

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
