@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.VisibilityAwareImageButton
import dev.inkremental.dsl.android.widget.ImageButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun visibilityAwareImageButton(configure: VisibilityAwareImageButtonScope.() -> Unit = {}) =
    v<VisibilityAwareImageButton>(configure.bind(VisibilityAwareImageButtonScope))
abstract class VisibilityAwareImageButtonScope : ImageButtonScope() {
  companion object : VisibilityAwareImageButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
