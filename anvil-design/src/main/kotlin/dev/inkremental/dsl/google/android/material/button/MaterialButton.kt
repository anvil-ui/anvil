@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.button

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import com.google.android.material.button.MaterialButton
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatButtonScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun materialButton(configure: MaterialButtonScope.() -> Unit = {}) =
    v<MaterialButton>(configure.bind(MaterialButtonScope))
abstract class MaterialButtonScope : AppCompatButtonScope() {
  fun cornerRadius(arg: Int): Unit = attr("cornerRadius", arg)
  fun cornerRadiusResource(arg: Int): Unit = attr("cornerRadiusResource", arg)
  fun icon(arg: Drawable): Unit = attr("icon", arg)
  fun iconGravity(arg: Int): Unit = attr("iconGravity", arg)
  fun iconPadding(arg: Int): Unit = attr("iconPadding", arg)
  fun iconResource(arg: Int): Unit = attr("iconResource", arg)
  fun iconSize(arg: Int): Unit = attr("iconSize", arg)
  fun iconTint(arg: ColorStateList?): Unit = attr("iconTint", arg)
  fun iconTintMode(arg: PorterDuff.Mode): Unit = attr("iconTintMode", arg)
  fun iconTintResource(arg: Int): Unit = attr("iconTintResource", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColorResource(arg: Int): Unit = attr("rippleColorResource", arg)
  fun strokeColor(arg: ColorStateList?): Unit = attr("strokeColor", arg)
  fun strokeColorResource(arg: Int): Unit = attr("strokeColorResource", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  fun strokeWidthResource(arg: Int): Unit = attr("strokeWidthResource", arg)
  companion object : MaterialButtonScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
