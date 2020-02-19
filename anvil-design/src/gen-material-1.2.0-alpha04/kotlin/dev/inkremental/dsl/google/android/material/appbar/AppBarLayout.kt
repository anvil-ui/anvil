@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material.appbar

import android.graphics.drawable.Drawable
import com.google.android.material.appbar.AppBarLayout
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.widget.LinearLayoutScope
import dev.inkremental.dsl.google.android.material.CustomMaterialSetter
import dev.inkremental.dsl.google.android.material.MaterialSetter
import dev.inkremental.v
import kotlin.Boolean
import kotlin.Int
import kotlin.Suppress
import kotlin.Unit

fun appBarLayout(configure: AppBarLayoutScope.() -> Unit = {}) =
    v<AppBarLayout>(configure.bind(AppBarLayoutScope))
abstract class AppBarLayoutScope : LinearLayoutScope() {
  fun expanded(arg: Boolean): Unit = attr("expanded", arg)
  fun liftOnScroll(arg: Boolean): Unit = attr("liftOnScroll", arg)
  fun liftOnScrollTargetViewId(arg: Int): Unit = attr("liftOnScrollTargetViewId", arg)
  fun liftable(arg: Boolean): Unit = attr("liftable", arg)
  fun lifted(arg: Boolean): Unit = attr("lifted", arg)
  fun statusBarForeground(arg: Drawable?): Unit = attr("statusBarForeground", arg)
  fun statusBarForegroundColor(arg: Int): Unit = attr("statusBarForegroundColor", arg)
  fun statusBarForegroundResource(arg: Int): Unit = attr("statusBarForegroundResource", arg)
  companion object : AppBarLayoutScope() {
    init {
      Inkremental.registerAttributeSetter(MaterialSetter)
      Inkremental.registerAttributeSetter(CustomMaterialSetter)
    }
  }
}
