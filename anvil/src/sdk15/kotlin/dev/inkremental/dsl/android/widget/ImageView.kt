@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun imageView(configure: ImageViewScope.() -> Unit = {}) =
    v<ImageView>(configure.bind(ImageViewScope))
abstract class ImageViewScope : ViewScope() {
  fun adjustViewBounds(arg: Boolean): Unit = attr("adjustViewBounds", arg)
  fun alpha(arg: Int): Unit = attr("alpha", arg)
  fun baseline(arg: Int): Unit = attr("baseline", arg)
  fun baselineAlignBottom(arg: Boolean): Unit = attr("baselineAlignBottom", arg)
  fun colorFilter(arg: ColorFilter): Unit = attr("colorFilter", arg)
  fun colorFilter(arg: Int): Unit = attr("colorFilter", arg)
  fun imageBitmap(arg: Bitmap): Unit = attr("imageBitmap", arg)
  fun imageDrawable(arg: Drawable?): Unit = attr("imageDrawable", arg)
  fun imageLevel(arg: Int): Unit = attr("imageLevel", arg)
  fun imageMatrix(arg: Matrix): Unit = attr("imageMatrix", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageURI(arg: Uri?): Unit = attr("imageURI", arg)
  fun maxHeight(arg: Int): Unit = attr("maxHeight", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun scaleType(arg: ImageView.ScaleType): Unit = attr("scaleType", arg)
  companion object : ImageViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
