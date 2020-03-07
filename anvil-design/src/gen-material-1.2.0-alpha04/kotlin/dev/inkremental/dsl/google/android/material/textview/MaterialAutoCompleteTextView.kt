@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.textview

import com.google.android.material.textview.MaterialAutoCompleteTextView
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatAutoCompleteTextViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun materialAutoCompleteTextView(configure: MaterialAutoCompleteTextViewScope.() -> Unit = {}) =
    v<MaterialAutoCompleteTextView>(configure.bind(MaterialAutoCompleteTextViewScope))
abstract class MaterialAutoCompleteTextViewScope : AppCompatAutoCompleteTextViewScope() {
  companion object : MaterialAutoCompleteTextViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
