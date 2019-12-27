@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.ButtonBarLayout
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun buttonBarLayout(configure: ButtonBarLayoutScope.() -> Unit = {}) =
    v<ButtonBarLayout>(configure.bind(ButtonBarLayoutScope))
abstract class ButtonBarLayoutScope : LinearLayoutScope() {
  fun allowStacking(arg: Boolean): Unit = attr("allowStacking", arg)
  companion object : ButtonBarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
