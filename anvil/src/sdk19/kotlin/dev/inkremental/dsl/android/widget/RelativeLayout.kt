@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.RelativeLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun relativeLayout(configure: RelativeLayoutScope.() -> Unit = {}) =
    v<RelativeLayout>(configure.bind(RelativeLayoutScope))
abstract class RelativeLayoutScope : ViewGroupScope() {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalGravity(arg: Int): Unit = attr("horizontalGravity", arg)
  fun ignoreGravity(arg: Int): Unit = attr("ignoreGravity", arg)
  fun verticalGravity(arg: Int): Unit = attr("verticalGravity", arg)
  companion object : RelativeLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(SdkSetter)
      Inkremental.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
