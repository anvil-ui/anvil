package dev.inkremental.dsl.androidx.cardview

import android.view.View
import androidx.cardview.widget.CardView
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dip
import dev.inkremental.dsl.android.Dip
import dev.inkremental.dsl.androidx.cardview.widget.CardViewScope

fun CardViewScope.cardElevation(arg: Dip): Unit = attr("cardElevation", arg.value)
fun CardViewScope.maxCardElevation(arg: Dip): Unit = attr("maxCardElevation", arg.value)
fun CardViewScope.radius(arg: Dip): Unit = attr("radius", arg.value)

object CustomCardViewv7Setter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "cardElevation" -> when {
            v is CardView && value is Int -> {
                v.cardElevation = dip(value).toFloat()
                true
            }
            else -> false
        }
        "maxCardElevation" -> when {
            v is CardView && value is Int -> {
                v.maxCardElevation = dip(value).toFloat()
                true
            }
            else -> false
        }
        "radius" -> when {
            v is CardView && value is Int -> {
                v.radius = dip(value).toFloat()
                true
            }
            else -> false
        }
        else -> false
    }
}