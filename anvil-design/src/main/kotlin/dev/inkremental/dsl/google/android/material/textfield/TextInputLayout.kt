@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.textfield

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.google.android.material.textfield.TextInputLayout
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun textInputLayout(configure: TextInputLayoutScope.() -> Unit = {}) =
    v<TextInputLayout>(configure.bind(TextInputLayoutScope))
abstract class TextInputLayoutScope : LinearLayoutScope() {
  fun boxBackgroundColor(arg: Int): Unit = attr("boxBackgroundColor", arg)
  fun boxBackgroundColorResource(arg: Int): Unit = attr("boxBackgroundColorResource", arg)
  fun boxBackgroundMode(arg: Int): Unit = attr("boxBackgroundMode", arg)
  fun boxStrokeColor(arg: Int): Unit = attr("boxStrokeColor", arg)
  fun counterEnabled(arg: Boolean): Unit = attr("counterEnabled", arg)
  fun counterMaxLength(arg: Int): Unit = attr("counterMaxLength", arg)
  fun defaultHintTextColor(arg: ColorStateList?): Unit = attr("defaultHintTextColor", arg)
  fun error(arg: CharSequence?): Unit = attr("error", arg)
  fun errorEnabled(arg: Boolean): Unit = attr("errorEnabled", arg)
  fun errorTextAppearance(arg: Int): Unit = attr("errorTextAppearance", arg)
  fun errorTextColor(arg: ColorStateList?): Unit = attr("errorTextColor", arg)
  fun helperText(arg: CharSequence?): Unit = attr("helperText", arg)
  fun helperTextColor(arg: ColorStateList?): Unit = attr("helperTextColor", arg)
  fun helperTextEnabled(arg: Boolean): Unit = attr("helperTextEnabled", arg)
  fun helperTextTextAppearance(arg: Int): Unit = attr("helperTextTextAppearance", arg)
  fun hint(arg: CharSequence?): Unit = attr("hint", arg)
  fun hintAnimationEnabled(arg: Boolean): Unit = attr("hintAnimationEnabled", arg)
  fun hintEnabled(arg: Boolean): Unit = attr("hintEnabled", arg)
  fun hintTextAppearance(arg: Int): Unit = attr("hintTextAppearance", arg)
  fun passwordVisibilityToggleContentDescription(arg: CharSequence?): Unit =
      attr("passwordVisibilityToggleContentDescription", arg)
  fun passwordVisibilityToggleContentDescription(arg: Int): Unit =
      attr("passwordVisibilityToggleContentDescription", arg)
  fun passwordVisibilityToggleDrawable(arg: Drawable?): Unit =
      attr("passwordVisibilityToggleDrawable", arg)
  fun passwordVisibilityToggleDrawable(arg: Int): Unit = attr("passwordVisibilityToggleDrawable",
      arg)
  fun passwordVisibilityToggleEnabled(arg: Boolean): Unit = attr("passwordVisibilityToggleEnabled",
      arg)
  fun passwordVisibilityToggleTintList(arg: ColorStateList?): Unit =
      attr("passwordVisibilityToggleTintList", arg)
  fun passwordVisibilityToggleTintMode(arg: PorterDuff.Mode?): Unit =
      attr("passwordVisibilityToggleTintMode", arg)
  fun textInputAccessibilityDelegate(arg: TextInputLayout.AccessibilityDelegate): Unit =
      attr("textInputAccessibilityDelegate", arg)
  fun typeface(arg: Typeface?): Unit = attr("typeface", arg)
  companion object : TextInputLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
