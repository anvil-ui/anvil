@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.appbar

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.google.android.material.appbar.CollapsingToolbarLayout
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.Long
import kotlin.Suppress
import kotlin.Unit
import trikita.anvil.Anvil
import trikita.anvil.attr
import trikita.anvil.bind
import trikita.anvil.v

fun collapsingToolbarLayout(configure: CollapsingToolbarLayoutScope.() -> Unit = {}) =
    v<CollapsingToolbarLayout>(configure.bind(CollapsingToolbarLayoutScope))
abstract class CollapsingToolbarLayoutScope : FrameLayoutScope() {
  fun collapsedTitleGravity(arg: Int): Unit = attr("collapsedTitleGravity", arg)
  fun collapsedTitleTextAppearance(arg: Int): Unit = attr("collapsedTitleTextAppearance", arg)
  fun collapsedTitleTextColor(arg: ColorStateList): Unit = attr("collapsedTitleTextColor", arg)
  fun collapsedTitleTextColor(arg: Int): Unit = attr("collapsedTitleTextColor", arg)
  fun collapsedTitleTypeface(arg: Typeface?): Unit = attr("collapsedTitleTypeface", arg)
  fun contentScrim(arg: Drawable?): Unit = attr("contentScrim", arg)
  fun contentScrimColor(arg: Int): Unit = attr("contentScrimColor", arg)
  fun contentScrimResource(arg: Int): Unit = attr("contentScrimResource", arg)
  fun expandedTitleColor(arg: Int): Unit = attr("expandedTitleColor", arg)
  fun expandedTitleGravity(arg: Int): Unit = attr("expandedTitleGravity", arg)
  fun expandedTitleMarginBottom(arg: Int): Unit = attr("expandedTitleMarginBottom", arg)
  fun expandedTitleMarginEnd(arg: Int): Unit = attr("expandedTitleMarginEnd", arg)
  fun expandedTitleMarginStart(arg: Int): Unit = attr("expandedTitleMarginStart", arg)
  fun expandedTitleMarginTop(arg: Int): Unit = attr("expandedTitleMarginTop", arg)
  fun expandedTitleTextAppearance(arg: Int): Unit = attr("expandedTitleTextAppearance", arg)
  fun expandedTitleTextColor(arg: ColorStateList): Unit = attr("expandedTitleTextColor", arg)
  fun expandedTitleTypeface(arg: Typeface?): Unit = attr("expandedTitleTypeface", arg)
  fun scrimAnimationDuration(arg: Long): Unit = attr("scrimAnimationDuration", arg)
  fun scrimVisibleHeightTrigger(arg: Int): Unit = attr("scrimVisibleHeightTrigger", arg)
  fun scrimsShown(arg: Boolean): Unit = attr("scrimsShown", arg)
  fun statusBarScrim(arg: Drawable?): Unit = attr("statusBarScrim", arg)
  fun statusBarScrimColor(arg: Int): Unit = attr("statusBarScrimColor", arg)
  fun statusBarScrimResource(arg: Int): Unit = attr("statusBarScrimResource", arg)
  fun title(arg: CharSequence?): Unit = attr("title", arg)
  fun titleEnabled(arg: Boolean): Unit = attr("titleEnabled", arg)
  companion object : CollapsingToolbarLayoutScope() {
    init {
      Anvil.registerAttributeSetter(MaterialSetter)
      Anvil.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
