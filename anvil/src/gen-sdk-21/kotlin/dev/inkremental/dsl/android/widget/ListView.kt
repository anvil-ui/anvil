@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.widget.ListView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun listView(configure: ListViewScope.() -> Unit = {}) = v<ListView>(configure.bind(ListViewScope))
abstract class ListViewScope : AbsListViewScope() {
  fun divider(arg: Drawable?): Unit = attr("divider", arg)
  fun dividerHeight(arg: Int): Unit = attr("dividerHeight", arg)
  fun footerDividersEnabled(arg: Boolean): Unit = attr("footerDividersEnabled", arg)
  fun headerDividersEnabled(arg: Boolean): Unit = attr("headerDividersEnabled", arg)
  fun itemsCanFocus(arg: Boolean): Unit = attr("itemsCanFocus", arg)
  fun overscrollFooter(arg: Drawable): Unit = attr("overscrollFooter", arg)
  fun overscrollHeader(arg: Drawable): Unit = attr("overscrollHeader", arg)
  companion object : ListViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
