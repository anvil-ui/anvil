@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.CheckableImageButton
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatImageButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun checkableImageButton(configure: CheckableImageButtonScope.() -> Unit = {}) =
    v<CheckableImageButton>(configure.bind(CheckableImageButtonScope))
abstract class CheckableImageButtonScope : AppCompatImageButtonScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun pressable(arg: Boolean): Unit = attr("pressable", arg)
  companion object : CheckableImageButtonScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
