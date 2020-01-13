@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.widget.Switch
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun switchView(configure: SwitchViewScope.() -> Unit = {}) =
    v<Switch>(configure.bind(SwitchViewScope))
abstract class SwitchViewScope : CompoundButtonScope() {
  fun showText(arg: Boolean): Unit = attr("showText", arg)
  fun splitTrack(arg: Boolean): Unit = attr("splitTrack", arg)
  fun switchMinWidth(arg: Int): Unit = attr("switchMinWidth", arg)
  fun switchPadding(arg: Int): Unit = attr("switchPadding", arg)
  fun switchTypeface(arg: Typeface): Unit = attr("switchTypeface", arg)
  fun textOff(arg: CharSequence): Unit = attr("textOff", arg)
  fun textOn(arg: CharSequence): Unit = attr("textOn", arg)
  fun thumbDrawable(arg: Drawable): Unit = attr("thumbDrawable", arg)
  fun thumbResource(arg: Int): Unit = attr("thumbResource", arg)
  fun thumbTextPadding(arg: Int): Unit = attr("thumbTextPadding", arg)
  fun trackDrawable(arg: Drawable): Unit = attr("trackDrawable", arg)
  fun trackResource(arg: Int): Unit = attr("trackResource", arg)
  companion object : SwitchViewScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
