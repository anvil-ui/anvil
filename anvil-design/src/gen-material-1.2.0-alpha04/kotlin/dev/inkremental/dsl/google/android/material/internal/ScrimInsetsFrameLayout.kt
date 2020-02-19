@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import android.graphics.drawable.Drawable
import com.google.android.material.internal.ScrimInsetsFrameLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun scrimInsetsFrameLayout(configure: ScrimInsetsFrameLayoutScope.() -> Unit = {}) =
    v<ScrimInsetsFrameLayout>(configure.bind(ScrimInsetsFrameLayoutScope))
abstract class ScrimInsetsFrameLayoutScope : FrameLayoutScope() {
  fun drawBottomInsetForeground(arg: Boolean): Unit = attr("drawBottomInsetForeground", arg)
  fun drawTopInsetForeground(arg: Boolean): Unit = attr("drawTopInsetForeground", arg)
  fun scrimInsetForeground(arg: Drawable?): Unit = attr("scrimInsetForeground", arg)
  companion object : ScrimInsetsFrameLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
