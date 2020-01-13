@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.transformation

import com.google.android.material.transformation.TransformationChildCard
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.circularreveal.cardview.CircularRevealCardViewScope
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun transformationChildCard(configure: TransformationChildCardScope.() -> Unit = {}) =
    v<TransformationChildCard>(configure.bind(TransformationChildCardScope))
abstract class TransformationChildCardScope : CircularRevealCardViewScope() {
  companion object : TransformationChildCardScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
