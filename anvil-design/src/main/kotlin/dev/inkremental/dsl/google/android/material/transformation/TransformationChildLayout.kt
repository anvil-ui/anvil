@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.transformation

import com.google.android.material.transformation.TransformationChildLayout
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.circularreveal.CircularRevealFrameLayoutScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

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
