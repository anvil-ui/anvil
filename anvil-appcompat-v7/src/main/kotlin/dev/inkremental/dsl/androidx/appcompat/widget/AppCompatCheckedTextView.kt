@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.AppCompatCheckedTextView
import dev.inkremental.dsl.android.widget.CheckedTextViewScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.bind
import trikita.anvil.v

fun appCompatCheckedTextView(configure: AppCompatCheckedTextViewScope.() -> Unit = {}) =
    v<AppCompatCheckedTextView>(configure.bind(AppCompatCheckedTextViewScope))
abstract class AppCompatCheckedTextViewScope : CheckedTextViewScope() {
  companion object : AppCompatCheckedTextViewScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
