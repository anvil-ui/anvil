@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.ButtonBarLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Suppress
import kotlin.Unit

fun buttonBarLayout(configure: ButtonBarLayoutScope.() -> Unit = {}) =
    v<ButtonBarLayout>(configure.bind(ButtonBarLayoutScope))
abstract class ButtonBarLayoutScope : LinearLayoutScope() {
  fun allowStacking(arg: Boolean): Unit = attr("allowStacking", arg)
  companion object : ButtonBarLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
