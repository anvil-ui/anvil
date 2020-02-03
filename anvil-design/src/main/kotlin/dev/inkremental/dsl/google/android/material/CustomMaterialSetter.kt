@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.google.android.material

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.google.android.material.appbar.AppBarLayoutScope
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dip
import dev.inkremental.dsl.android.Dip
import dev.inkremental.dsl.google.android.material.floatingactionbutton.FloatingActionButtonScope

fun ViewScope.collapseMode(collapseMode: Int) = attr("collapseMode", collapseMode)
fun ViewScope.scrollFlags(scrollFlags: Int) = attr("scrollFlags", scrollFlags)
fun ViewScope.behavior(behavior: CoordinatorLayout.Behavior<*>) = attr("behavior", behavior)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun AppBarLayoutScope.appBarElevation(elevation: Dip) = attr("appBarElevation", elevation.value)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun FloatingActionButtonScope.compatElevation(arg: Dip): Unit = attr("compatElevation", arg.value)

object CustomMaterialSetter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "collapseMode" -> when {
            v.layoutParams is CollapsingToolbarLayout.LayoutParams && value is Int -> {
                (v.layoutParams as CollapsingToolbarLayout.LayoutParams).collapseMode = value
                true
            }
            else -> false
        }
        "scrollFlags" -> when {
            v.layoutParams is AppBarLayout.LayoutParams && value is Int -> {
                (v.layoutParams as AppBarLayout.LayoutParams).scrollFlags = value
                true
            }
            else -> false
        }
        "behavior" -> when {
            v.layoutParams is CoordinatorLayout.LayoutParams && value is CoordinatorLayout.Behavior<*>? -> {
                (v.layoutParams as CoordinatorLayout.LayoutParams).behavior = value
                true
            }
            else -> false
        }
        "appBarElevation" -> when {
            v is AppBarLayout && value is Int -> {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.stateListAnimator = StateListAnimator().apply {
                        addState(IntArray(0), ObjectAnimator.ofFloat(v, "elevation", dip(value).toFloat()))
                    }
                }
                true
            }
            else -> false
        }
        "compatElevation" -> when {
            v is FloatingActionButton && value is Int -> {
                v.compatElevation = dip(value).toFloat()
                true
            }
            else -> false
        }
        else -> false
    }
}
