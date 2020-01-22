@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.bottomnavigation

import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun bottomNavigationMenuView(configure: BottomNavigationMenuViewScope.() -> Unit = {}) =
    v<BottomNavigationMenuView>(configure.bind(BottomNavigationMenuViewScope))
abstract class BottomNavigationMenuViewScope : ViewGroupScope() {
  companion object : BottomNavigationMenuViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
