@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.RelativeLayout
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import dev.inkremental.dsl.android.view.ViewGroupScope
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun relativeLayout(configure: RelativeLayoutScope.() -> Unit = {}) =
    v<RelativeLayout>(configure.bind(RelativeLayoutScope))
abstract class RelativeLayoutScope : ViewGroupScope() {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun horizontalGravity(arg: Int): Unit = attr("horizontalGravity", arg)
  fun ignoreGravity(arg: Int): Unit = attr("ignoreGravity", arg)
  fun verticalGravity(arg: Int): Unit = attr("verticalGravity", arg)
  companion object : RelativeLayoutScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
