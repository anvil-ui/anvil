@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.card

import com.google.android.material.card.MaterialCardView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.cardview.widget.CardViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun materialCardView(configure: MaterialCardViewScope.() -> Unit = {}) =
    v<MaterialCardView>(configure.bind(MaterialCardViewScope))
abstract class MaterialCardViewScope : CardViewScope() {
  fun strokeColor(arg: Int): Unit = attr("strokeColor", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  companion object : MaterialCardViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
