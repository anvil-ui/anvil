@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.imageview

import android.content.res.ColorStateList
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatImageViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun shapeableImageView(configure: ShapeableImageViewScope.() -> Unit = {}) =
    v<ShapeableImageView>(configure.bind(ShapeableImageViewScope))
abstract class ShapeableImageViewScope : AppCompatImageViewScope() {
  fun shapeAppearanceModel(arg: ShapeAppearanceModel): Unit = attr("shapeAppearanceModel", arg)
  fun strokeColor(arg: ColorStateList?): Unit = attr("strokeColor", arg)
  fun strokeColorResource(arg: Int): Unit = attr("strokeColorResource", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  fun strokeWidthResource(arg: Int): Unit = attr("strokeWidthResource", arg)
  companion object : ShapeableImageViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
