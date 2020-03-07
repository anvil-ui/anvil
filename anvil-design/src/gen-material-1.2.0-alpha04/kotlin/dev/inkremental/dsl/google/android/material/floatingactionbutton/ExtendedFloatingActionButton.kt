@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.floatingactionbutton

import com.google.android.material.animation.MotionSpec
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.dsl.google.android.material.button.MaterialButtonScope
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun extendedFloatingActionButton(configure: ExtendedFloatingActionButtonScope.() -> Unit = {}) =
    v<ExtendedFloatingActionButton>(configure.bind(ExtendedFloatingActionButtonScope))
abstract class ExtendedFloatingActionButtonScope : MaterialButtonScope() {
  fun extendMotionSpec(arg: MotionSpec?): Unit = attr("extendMotionSpec", arg)
  fun extendMotionSpecResource(arg: Int): Unit = attr("extendMotionSpecResource", arg)
  fun extended(arg: Boolean): Unit = attr("extended", arg)
  fun hideMotionSpec(arg: MotionSpec?): Unit = attr("hideMotionSpec", arg)
  fun hideMotionSpecResource(arg: Int): Unit = attr("hideMotionSpecResource", arg)
  fun showMotionSpec(arg: MotionSpec?): Unit = attr("showMotionSpec", arg)
  fun showMotionSpecResource(arg: Int): Unit = attr("showMotionSpecResource", arg)
  fun shrinkMotionSpec(arg: MotionSpec?): Unit = attr("shrinkMotionSpec", arg)
  fun shrinkMotionSpecResource(arg: Int): Unit = attr("shrinkMotionSpecResource", arg)
  companion object : ExtendedFloatingActionButtonScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
