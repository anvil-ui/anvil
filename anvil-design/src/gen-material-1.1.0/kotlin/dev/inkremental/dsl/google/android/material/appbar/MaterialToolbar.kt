@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.appbar

import com.google.android.material.appbar.MaterialToolbar
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.ToolbarScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun materialToolbar(configure: MaterialToolbarScope.() -> Unit = {}) =
    v<MaterialToolbar>(configure.bind(MaterialToolbarScope))
abstract class MaterialToolbarScope : ToolbarScope() {
  companion object : MaterialToolbarScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
