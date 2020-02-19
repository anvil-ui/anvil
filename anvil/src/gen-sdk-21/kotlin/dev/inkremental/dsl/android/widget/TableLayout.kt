@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.TableLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun tableLayout(configure: TableLayoutScope.() -> Unit = {}) =
    v<TableLayout>(configure.bind(TableLayoutScope))
abstract class TableLayoutScope : LinearLayoutScope() {
  fun shrinkAllColumns(arg: Boolean): Unit = attr("shrinkAllColumns", arg)
  fun stretchAllColumns(arg: Boolean): Unit = attr("stretchAllColumns", arg)
  companion object : TableLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
