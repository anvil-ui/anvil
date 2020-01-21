@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.view.View
import android.widget.MediaController
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun mediaController(configure: MediaControllerScope.() -> Unit = {}) =
    v<MediaController>(configure.bind(MediaControllerScope))
abstract class MediaControllerScope : FrameLayoutScope() {
  fun anchorView(arg: View): Unit = attr("anchorView", arg)
  fun mediaPlayer(arg: MediaController.MediaPlayerControl): Unit = attr("mediaPlayer", arg)
  companion object : MediaControllerScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
