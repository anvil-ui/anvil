@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.ImageButton
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun imageButton(configure: ImageButtonScope.() -> Unit = {}) =
    v<ImageButton>(configure.bind(ImageButtonScope))
abstract class ImageButtonScope : ImageViewScope() {
  companion object : ImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
