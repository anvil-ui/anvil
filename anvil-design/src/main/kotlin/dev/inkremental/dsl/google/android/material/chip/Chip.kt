@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.chip

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import com.google.android.material.animation.MotionSpec
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.resources.TextAppearance
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatCheckBoxScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Float
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun chip(configure: ChipScope.() -> Unit = {}) = v<Chip>(configure.bind(ChipScope))
abstract class ChipScope : AppCompatCheckBoxScope() {
  fun checkable(arg: Boolean): Unit = attr("checkable", arg)
  fun checkableResource(arg: Int): Unit = attr("checkableResource", arg)
  fun checkedIcon(arg: Drawable?): Unit = attr("checkedIcon", arg)
  fun checkedIconResource(arg: Int): Unit = attr("checkedIconResource", arg)
  fun checkedIconVisible(arg: Boolean): Unit = attr("checkedIconVisible", arg)
  fun checkedIconVisible(arg: Int): Unit = attr("checkedIconVisible", arg)
  fun chipBackgroundColor(arg: ColorStateList?): Unit = attr("chipBackgroundColor", arg)
  fun chipBackgroundColorResource(arg: Int): Unit = attr("chipBackgroundColorResource", arg)
  fun chipCornerRadius(arg: Float): Unit = attr("chipCornerRadius", arg)
  fun chipCornerRadiusResource(arg: Int): Unit = attr("chipCornerRadiusResource", arg)
  fun chipDrawable(arg: ChipDrawable): Unit = attr("chipDrawable", arg)
  fun chipEndPadding(arg: Float): Unit = attr("chipEndPadding", arg)
  fun chipEndPaddingResource(arg: Int): Unit = attr("chipEndPaddingResource", arg)
  fun chipIcon(arg: Drawable?): Unit = attr("chipIcon", arg)
  fun chipIconResource(arg: Int): Unit = attr("chipIconResource", arg)
  fun chipIconSize(arg: Float): Unit = attr("chipIconSize", arg)
  fun chipIconSizeResource(arg: Int): Unit = attr("chipIconSizeResource", arg)
  fun chipIconTint(arg: ColorStateList?): Unit = attr("chipIconTint", arg)
  fun chipIconTintResource(arg: Int): Unit = attr("chipIconTintResource", arg)
  fun chipIconVisible(arg: Boolean): Unit = attr("chipIconVisible", arg)
  fun chipIconVisible(arg: Int): Unit = attr("chipIconVisible", arg)
  fun chipMinHeight(arg: Float): Unit = attr("chipMinHeight", arg)
  fun chipMinHeightResource(arg: Int): Unit = attr("chipMinHeightResource", arg)
  fun chipStartPadding(arg: Float): Unit = attr("chipStartPadding", arg)
  fun chipStartPaddingResource(arg: Int): Unit = attr("chipStartPaddingResource", arg)
  fun chipStrokeColor(arg: ColorStateList?): Unit = attr("chipStrokeColor", arg)
  fun chipStrokeColorResource(arg: Int): Unit = attr("chipStrokeColorResource", arg)
  fun chipStrokeWidth(arg: Float): Unit = attr("chipStrokeWidth", arg)
  fun chipStrokeWidthResource(arg: Int): Unit = attr("chipStrokeWidthResource", arg)
  fun closeIcon(arg: Drawable?): Unit = attr("closeIcon", arg)
  fun closeIconContentDescription(arg: CharSequence?): Unit = attr("closeIconContentDescription",
      arg)
  fun closeIconEndPadding(arg: Float): Unit = attr("closeIconEndPadding", arg)
  fun closeIconEndPaddingResource(arg: Int): Unit = attr("closeIconEndPaddingResource", arg)
  fun closeIconResource(arg: Int): Unit = attr("closeIconResource", arg)
  fun closeIconSize(arg: Float): Unit = attr("closeIconSize", arg)
  fun closeIconSizeResource(arg: Int): Unit = attr("closeIconSizeResource", arg)
  fun closeIconStartPadding(arg: Float): Unit = attr("closeIconStartPadding", arg)
  fun closeIconStartPaddingResource(arg: Int): Unit = attr("closeIconStartPaddingResource", arg)
  fun closeIconTint(arg: ColorStateList?): Unit = attr("closeIconTint", arg)
  fun closeIconTintResource(arg: Int): Unit = attr("closeIconTintResource", arg)
  fun closeIconVisible(arg: Boolean): Unit = attr("closeIconVisible", arg)
  fun closeIconVisible(arg: Int): Unit = attr("closeIconVisible", arg)
  fun hideMotionSpec(arg: MotionSpec?): Unit = attr("hideMotionSpec", arg)
  fun hideMotionSpecResource(arg: Int): Unit = attr("hideMotionSpecResource", arg)
  fun iconEndPadding(arg: Float): Unit = attr("iconEndPadding", arg)
  fun iconEndPaddingResource(arg: Int): Unit = attr("iconEndPaddingResource", arg)
  fun iconStartPadding(arg: Float): Unit = attr("iconStartPadding", arg)
  fun iconStartPaddingResource(arg: Int): Unit = attr("iconStartPaddingResource", arg)
  fun onCloseIconClick(arg: ((arg0: View) -> Unit)?): Unit = attr("onCloseIconClick", arg)
  fun rippleColor(arg: ColorStateList?): Unit = attr("rippleColor", arg)
  fun rippleColorResource(arg: Int): Unit = attr("rippleColorResource", arg)
  fun showMotionSpec(arg: MotionSpec?): Unit = attr("showMotionSpec", arg)
  fun showMotionSpecResource(arg: Int): Unit = attr("showMotionSpecResource", arg)
  fun textAppearance(arg: TextAppearance?): Unit = attr("textAppearance", arg)
  fun textAppearanceResource(arg: Int): Unit = attr("textAppearanceResource", arg)
  fun textEndPadding(arg: Float): Unit = attr("textEndPadding", arg)
  fun textEndPaddingResource(arg: Int): Unit = attr("textEndPaddingResource", arg)
  fun textStartPadding(arg: Float): Unit = attr("textStartPadding", arg)
  fun textStartPaddingResource(arg: Int): Unit = attr("textStartPaddingResource", arg)
  companion object : ChipScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
