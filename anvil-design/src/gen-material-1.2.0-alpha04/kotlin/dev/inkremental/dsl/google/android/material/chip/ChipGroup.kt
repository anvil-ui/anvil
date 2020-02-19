@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.chip

import com.google.android.material.chip.ChipGroup
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.internal.FlowLayoutScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun chipGroup(configure: ChipGroupScope.() -> Unit = {}) =
    v<ChipGroup>(configure.bind(ChipGroupScope))
abstract class ChipGroupScope : FlowLayoutScope() {
  fun chipSpacing(arg: Int): Unit = attr("chipSpacing", arg)
  fun chipSpacingHorizontal(arg: Int): Unit = attr("chipSpacingHorizontal", arg)
  fun chipSpacingHorizontalResource(arg: Int): Unit = attr("chipSpacingHorizontalResource", arg)
  fun chipSpacingResource(arg: Int): Unit = attr("chipSpacingResource", arg)
  fun chipSpacingVertical(arg: Int): Unit = attr("chipSpacingVertical", arg)
  fun chipSpacingVerticalResource(arg: Int): Unit = attr("chipSpacingVerticalResource", arg)
  fun onCheckedChange(arg: ((arg0: ChipGroup, arg1: Int) -> Unit)?): Unit = attr("onCheckedChange",
      arg)
  fun selectionRequired(arg: Boolean): Unit = attr("selectionRequired", arg)
  fun singleLine(arg: Int): Unit = attr("singleLine", arg)
  fun singleSelection(arg: Boolean): Unit = attr("singleSelection", arg)
  fun singleSelection(arg: Int): Unit = attr("singleSelection", arg)
  companion object : ChipGroupScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
