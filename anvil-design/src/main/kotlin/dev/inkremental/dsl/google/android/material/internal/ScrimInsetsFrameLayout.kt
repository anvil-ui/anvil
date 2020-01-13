@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.ScrimInsetsFrameLayout
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun scrimInsetsFrameLayout(configure: ScrimInsetsFrameLayoutScope.() -> Unit = {}) =
    v<ScrimInsetsFrameLayout>(configure.bind(ScrimInsetsFrameLayoutScope))
abstract class ScrimInsetsFrameLayoutScope : FrameLayoutScope() {
  companion object : ScrimInsetsFrameLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
