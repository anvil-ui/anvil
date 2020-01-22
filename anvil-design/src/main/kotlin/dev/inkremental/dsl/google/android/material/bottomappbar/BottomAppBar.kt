@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.bottomappbar

import android.content.res.ColorStateList
import com.google.android.material.bottomappbar.BottomAppBar
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.ToolbarScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun bottomAppBar(configure: BottomAppBarScope.() -> Unit = {}) =
    v<BottomAppBar>(configure.bind(BottomAppBarScope))
abstract class BottomAppBarScope : ToolbarScope() {
  fun backgroundTint(arg: ColorStateList?): Unit = attr("backgroundTint", arg)
  fun cradleVerticalOffset(arg: Float): Unit = attr("cradleVerticalOffset", arg)
  fun fabAlignmentMode(arg: Int): Unit = attr("fabAlignmentMode", arg)
  fun fabCradleMargin(arg: Float): Unit = attr("fabCradleMargin", arg)
  fun fabCradleRoundedCornerRadius(arg: Float): Unit = attr("fabCradleRoundedCornerRadius", arg)
  fun hideOnScroll(arg: Boolean): Unit = attr("hideOnScroll", arg)
  companion object : BottomAppBarScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
