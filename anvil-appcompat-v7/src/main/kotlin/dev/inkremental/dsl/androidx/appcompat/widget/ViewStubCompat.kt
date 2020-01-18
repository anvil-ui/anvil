@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.ViewStubCompat
import dev.inkremental.Anvil
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun viewStubCompat(configure: ViewStubCompatScope.() -> Unit = {}) =
    v<ViewStubCompat>(configure.bind(ViewStubCompatScope))
abstract class ViewStubCompatScope : ViewScope() {
  fun inflatedId(arg: Int): Unit = attr("inflatedId", arg)
  fun layoutInflater(arg: LayoutInflater): Unit = attr("layoutInflater", arg)
  fun layoutResource(arg: Int): Unit = attr("layoutResource", arg)
  fun onInflate(arg: ((arg0: ViewStubCompat, arg1: View) -> Unit)?): Unit = attr("onInflate", arg)
  companion object : ViewStubCompatScope() {
    init {
      Anvil.registerAttributeSetter(AppCompatv7Setter)
      Anvil.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
