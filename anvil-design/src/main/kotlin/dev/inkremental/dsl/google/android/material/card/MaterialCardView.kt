@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.card

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.cardview.widget.CardViewScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun materialCardView(configure: MaterialCardViewScope.() -> Unit = {}) =
    v<MaterialCardView>(configure.bind(MaterialCardViewScope))
abstract class MaterialCardViewScope : CardViewScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checked(arg: Boolean): Unit = attr("checked", arg)
  fun checkedIcon(arg: Drawable?): Unit = attr("checkedIcon", arg)
  fun checkedIconResource(arg: Int): Unit = attr("checkedIconResource", arg)
  fun checkedIconTint(arg: ColorStateList?): Unit = attr("checkedIconTint", arg)
  fun dragged(arg: Boolean): Unit = attr("dragged", arg)
  fun onCheckedChange(arg: ((arg0: MaterialCardView, arg1: Boolean) -> Unit)?): Unit =
      attr("onCheckedChange", arg)
  fun progress(arg: Float): Unit = attr("progress", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColorResource(arg: Int): Unit = attr("rippleColorResource", arg)
  fun shapeAppearanceModel(arg: ShapeAppearanceModel): Unit = attr("shapeAppearanceModel", arg)
  fun strokeColor(arg: ColorStateList): Unit = attr("strokeColor", arg)
  fun strokeColor(arg: Int): Unit = attr("strokeColor", arg)
  fun strokeWidth(arg: Int): Unit = attr("strokeWidth", arg)
  companion object : MaterialCardViewScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
