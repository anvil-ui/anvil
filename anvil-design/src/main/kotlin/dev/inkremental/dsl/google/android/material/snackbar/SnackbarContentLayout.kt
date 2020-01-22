@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.snackbar

import com.google.android.material.snackbar.SnackbarContentLayout
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun snackbarContentLayout(configure: SnackbarContentLayoutScope.() -> Unit = {}) =
    v<SnackbarContentLayout>(configure.bind(SnackbarContentLayoutScope))
abstract class SnackbarContentLayoutScope : LinearLayoutScope() {
  companion object : SnackbarContentLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
