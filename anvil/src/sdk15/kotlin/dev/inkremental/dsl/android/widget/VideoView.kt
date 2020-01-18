@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.SurfaceViewScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun videoView(configure: VideoViewScope.() -> Unit = {}) =
    v<VideoView>(configure.bind(VideoViewScope))
abstract class VideoViewScope : SurfaceViewScope() {
  fun mediaController(arg: MediaController): Unit = attr("mediaController", arg)
  fun onCompletion(arg: ((arg0: MediaPlayer) -> Unit)?): Unit = attr("onCompletion", arg)
  fun onError(arg: ((
    arg0: MediaPlayer,
    arg1: Int,
    arg2: Int
  ) -> Boolean)?): Unit = attr("onError", arg)
  fun onPrepared(arg: ((arg0: MediaPlayer) -> Unit)?): Unit = attr("onPrepared", arg)
  fun videoPath(arg: String): Unit = attr("videoPath", arg)
  fun videoURI(arg: Uri): Unit = attr("videoURI", arg)
  companion object : VideoViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
