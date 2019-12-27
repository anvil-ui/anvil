@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.app

import android.app.Activity
import android.app.FragmentBreadCrumbs
import android.app.FragmentManager
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun fragmentBreadCrumbs(configure: FragmentBreadCrumbsScope.() -> Unit = {}) =
    v<FragmentBreadCrumbs>(configure.bind(FragmentBreadCrumbsScope))
abstract class FragmentBreadCrumbsScope : ViewGroupScope() {
  fun activity(arg: Activity): Unit = attr("activity", arg)
  fun maxVisible(arg: Int): Unit = attr("maxVisible", arg)
  fun onBreadCrumbClick(arg: ((arg0: FragmentManager.BackStackEntry, arg1: Int) -> Boolean)?): Unit
      = attr("onBreadCrumbClick", arg)
  companion object : FragmentBreadCrumbsScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
