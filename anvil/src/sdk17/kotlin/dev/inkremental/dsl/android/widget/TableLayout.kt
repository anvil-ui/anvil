@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.TableLayout
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun tableLayout(configure: TableLayoutScope.() -> Unit = {}) =
    v<TableLayout>(configure.bind(TableLayoutScope))
abstract class TableLayoutScope : LinearLayoutScope() {
  fun shrinkAllColumns(arg: Boolean): Unit = attr("shrinkAllColumns", arg)
  fun stretchAllColumns(arg: Boolean): Unit = attr("stretchAllColumns", arg)
  companion object : TableLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
