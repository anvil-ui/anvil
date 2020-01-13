@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.bottomnavigation

import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun bottomNavigationMenuView(configure: BottomNavigationMenuViewScope.() -> Unit = {}) =
    v<BottomNavigationMenuView>(configure.bind(BottomNavigationMenuViewScope))
abstract class BottomNavigationMenuViewScope : ViewGroupScope() {
  companion object : BottomNavigationMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
