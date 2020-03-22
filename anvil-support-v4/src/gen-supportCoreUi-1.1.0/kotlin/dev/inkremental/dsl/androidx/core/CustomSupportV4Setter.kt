package dev.inkremental.dsl.androidx.core

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.android.widget.ImageViewScope
import dev.inkremental.dsl.android.widget.TextViewScope
import dev.inkremental.r

abstract class CompatImageViewScope : ImageViewScope() {

    fun imageDrawableCompat(@DrawableRes drawbleRes: Int) = attr("imageDrawableCompat", drawbleRes)

    companion object : CompatImageViewScope() {
        init {
            Inkremental.registerAttributeSetter(CustomSupportV4Setter)
        }
    }
}

abstract class CompatTextViewScope : TextViewScope() {

    fun textColorCompat(@ColorRes colorRes: Int) = attr("textColorCompat", colorRes)

    companion object : CompatTextViewScope() {
        init {
            Inkremental.registerAttributeSetter(CustomSupportV4Setter)
        }
    }
}

abstract class CompatViewScope : ViewScope() {
    fun backgroundColorCompat(@ColorRes colorRes: Int) = attr("backgroundColorCompat", colorRes)
    fun backgroundDrawableCompat(@DrawableRes drawbleRes: Int) = attr("backgroundDrawableCompat", drawbleRes)
    companion object : CompatViewScope() {
        init {
            Inkremental.registerAttributeSetter(CustomSupportV4Setter)
        }
    }
}


object CustomSupportV4Setter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        "textColorCompat" -> when {
            v is TextView && value is Int -> {
                v.setTextColor(ResourcesCompat.getColor(r, value, null))
                true
            }
            else -> false
        }
        "backgroundColorCompat" -> when (value) {
            is Int -> {
                v.setBackgroundColor(ResourcesCompat.getColor(r, value, null))
                true
            }
            else -> false
        }
        "backgroundDrawableCompat" -> when (value) {
            is Int -> {
                v.background = ResourcesCompat.getDrawable(r, value, null)
                true
            }
            else -> false
        }
        "imageDrawableCompat" -> when {
            v is ImageView && value is Int -> {
                v.setImageDrawable(ResourcesCompat.getDrawable(r, value, null))
                true
            }
            else -> false
        }
        else -> false
    }
}