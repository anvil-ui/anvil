@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.textfield

import com.google.android.material.textfield.TextInputEditText
import dev.inkremental.Inkremental
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatEditTextScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Suppress
import kotlin.Unit

fun textInputEditText(configure: TextInputEditTextScope.() -> Unit = {}) =
    v<TextInputEditText>(configure.bind(TextInputEditTextScope))
abstract class TextInputEditTextScope : AppCompatEditTextScope() {
  companion object : TextInputEditTextScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
