@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout.widget

import androidx.constraintlayout.widget.Barrier
import trikita.anvil.*

fun barrier(configure: BarrierScope.() -> Unit = {}) =
    v<Barrier>(configure.bind(BarrierScope))
abstract class BarrierScope : ConstraintHelperScope() {
    fun type(arg: Int): Unit = attr("type", arg)
    fun allowsGoneWidget(arg: Boolean): Unit = attr("allowsGoneWidget", arg)
    companion object : BarrierScope()
}
