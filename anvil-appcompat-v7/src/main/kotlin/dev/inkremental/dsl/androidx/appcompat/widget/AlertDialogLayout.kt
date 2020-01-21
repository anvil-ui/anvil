@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import androidx.appcompat.widget.AlertDialogLayout
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun alertDialogLayout(configure: AlertDialogLayoutScope.() -> Unit = {}) =
    v<AlertDialogLayout>(configure.bind(AlertDialogLayoutScope))
abstract class AlertDialogLayoutScope : LinearLayoutCompatScope() {
  companion object : AlertDialogLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
