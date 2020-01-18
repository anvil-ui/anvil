@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.NavigationMenuView
import dev.inkremental.Anvil
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.recyclerview.widget.RecyclerViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun navigationMenuView(configure: NavigationMenuViewScope.() -> Unit = {}) =
    v<NavigationMenuView>(configure.bind(NavigationMenuViewScope))
abstract class NavigationMenuViewScope : RecyclerViewScope() {
  companion object : NavigationMenuViewScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
