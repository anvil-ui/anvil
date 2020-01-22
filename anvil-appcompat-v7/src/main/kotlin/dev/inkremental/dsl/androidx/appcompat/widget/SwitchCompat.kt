@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.SwitchCompat
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.CompoundButtonScope
import dev.inkremental.dsl.androidx.appcompat.AppCompatv7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun switchCompat(configure: SwitchCompatScope.() -> Unit = {}) =
    v<SwitchCompat>(configure.bind(SwitchCompatScope))
abstract class SwitchCompatScope : CompoundButtonScope() {
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
  fun thumbTintList(arg: ColorStateList?): Unit = attr("thumbTintList", arg)
  fun thumbTintMode(arg: PorterDuff.Mode?): Unit = attr("thumbTintMode", arg)
  fun trackDrawable(arg: Drawable): Unit = attr("trackDrawable", arg)
  fun trackResource(arg: Int): Unit = attr("trackResource", arg)
  fun trackTintList(arg: ColorStateList?): Unit = attr("trackTintList", arg)
  fun trackTintMode(arg: PorterDuff.Mode?): Unit = attr("trackTintMode", arg)
  companion object : SwitchCompatScope() {
    init {
      Inkremental.registerAttributeSetter(AppCompatv7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
