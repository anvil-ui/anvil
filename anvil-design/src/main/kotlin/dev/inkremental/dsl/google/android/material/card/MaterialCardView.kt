@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.card

import com.google.android.material.card.MaterialCardView
import dev.inkremental.dsl.androidx.cardview.widget.CardViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun materialCardView(configure: MaterialCardViewScope.() -> Unit = {}) =
    v<MaterialCardView>(configure.bind(MaterialCardViewScope))
abstract class MaterialCardViewScope : CardViewScope() {
  fun strokeColor(arg: Int): Unit = attr("strokeColor", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  companion object : MaterialCardViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
