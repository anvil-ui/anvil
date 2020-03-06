@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.switchmaterial

import com.google.android.material.switchmaterial.SwitchMaterial
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.SwitchCompatScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun switchMaterial(configure: SwitchMaterialScope.() -> Unit = {}) =
    v<SwitchMaterial>(configure.bind(SwitchMaterialScope))
abstract class SwitchMaterialScope : SwitchCompatScope() {
  fun useMaterialThemeColors(arg: Boolean): Unit = attr("useMaterialThemeColors", arg)
  companion object : SwitchMaterialScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
