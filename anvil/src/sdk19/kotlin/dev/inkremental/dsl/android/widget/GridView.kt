@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.GridView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun gridView(configure: GridViewScope.() -> Unit = {}) = v<GridView>(configure.bind(GridViewScope))
abstract class GridViewScope : AbsListViewScope() {
  fun columnWidth(arg: Int): Unit = attr("columnWidth", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalSpacing(arg: Int): Unit = attr("horizontalSpacing", arg)
  fun numColumns(arg: Int): Unit = attr("numColumns", arg)
  fun stretchMode(arg: Int): Unit = attr("stretchMode", arg)
  fun verticalSpacing(arg: Int): Unit = attr("verticalSpacing", arg)
  companion object : GridViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
