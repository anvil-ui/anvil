@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.radiobutton

import com.google.android.material.radiobutton.MaterialRadioButton
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatRadioButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun materialRadioButton(configure: MaterialRadioButtonScope.() -> Unit = {}) =
    v<MaterialRadioButton>(configure.bind(MaterialRadioButtonScope))
abstract class MaterialRadioButtonScope : AppCompatRadioButtonScope() {
  fun useMaterialThemeColors(arg: Boolean): Unit = attr("useMaterialThemeColors", arg)
  companion object : MaterialRadioButtonScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
