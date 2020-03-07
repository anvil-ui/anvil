@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.slider

import android.content.res.ColorStateList
import com.google.android.material.slider.Slider
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.Dip
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun slider(configure: SliderScope.() -> Unit = {}) = v<Slider>(configure.bind(SliderScope))
abstract class SliderScope : ViewScope() {
  fun haloColor(arg: ColorStateList): Unit = attr("haloColor", arg)
  fun haloRadius(arg: Int): Unit = attr("haloRadius", arg)
  fun haloRadiusResource(arg: Int): Unit = attr("haloRadiusResource", arg)
  fun labelBehavior(arg: Int): Unit = attr("labelBehavior", arg)
  fun labelFormatter(arg: Slider.LabelFormatter?): Unit = attr("labelFormatter", arg)
  fun stepSize(arg: Float): Unit = attr("stepSize", arg)
  fun thumbColor(arg: ColorStateList): Unit = attr("thumbColor", arg)
  fun thumbElevation(arg: Dip): Unit = attr("thumbElevation", arg.value)
  fun thumbElevationResource(arg: Int): Unit = attr("thumbElevationResource", arg)
  fun thumbRadius(arg: Int): Unit = attr("thumbRadius", arg)
  fun thumbRadiusResource(arg: Int): Unit = attr("thumbRadiusResource", arg)
  fun tickColor(arg: ColorStateList): Unit = attr("tickColor", arg)
  fun tickColorActive(arg: ColorStateList): Unit = attr("tickColorActive", arg)
  fun tickColorInactive(arg: ColorStateList): Unit = attr("tickColorInactive", arg)
  fun trackColor(arg: ColorStateList): Unit = attr("trackColor", arg)
  fun trackColorActive(arg: ColorStateList): Unit = attr("trackColorActive", arg)
  fun trackColorInactive(arg: ColorStateList): Unit = attr("trackColorInactive", arg)
  fun trackHeight(arg: Int): Unit = attr("trackHeight", arg)
  fun value(arg: Float): Unit = attr("value", arg)
  fun valueFrom(arg: Float): Unit = attr("valueFrom", arg)
  fun valueTo(arg: Float): Unit = attr("valueTo", arg)
  companion object : SliderScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
