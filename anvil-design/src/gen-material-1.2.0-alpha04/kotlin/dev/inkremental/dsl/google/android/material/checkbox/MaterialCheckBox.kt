@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.checkbox

import com.google.android.material.checkbox.MaterialCheckBox
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatCheckBoxScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun materialCheckBox(configure: MaterialCheckBoxScope.() -> Unit = {}) =
    v<MaterialCheckBox>(configure.bind(MaterialCheckBoxScope))
abstract class MaterialCheckBoxScope : AppCompatCheckBoxScope() {
  fun useMaterialThemeColors(arg: Boolean): Unit = attr("useMaterialThemeColors", arg)
  companion object : MaterialCheckBoxScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
