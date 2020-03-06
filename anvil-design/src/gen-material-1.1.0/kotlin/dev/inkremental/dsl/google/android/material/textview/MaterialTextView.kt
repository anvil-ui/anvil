@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.textview

import com.google.android.material.textview.MaterialTextView
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatTextViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun materialTextView(configure: MaterialTextViewScope.() -> Unit = {}) =
    v<MaterialTextView>(configure.bind(MaterialTextViewScope))
abstract class MaterialTextViewScope : AppCompatTextViewScope() {
  companion object : MaterialTextViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
