package trikita.anvil

import android.view.View
import androidx.appcompat.widget.Toolbar

fun ToolbarScope.layoutGravity(gravity: Int) = attr("layoutGravity", gravity)

object AppcompatV7DslSetter : Anvil.AttributeSetter<Any?> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "layoutGravity" -> when {
            v.layoutParams is Toolbar.LayoutParams && value is Int -> {
                (v.layoutParams as Toolbar.LayoutParams).gravity = value
                true
            }
            else -> false
        }
        else -> false
    }
}
