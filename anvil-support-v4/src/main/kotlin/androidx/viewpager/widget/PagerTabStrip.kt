@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.viewpager.widget

import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun pagerTabStrip(configure: PagerTabStripScope.() -> Unit = {}) =
    v<PagerTabStrip>(configure.bind(PagerTabStripScope))
abstract class PagerTabStripScope : PagerTitleStripScope() {
  fun drawFullUnderline(arg: Boolean): Unit = attr("drawFullUnderline", arg)
  fun tabIndicatorColor(arg: Int): Unit = attr("tabIndicatorColor", arg)
  fun tabIndicatorColorResource(arg: Int): Unit = attr("tabIndicatorColorResource", arg)
  companion object : PagerTabStripScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
