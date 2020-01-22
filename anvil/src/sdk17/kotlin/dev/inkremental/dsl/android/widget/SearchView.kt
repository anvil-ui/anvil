@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.app.SearchableInfo
import android.view.View
import android.widget.CursorAdapter
import android.widget.SearchView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun searchView(configure: SearchViewScope.() -> Unit = {}) =
    v<SearchView>(configure.bind(SearchViewScope))
abstract class SearchViewScope : LinearLayoutScope() {
  fun iconified(arg: Boolean): Unit = attr("iconified", arg)
  fun iconifiedByDefault(arg: Boolean): Unit = attr("iconifiedByDefault", arg)
  fun imeOptions(arg: Int): Unit = attr("imeOptions", arg)
  fun inputType(arg: Int): Unit = attr("inputType", arg)
  fun maxWidth(arg: Int): Unit = attr("maxWidth", arg)
  fun onClose(arg: (() -> Boolean)?): Unit = attr("onClose", arg)
  fun onQueryText(arg: SearchView.OnQueryTextListener?): Unit = attr("onQueryText", arg)
  fun onQueryTextFocusChange(arg: ((arg0: View, arg1: Boolean) -> Unit)?): Unit =
      attr("onQueryTextFocusChange", arg)
  fun onSearchClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onSearchClick", arg)
  fun onSuggestion(arg: SearchView.OnSuggestionListener?): Unit = attr("onSuggestion", arg)
  fun queryHint(arg: CharSequence?): Unit = attr("queryHint", arg)
  fun queryRefinementEnabled(arg: Boolean): Unit = attr("queryRefinementEnabled", arg)
  fun searchableInfo(arg: SearchableInfo): Unit = attr("searchableInfo", arg)
  fun submitButtonEnabled(arg: Boolean): Unit = attr("submitButtonEnabled", arg)
  fun suggestionsAdapter(arg: CursorAdapter): Unit = attr("suggestionsAdapter", arg)
  companion object : SearchViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
