@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageSwitcher
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun imageSwitcher(configure: ImageSwitcherScope.() -> Unit = {}) =
    v<ImageSwitcher>(configure.bind(ImageSwitcherScope))
abstract class ImageSwitcherScope : ViewSwitcherScope() {
  fun imageDrawable(arg: Drawable): Unit = attr("imageDrawable", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageURI(arg: Uri): Unit = attr("imageURI", arg)
  companion object : ImageSwitcherScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
