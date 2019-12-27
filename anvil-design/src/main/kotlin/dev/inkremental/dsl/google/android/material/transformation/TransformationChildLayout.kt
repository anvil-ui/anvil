@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.transformation

import com.google.android.material.transformation.TransformationChildLayout
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.circularreveal.CircularRevealFrameLayoutScope
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun transformationChildLayout(configure: TransformationChildLayoutScope.() -> Unit = {}) =
    v<TransformationChildLayout>(configure.bind(TransformationChildLayoutScope))
abstract class TransformationChildLayoutScope : CircularRevealFrameLayoutScope() {
  companion object : TransformationChildLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
