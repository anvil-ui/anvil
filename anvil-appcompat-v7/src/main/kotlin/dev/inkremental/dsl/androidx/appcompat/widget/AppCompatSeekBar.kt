@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.AppCompatSeekBar
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.SeekBarScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun appCompatSeekBar(configure: AppCompatSeekBarScope.() -> Unit = {}) =
    v<AppCompatSeekBar>(configure.bind(AppCompatSeekBarScope))
abstract class AppCompatSeekBarScope : SeekBarScope() {
  companion object : AppCompatSeekBarScope() {
    init {
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
