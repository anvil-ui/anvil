@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.ForegroundLinearLayout
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.LinearLayoutCompatScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

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
