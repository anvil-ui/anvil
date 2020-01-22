@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit

fun expandableListView(configure: ExpandableListViewScope.() -> Unit = {}) =
    v<ExpandableListView>(configure.bind(ExpandableListViewScope))
abstract class ExpandableListViewScope : ListViewScope() {
  fun adapter(arg: ExpandableListAdapter): Unit = attr("adapter", arg)
  fun childDivider(arg: Drawable): Unit = attr("childDivider", arg)
  fun childIndicator(arg: Drawable): Unit = attr("childIndicator", arg)
  fun groupIndicator(arg: Drawable): Unit = attr("groupIndicator", arg)
  fun onChildClick(arg: ((
    arg0: ExpandableListView,
    arg1: View,
    arg2: Int,
    arg3: Int,
    arg4: Long
  ) -> Boolean)?): Unit = attr("onChildClick", arg)
  fun onGroupClick(arg: ((
    arg0: ExpandableListView,
    arg1: View,
    arg2: Int,
    arg3: Long
  ) -> Boolean)?): Unit = attr("onGroupClick", arg)
  fun onGroupCollapse(arg: ((arg0: Int) -> Unit)?): Unit = attr("onGroupCollapse", arg)
  fun onGroupExpand(arg: ((arg0: Int) -> Unit)?): Unit = attr("onGroupExpand", arg)
  fun selectedGroup(arg: Int): Unit = attr("selectedGroup", arg)
  companion object : ExpandableListViewScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
