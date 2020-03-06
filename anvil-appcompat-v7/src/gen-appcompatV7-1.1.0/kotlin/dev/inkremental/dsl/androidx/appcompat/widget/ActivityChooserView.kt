@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat.widget

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.ActivityChooserView
import androidx.core.view.ActionProvider
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.appcompat.AppcompatV7Setter
import dev.inkremental.dsl.androidx.appcompat.CustomAppCompatv7Setter
import dev.inkremental.v
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun activityChooserView(configure: ActivityChooserViewScope.() -> Unit = {}) =
    v<ActivityChooserView>(configure.bind(ActivityChooserViewScope))
abstract class ActivityChooserViewScope : ViewGroupScope() {
  fun defaultActionButtonContentDescription(arg: Int): Unit =
      attr("defaultActionButtonContentDescription", arg)
  fun expandActivityOverflowButtonContentDescription(arg: Int): Unit =
      attr("expandActivityOverflowButtonContentDescription", arg)
  fun expandActivityOverflowButtonDrawable(arg: Drawable): Unit =
      attr("expandActivityOverflowButtonDrawable", arg)
  fun initialActivityCount(arg: Int): Unit = attr("initialActivityCount", arg)
  fun onDismiss(arg: (() -> Unit)?): Unit = attr("onDismiss", arg)
  fun provider(arg: ActionProvider): Unit = attr("provider", arg)
  companion object : ActivityChooserViewScope() {
    init {
      Inkremental.registerAttributeSetter(AppcompatV7Setter)
      Inkremental.registerAttributeSetter(CustomAppCompatv7Setter)
    }
  }
}
