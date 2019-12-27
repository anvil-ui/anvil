@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.QuickContactBadge
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun quickContactBadge(configure: QuickContactBadgeScope.() -> Unit = {}) =
    v<QuickContactBadge>(configure.bind(QuickContactBadgeScope))
abstract class QuickContactBadgeScope : ImageViewScope() {
  fun excludeMimes(arg: Array<String>): Unit = attr("excludeMimes", arg)
  fun mode(arg: Int): Unit = attr("mode", arg)
  companion object : QuickContactBadgeScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
