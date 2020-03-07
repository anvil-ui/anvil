@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.transformation

import com.google.android.material.transformation.TransformationChildCard
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.circularreveal.cardview.CircularRevealCardViewScope
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun transformationChildCard(configure: TransformationChildCardScope.() -> Unit = {}) =
    v<TransformationChildCard>(configure.bind(TransformationChildCardScope))
abstract class TransformationChildCardScope : CircularRevealCardViewScope() {
  companion object : TransformationChildCardScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
