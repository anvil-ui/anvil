@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.Editable
import android.text.InputFilter
import android.text.Spannable
import android.text.TextUtils
import android.text.method.KeyListener
import android.text.method.MovementMethod
import android.text.method.TransformationMethod
import android.view.ActionMode
import android.view.KeyEvent
import android.view.inputmethod.ExtractedText
import android.widget.Scroller
import android.widget.TextView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.v
import kotlin.Array
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit

fun textView(configure: TextViewScope.() -> Unit = {}) = v<TextView>(configure.bind(TextViewScope))
abstract class TextViewScope : ViewScope() {
  fun allCaps(arg: Boolean): Unit = attr("allCaps", arg)
  fun autoLinkMask(arg: Int): Unit = attr("autoLinkMask", arg)
  fun compoundDrawablePadding(arg: Int): Unit = attr("compoundDrawablePadding", arg)
  fun cursorVisible(arg: Boolean): Unit = attr("cursorVisible", arg)
  fun customSelectionActionModeCallback(arg: ActionMode.Callback): Unit =
      attr("customSelectionActionModeCallback", arg)
  fun editableFactory(arg: Editable.Factory): Unit = attr("editableFactory", arg)
  fun ellipsize(arg: TextUtils.TruncateAt): Unit = attr("ellipsize", arg)
  fun ems(arg: Int): Unit = attr("ems", arg)
  fun error(arg: CharSequence): Unit = attr("error", arg)
  fun extractedText(arg: ExtractedText): Unit = attr("extractedText", arg)
  fun filters(arg: Array<InputFilter>): Unit = attr("filters", arg)
  fun freezesText(arg: Boolean): Unit = attr("freezesText", arg)
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun height(arg: Int): Unit = attr("height", arg)
  fun highlightColor(arg: Int): Unit = attr("highlightColor", arg)
  fun hint(arg: CharSequence): Unit = attr("hint", arg)
  fun hint(arg: Int): Unit = attr("hint", arg)
  fun hintTextColor(arg: ColorStateList): Unit = attr("hintTextColor", arg)
  fun hintTextColor(arg: Int): Unit = attr("hintTextColor", arg)
  fun horizontallyScrolling(arg: Boolean): Unit = attr("horizontallyScrolling", arg)
  fun imeOptions(arg: Int): Unit = attr("imeOptions", arg)
  fun includeFontPadding(arg: Boolean): Unit = attr("includeFontPadding", arg)
  fun inputType(arg: Int): Unit = attr("inputType", arg)
  fun keyListener(arg: KeyListener): Unit = attr("keyListener", arg)
  fun lines(arg: Int): Unit = attr("lines", arg)
  fun linkTextColor(arg: ColorStateList): Unit = attr("linkTextColor", arg)
  fun linkTextColor(arg: Int): Unit = attr("linkTextColor", arg)
  fun linksClickable(arg: Boolean): Unit = attr("linksClickable", arg)
  fun marqueeRepeatLimit(arg: Int): Unit = attr("marqueeRepeatLimit", arg)
  fun maxEms(arg: Int): Unit = attr("maxEms", arg)
  fun maxHeight(arg: Int): Unit = attr("maxHeight", arg)
  fun maxLines(arg: Int): Unit = attr("maxLines", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun minEms(arg: Int): Unit = attr("minEms", arg)
  fun minHeight(arg: Int): Unit = attr("minHeight", arg)
  fun minLines(arg: Int): Unit = attr("minLines", arg)
  fun minWidth(arg: Int): Unit = attr("minWidth", arg)
  fun movementMethod(arg: MovementMethod): Unit = attr("movementMethod", arg)
  fun onEditorAction(arg: ((
    arg0: TextView,
    arg1: Int,
    arg2: KeyEvent
  ) -> Boolean)?): Unit = attr("onEditorAction", arg)
  fun paintFlags(arg: Int): Unit = attr("paintFlags", arg)
  fun privateImeOptions(arg: String): Unit = attr("privateImeOptions", arg)
  fun rawInputType(arg: Int): Unit = attr("rawInputType", arg)
  fun scroller(arg: Scroller): Unit = attr("scroller", arg)
  fun selectAllOnFocus(arg: Boolean): Unit = attr("selectAllOnFocus", arg)
  fun singleLine(arg: Boolean): Unit = attr("singleLine", arg)
  fun spannableFactory(arg: Spannable.Factory): Unit = attr("spannableFactory", arg)
  fun text(arg: Int): Unit = attr("text", arg)
  fun textColor(arg: ColorStateList): Unit = attr("textColor", arg)
  fun textColor(arg: Int): Unit = attr("textColor", arg)
  fun textIsSelectable(arg: Boolean): Unit = attr("textIsSelectable", arg)
  fun textKeepState(arg: CharSequence): Unit = attr("textKeepState", arg)
  fun textScaleX(arg: Float): Unit = attr("textScaleX", arg)
  fun transformationMethod(arg: TransformationMethod): Unit = attr("transformationMethod", arg)
  fun typeface(arg: Typeface?): Unit = attr("typeface", arg)
  fun width(arg: Int): Unit = attr("width", arg)
  companion object : TextViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
