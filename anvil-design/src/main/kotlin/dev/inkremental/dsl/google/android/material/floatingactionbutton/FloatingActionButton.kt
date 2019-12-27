@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.floatingactionbutton

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import com.google.android.material.animation.MotionSpec
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.internal.VisibilityAwareImageButtonScope
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun floatingActionButton(configure: FloatingActionButtonScope.() -> Unit = {}) =
    v<FloatingActionButton>(configure.bind(FloatingActionButtonScope))
abstract class FloatingActionButtonScope : VisibilityAwareImageButtonScope() {
  fun compatElevation(arg: Float): Unit = attr("compatElevation", arg)
  fun compatElevationResource(arg: Int): Unit = attr("compatElevationResource", arg)
  fun compatHoveredFocusedTranslationZ(arg: Float): Unit = attr("compatHoveredFocusedTranslationZ",
      arg)
  fun compatHoveredFocusedTranslationZResource(arg: Int): Unit =
      attr("compatHoveredFocusedTranslationZResource", arg)
  fun compatPressedTranslationZ(arg: Float): Unit = attr("compatPressedTranslationZ", arg)
  fun compatPressedTranslationZResource(arg: Int): Unit = attr("compatPressedTranslationZResource",
      arg)
  fun customSize(arg: Int): Unit = attr("customSize", arg)
  fun expanded(arg: Boolean): Unit = attr("expanded", arg)
  fun expandedComponentIdHint(arg: Int): Unit = attr("expandedComponentIdHint", arg)
  fun hideMotionSpec(arg: MotionSpec): Unit = attr("hideMotionSpec", arg)
  fun hideMotionSpecResource(arg: Int): Unit = attr("hideMotionSpecResource", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColor(arg: Int): Unit = attr("rippleColor", arg)
  fun showMotionSpec(arg: MotionSpec): Unit = attr("showMotionSpec", arg)
  fun showMotionSpecResource(arg: Int): Unit = attr("showMotionSpecResource", arg)
  fun size(arg: Int): Unit = attr("size", arg)
  fun supportBackgroundTintList(arg: ColorStateList?): Unit = attr("supportBackgroundTintList", arg)
  fun supportBackgroundTintMode(arg: PorterDuff.Mode?): Unit = attr("supportBackgroundTintMode",
      arg)
  fun supportImageTintList(arg: ColorStateList?): Unit = attr("supportImageTintList", arg)
  fun supportImageTintMode(arg: PorterDuff.Mode?): Unit = attr("supportImageTintMode", arg)
  fun useCompatPadding(arg: Boolean): Unit = attr("useCompatPadding", arg)
  companion object : FloatingActionButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
