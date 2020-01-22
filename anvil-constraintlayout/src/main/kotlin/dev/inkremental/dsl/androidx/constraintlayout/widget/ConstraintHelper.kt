@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout.widget

import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.attr

abstract class ConstraintHelperScope : ViewScope() {
    fun referencedIds(vararg arg: Int): Unit = attr("referencedIds", arg)
}
