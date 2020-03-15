@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.appcompat

import android.os.Build
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat
import dev.inkremental.dsl.androidx.appcompat.widget.ToolbarScope
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dip
import dev.inkremental.dsl.android.Dip
import dev.inkremental.dsl.android.Sp
import dev.inkremental.dsl.androidx.appcompat.widget.AppCompatTextViewScope

fun ToolbarScope.layoutGravity(gravity: Int) = attr("layoutGravity", gravity)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun ToolbarScope.contentInsets(left: Dip, right : Dip) = attr("contentInsets", intArrayOf(right.value, left.value))

fun AppCompatTextViewScope.autoSizeTextWithDefaults(autoSizeTextType: Int) = attr("autoSizeTextWithDefaults", autoSizeTextType)
fun AppCompatTextViewScope.autoSizeTextWithConfiguration(autoSizeMinTextSize: Sp, autoSizeMaxTextSize : Sp, autoSizeStepGranularity : Sp) =
        attr("autoSizeTextWithConfigurationSp", floatArrayOf(autoSizeMinTextSize.value, autoSizeMaxTextSize.value, autoSizeStepGranularity.value))
fun AppCompatTextViewScope.autoSizeTextWithConfiguration(autoSizeMinTextSize: Dip, autoSizeMaxTextSize : Dip, autoSizeStepGranularity : Dip) =
        attr("autoSizeTextWithConfigurationDp", intArrayOf(autoSizeMinTextSize.value, autoSizeMaxTextSize.value, autoSizeStepGranularity.value))
fun AppCompatTextViewScope.autoSizeTextUniformWithPresetSizesDp(present : IntArray) =
        attr("autoSizeTextTypeUniformWithPresetSizesDp", present)
fun AppCompatTextViewScope.autoSizeTextUniformWithPresetSizesSp(present : IntArray) =
        attr("autoSizeTextTypeUniformWithPresetSizesSp", present)

object CustomAppCompatv7Setter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "layoutGravity" -> when {
            v.layoutParams is Toolbar.LayoutParams && value is Int -> {
                (v.layoutParams as Toolbar.LayoutParams).gravity = value
                true
            }
            else -> false
        }
        "autoSizeTextWithDefaults" -> when {
            v is TextView && value is Int -> {
                TextViewCompat.setAutoSizeTextTypeWithDefaults(v, value)
                true
            }
            else -> false
        }
        "autoSizeTextWithConfigurationSp" -> when {
            v is TextView && value is FloatArray -> {
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(v, value[0].toInt(), value[1].toInt(), value[2].toInt(), COMPLEX_UNIT_SP)
                true
            }
            else -> false
        }
        "autoSizeTextWithConfigurationDp" -> when {
            v is TextView && value is IntArray -> {
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(v, value[0], value[1], value[2], COMPLEX_UNIT_DIP)
                true
            }
            else -> false
        }
        "autoSizeTextTypeUniformWithPresetSizesDp" -> when {
            v is TextView && value is IntArray -> {
                TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(v, value, COMPLEX_UNIT_DIP)
                true
            }
            else -> false
        }
        "autoSizeTextTypeUniformWithPresetSizesSp" -> when {
            v is TextView && value is IntArray -> {
                TextViewCompat.setAutoSizeTextTypeUniformWithPresetSizes(v, value, COMPLEX_UNIT_SP)
                true
            }
            else -> false
        }
        "contentInsets" -> when {
            v is Toolbar && value is IntArray -> {
                v.setContentInsetsAbsolute(dip(value[0]), dip(value[1]))
                true
            }
            else -> false
        }
        else -> false
    }
}
