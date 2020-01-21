@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android.widget

import android.widget.SeekBar
import dev.inkremental.dsl.android.CustomSdkSetter
import dev.inkremental.dsl.android.SdkSetter
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun seekBar(configure: SeekBarScope.() -> Unit = {}) = v<SeekBar>(configure.bind(SeekBarScope))
abstract class SeekBarScope : AbsSeekBarScope() {
  fun onSeekBarChange(arg: SeekBar.OnSeekBarChangeListener?): Unit = attr("onSeekBarChange", arg)
  companion object : SeekBarScope() {
    init {
      Anvil.registerAttributeSetter(SdkSetter)
      Anvil.registerAttributeSetter(CustomSdkSetter)
    }
  }
}
