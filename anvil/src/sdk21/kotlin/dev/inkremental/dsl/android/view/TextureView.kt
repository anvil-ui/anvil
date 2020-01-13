@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.view.TextureView
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun textureView(configure: TextureViewScope.() -> Unit = {}) =
    v<TextureView>(configure.bind(TextureViewScope))
abstract class TextureViewScope : ViewScope() {
  fun opaque(arg: Boolean): Unit = attr("opaque", arg)
  fun surfaceTexture(arg: SurfaceTexture): Unit = attr("surfaceTexture", arg)
  fun surfaceTextureListener(arg: TextureView.SurfaceTextureListener): Unit =
      attr("surfaceTextureListener", arg)
  fun transform(arg: Matrix): Unit = attr("transform", arg)
  companion object : TextureViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
