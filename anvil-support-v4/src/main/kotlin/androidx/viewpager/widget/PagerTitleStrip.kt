@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package androidx.viewpager.widget

import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.core.SupportCoreUiSetter
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun pagerTitleStrip(configure: PagerTitleStripScope.() -> Unit = {}) =
    v<PagerTitleStrip>(configure.bind(PagerTitleStripScope))
abstract class PagerTitleStripScope : ViewGroupScope() {
  fun gravity(arg: Int): Unit = attr("gravity", arg)
  fun nonPrimaryAlpha(arg: Float): Unit = attr("nonPrimaryAlpha", arg)
  fun textColor(arg: Int): Unit = attr("textColor", arg)
  fun textSpacing(arg: Int): Unit = attr("textSpacing", arg)
  companion object : PagerTitleStripScope() {
    init {
      Anvil.registerAttributeSetter(SupportCoreUiSetter)
    }
  }
}
