@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.internal

import com.google.android.material.internal.BaselineLayout
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun baselineLayout(configure: BaselineLayoutScope.() -> Unit = {}) =
    v<BaselineLayout>(configure.bind(BaselineLayoutScope))
abstract class BaselineLayoutScope : ViewGroupScope() {
  companion object : BaselineLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
