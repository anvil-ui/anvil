@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun viewStub(configure: ViewStubScope.() -> Unit = {}) = v<ViewStub>(configure.bind(ViewStubScope))
abstract class ViewStubScope : ViewScope() {
  fun inflatedId(arg: Int): Unit = attr("inflatedId", arg)
  fun layoutInflater(arg: LayoutInflater): Unit = attr("layoutInflater", arg)
  fun layoutResource(arg: Int): Unit = attr("layoutResource", arg)
  fun onInflate(arg: ((arg0: ViewStub, arg1: View) -> Unit)?): Unit = attr("onInflate", arg)
  companion object : ViewStubScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
