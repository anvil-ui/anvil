@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.media.tv

import android.media.tv.TvView
import android.view.InputEvent
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Suppress
import kotlin.Unit

fun tvView(configure: TvViewScope.() -> Unit = {}) = v<TvView>(configure.bind(TvViewScope))
abstract class TvViewScope : ViewGroupScope() {
  fun callback(arg: TvView.TvInputCallback?): Unit = attr("callback", arg)
  fun captionEnabled(arg: Boolean): Unit = attr("captionEnabled", arg)
  fun onUnhandledInputEvent(arg: ((arg0: InputEvent) -> Boolean)?): Unit =
      attr("onUnhandledInputEvent", arg)
  fun streamVolume(arg: Float): Unit = attr("streamVolume", arg)
  companion object : TvViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
