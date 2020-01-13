@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.CheckableImageButton
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatImageButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

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
