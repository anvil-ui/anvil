@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.appbar

import com.google.android.material.appbar.AppBarLayout
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun appBarLayout(configure: AppBarLayoutScope.() -> Unit = {}) =
    v<AppBarLayout>(configure.bind(AppBarLayoutScope))
abstract class AppBarLayoutScope : LinearLayoutScope() {
  fun expanded(arg: Boolean): Unit = attr("expanded", arg)
  fun liftOnScroll(arg: Boolean): Unit = attr("liftOnScroll", arg)
  fun liftable(arg: Boolean): Unit = attr("liftable", arg)
  fun lifted(arg: Boolean): Unit = attr("lifted", arg)
  companion object : AppBarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
