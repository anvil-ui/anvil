@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ZoomButton
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit

fun zoomButton(configure: ZoomButtonScope.() -> Unit = {}) =
    v<ZoomButton>(configure.bind(ZoomButtonScope))
abstract class ZoomButtonScope : ImageButtonScope() {
  fun zoomSpeed(arg: Long): Unit = attr("zoomSpeed", arg)
  companion object : ZoomButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
