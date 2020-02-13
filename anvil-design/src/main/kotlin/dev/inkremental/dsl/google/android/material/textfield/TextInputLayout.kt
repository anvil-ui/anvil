@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.textfield

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import dev.inkremental.Inkremental
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
  fun counterOverflowTextAppearance(arg: Int): Unit = attr("counterOverflowTextAppearance", arg)
  fun counterOverflowTextColor(arg: ColorStateList?): Unit = attr("counterOverflowTextColor", arg)
  fun counterTextAppearance(arg: Int): Unit = attr("counterTextAppearance", arg)
  fun counterTextColor(arg: ColorStateList?): Unit = attr("counterTextColor", arg)
  fun defaultHintTextColor(arg: ColorStateList?): Unit = attr("defaultHintTextColor", arg)
  fun endIconActivated(arg: Boolean): Unit = attr("endIconActivated", arg)
  fun endIconCheckable(arg: Boolean): Unit = attr("endIconCheckable", arg)
  fun endIconContentDescription(arg: CharSequence?): Unit = attr("endIconContentDescription", arg)
  fun endIconContentDescription(arg: Int): Unit = attr("endIconContentDescription", arg)
  fun endIconDrawable(arg: Drawable?): Unit = attr("endIconDrawable", arg)
  fun endIconDrawable(arg: Int): Unit = attr("endIconDrawable", arg)
  fun endIconMode(arg: Int): Unit = attr("endIconMode", arg)
  fun endIconOnClickListener(arg: View.OnClickListener?): Unit = attr("endIconOnClickListener", arg)
  fun endIconOnLongClickListener(arg: View.OnLongClickListener?): Unit =
      attr("endIconOnLongClickListener", arg)
  fun endIconTintList(arg: ColorStateList?): Unit = attr("endIconTintList", arg)
  fun endIconTintMode(arg: PorterDuff.Mode?): Unit = attr("endIconTintMode", arg)
  fun endIconVisible(arg: Boolean): Unit = attr("endIconVisible", arg)
  fun error(arg: CharSequence?): Unit = attr("error", arg)
  fun errorEnabled(arg: Boolean): Unit = attr("errorEnabled", arg)
  fun errorIconDrawable(arg: Drawable?): Unit = attr("errorIconDrawable", arg)
  fun errorIconDrawable(arg: Int): Unit = attr("errorIconDrawable", arg)
  fun errorIconTintList(arg: ColorStateList): Unit = attr("errorIconTintList", arg)
  fun errorIconTintMode(arg: PorterDuff.Mode): Unit = attr("errorIconTintMode", arg)
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
  fun hintTextColor(arg: ColorStateList?): Unit = attr("hintTextColor", arg)
  fun startIconCheckable(arg: Boolean): Unit = attr("startIconCheckable", arg)
  fun startIconContentDescription(arg: CharSequence?): Unit = attr("startIconContentDescription",
      arg)
  fun startIconContentDescription(arg: Int): Unit = attr("startIconContentDescription", arg)
  fun startIconDrawable(arg: Drawable?): Unit = attr("startIconDrawable", arg)
  fun startIconDrawable(arg: Int): Unit = attr("startIconDrawable", arg)
  fun startIconOnClickListener(arg: View.OnClickListener?): Unit = attr("startIconOnClickListener",
      arg)
  fun startIconOnLongClickListener(arg: View.OnLongClickListener?): Unit =
      attr("startIconOnLongClickListener", arg)
  fun startIconTintList(arg: ColorStateList?): Unit = attr("startIconTintList", arg)
  fun startIconTintMode(arg: PorterDuff.Mode?): Unit = attr("startIconTintMode", arg)
  fun startIconVisible(arg: Boolean): Unit = attr("startIconVisible", arg)
  fun textInputAccessibilityDelegate(arg: TextInputLayout.AccessibilityDelegate): Unit =
      attr("textInputAccessibilityDelegate", arg)
  fun typeface(arg: Typeface?): Unit = attr("typeface", arg)
  companion object : TextInputLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
