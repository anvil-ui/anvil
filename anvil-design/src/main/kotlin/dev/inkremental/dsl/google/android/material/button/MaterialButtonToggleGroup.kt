@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.button

import com.google.android.material.button.MaterialButtonToggleGroup
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun materialButtonToggleGroup(configure: MaterialButtonToggleGroupScope.() -> Unit = {}) =
    v<MaterialButtonToggleGroup>(configure.bind(MaterialButtonToggleGroupScope))
abstract class MaterialButtonToggleGroupScope : LinearLayoutScope() {
  fun singleSelection(arg: Boolean): Unit = attr("singleSelection", arg)
  fun singleSelection(arg: Int): Unit = attr("singleSelection", arg)
  companion object : MaterialButtonToggleGroupScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
