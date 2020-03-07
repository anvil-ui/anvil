@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.tabs

import com.google.android.material.tabs.TabItem
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun tabItem(configure: TabItemScope.() -> Unit = {}) = v<TabItem>(configure.bind(TabItemScope))
abstract class TabItemScope : ViewScope() {
  companion object : TabItemScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
