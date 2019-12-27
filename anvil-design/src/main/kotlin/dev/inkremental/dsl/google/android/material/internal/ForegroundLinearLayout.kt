@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.ForegroundLinearLayout
import dev.inkremental.dsl.androidx.appcompat.widget.LinearLayoutCompatScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun foregroundLinearLayout(configure: ForegroundLinearLayoutScope.() -> Unit = {}) =
    v<ForegroundLinearLayout>(configure.bind(ForegroundLinearLayoutScope))
abstract class ForegroundLinearLayoutScope : LinearLayoutCompatScope() {
  companion object : ForegroundLinearLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
