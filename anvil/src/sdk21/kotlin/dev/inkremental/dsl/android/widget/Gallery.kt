@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.Gallery
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun gallery(configure: GalleryScope.() -> Unit = {}) = v<Gallery>(configure.bind(GalleryScope))
abstract class GalleryScope : AbsSpinnerScope() {
  fun animationDuration(arg: Int): Unit = attr("animationDuration", arg)
  fun callbackDuringFling(arg: Boolean): Unit = attr("callbackDuringFling", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun spacing(arg: Int): Unit = attr("spacing", arg)
  fun unselectedAlpha(arg: Float): Unit = attr("unselectedAlpha", arg)
  companion object : GalleryScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
