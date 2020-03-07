@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.CharSequence
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit

fun autoCompleteTextView(configure: AutoCompleteTextViewScope.() -> Unit = {}) =
    v<AutoCompleteTextView>(configure.bind(AutoCompleteTextViewScope))
abstract class AutoCompleteTextViewScope : EditTextScope() {
  fun completionHint(arg: CharSequence): Unit = attr("completionHint", arg)
  fun dropDownAnchor(arg: Int): Unit = attr("dropDownAnchor", arg)
  fun dropDownBackgroundDrawable(arg: Drawable): Unit = attr("dropDownBackgroundDrawable", arg)
  fun dropDownBackgroundResource(arg: Int): Unit = attr("dropDownBackgroundResource", arg)
  fun dropDownHeight(arg: Int): Unit = attr("dropDownHeight", arg)
  fun dropDownHorizontalOffset(arg: Int): Unit = attr("dropDownHorizontalOffset", arg)
  fun dropDownVerticalOffset(arg: Int): Unit = attr("dropDownVerticalOffset", arg)
  fun dropDownWidth(arg: Int): Unit = attr("dropDownWidth", arg)
  fun listSelection(arg: Int): Unit = attr("listSelection", arg)
  fun onDismiss(arg: (() -> Unit)?): Unit = attr("onDismiss", arg)
  fun onItemClick(arg: ((
    arg0: AdapterView<*>,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Unit)?): Unit = attr("onItemClick", arg)
  fun onItemSelected(arg: AdapterView.OnItemSelectedListener?): Unit = attr("onItemSelected", arg)
  fun threshold(arg: Int): Unit = attr("threshold", arg)
  fun validator(arg: AutoCompleteTextView.Validator): Unit = attr("validator", arg)
  companion object : AutoCompleteTextViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
