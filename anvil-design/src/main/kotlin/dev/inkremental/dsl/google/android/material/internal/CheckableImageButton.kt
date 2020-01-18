@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.CheckableImageButton
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatImageButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun checkableImageButton(configure: CheckableImageButtonScope.() -> Unit = {}) =
    v<CheckableImageButton>(configure.bind(CheckableImageButtonScope))
abstract class CheckableImageButtonScope : AppCompatImageButtonScope() {
  companion object : CheckableImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
