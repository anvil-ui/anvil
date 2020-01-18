@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout.widget

import androidx.constraintlayout.widget.Guideline
import dev.inkremental.attr
import dev.inkremental.bind
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.v

fun guideline(configure: GuidelineScope.() -> Unit = {}) =
    v<Guideline>(configure.bind(GuidelineScope))
abstract class GuidelineScope : ViewGroupScope() {
    fun orientation(orientation: Int): Unit = attr("orientation", orientation)
    fun guideBegin(margin: Int): Unit = attr("guideBegin", margin)
    fun guideEnd(margin: Int): Unit = attr("guideEnd", margin)
    fun guidelinePercent(ratio: Float): Unit = attr("guidelinePercent", ratio)
    companion object : GuidelineScope()
}
