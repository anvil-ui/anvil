@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import dev.inkremental.Inkremental
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
  fun baseline(arg: Int): Unit = attr("baseline", arg)
  fun baselineAlignBottom(arg: Boolean): Unit = attr("baselineAlignBottom", arg)
  fun colorFilter(arg: ColorFilter): Unit = attr("colorFilter", arg)
  fun colorFilter(arg: Int): Unit = attr("colorFilter", arg)
  fun cropToPadding(arg: Boolean): Unit = attr("cropToPadding", arg)
  fun imageAlpha(arg: Int): Unit = attr("imageAlpha", arg)
  fun imageBitmap(arg: Bitmap): Unit = attr("imageBitmap", arg)
  fun imageDrawable(arg: Drawable?): Unit = attr("imageDrawable", arg)
  fun imageLevel(arg: Int): Unit = attr("imageLevel", arg)
  fun imageMatrix(arg: Matrix): Unit = attr("imageMatrix", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageTintList(arg: ColorStateList?): Unit = attr("imageTintList", arg)
  fun imageTintMode(arg: PorterDuff.Mode?): Unit = attr("imageTintMode", arg)
  fun imageURI(arg: Uri?): Unit = attr("imageURI", arg)
  fun maxHeight(arg: Int): Unit = attr("maxHeight", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun scaleType(arg: ImageView.ScaleType): Unit = attr("scaleType", arg)
  companion object : ImageViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
