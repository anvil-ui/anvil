@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageSwitcher
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun imageSwitcher(configure: ImageSwitcherScope.() -> Unit = {}) =
    v<ImageSwitcher>(configure.bind(ImageSwitcherScope))
abstract class ImageSwitcherScope : ViewSwitcherScope() {
  fun imageDrawable(arg: Drawable): Unit = attr("imageDrawable", arg)
  fun imageResource(arg: Int): Unit = attr("imageResource", arg)
  fun imageURI(arg: Uri): Unit = attr("imageURI", arg)
  companion object : ImageSwitcherScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
