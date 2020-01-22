@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout.widget

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dsl.android.view.ViewGroupScope
import dev.inkremental.dsl.androidx.constraintlayout.*
import dev.inkremental.v

/*
 * TODO many of these methods should be available for children of ConstraintLayout but not ConstraintLayout itself.
 *   Revisit this when multi-receivers are available.
*/

fun constraintLayout(configure: ConstraintLayoutScope.() -> Unit = {}) =
    v<ConstraintLayout, ConstraintLayoutScope>(ConstraintLayoutScope) {
        configure()
        applyConstraints()
    }
abstract class ConstraintLayoutScope : ViewGroupScope() {
    fun sideConstraint(toId: Int, fromSide: Int, toSide: Int): Unit =
        attr("sideConstraint", ConstraintSide(0, fromSide, toId, toSide))
    fun leftConstraintToLeft(toId: Int): Unit = sideConstraint(toId, ConstraintSet.LEFT, ConstraintSet.LEFT)
    fun leftConstraintToRight(toId: Int): Unit = sideConstraint(toId, ConstraintSet.LEFT, ConstraintSet.RIGHT)
    fun leftConstraintToTop(toId: Int): Unit = sideConstraint(toId, ConstraintSet.LEFT, ConstraintSet.TOP)
    fun leftConstraintToBottom(toId: Int): Unit = sideConstraint(toId, ConstraintSet.LEFT, ConstraintSet.BOTTOM)
    fun leftConstraintToParent(): Unit = leftConstraintToLeft(ConstraintSet.PARENT_ID)
    fun rightConstraintToLeft(toId: Int): Unit = sideConstraint(toId, ConstraintSet.RIGHT, ConstraintSet.LEFT)
    fun rightConstraintToRight(toId: Int): Unit = sideConstraint(toId, ConstraintSet.RIGHT, ConstraintSet.RIGHT)
    fun rightConstraintToTop(toId: Int): Unit = sideConstraint(toId, ConstraintSet.RIGHT, ConstraintSet.TOP)
    fun rightConstraintToBottom(toId: Int): Unit = sideConstraint(toId, ConstraintSet.RIGHT, ConstraintSet.BOTTOM)
    fun rightConstraintToParent(): Unit = rightConstraintToRight(ConstraintSet.PARENT_ID)
    fun topConstraintToLeft(toId: Int): Unit = sideConstraint(toId, ConstraintSet.TOP, ConstraintSet.LEFT)
    fun topConstraintToRight(toId: Int): Unit = sideConstraint(toId, ConstraintSet.TOP, ConstraintSet.RIGHT)
    fun topConstraintToTop(toId: Int): Unit = sideConstraint(toId, ConstraintSet.TOP, ConstraintSet.TOP)
    fun topConstraintToBottom(toId: Int): Unit = sideConstraint(toId, ConstraintSet.TOP, ConstraintSet.BOTTOM)
    fun topConstraintToParent(): Unit = topConstraintToTop(ConstraintSet.PARENT_ID)
    fun bottomConstraintToLeft(toId: Int): Unit = sideConstraint(toId, ConstraintSet.BOTTOM, ConstraintSet.LEFT)
    fun bottomConstraintToRight(toId: Int): Unit = sideConstraint(toId, ConstraintSet.BOTTOM, ConstraintSet.RIGHT)
    fun bottomConstraintToTop(toId: Int): Unit = sideConstraint(toId, ConstraintSet.BOTTOM, ConstraintSet.TOP)
    fun bottomConstraintToBottom(toId: Int): Unit = sideConstraint(toId, ConstraintSet.BOTTOM, ConstraintSet.BOTTOM)
    fun bottomConstraintToParent(): Unit = bottomConstraintToBottom(ConstraintSet.PARENT_ID)
    fun horizontalChain(
        leftId: Int, leftSide: Int, rightId: Int, rightSide: Int,
        chainIds: IntArray?, weights: FloatArray?, style: Int): Unit = attr(
        "chainConstraint",
        ConstraintChain(true, leftId, leftSide, rightId, rightSide, chainIds, weights, style)
    )
    fun verticalChain(
        leftId: Int, leftSide: Int, rightId: Int, rightSide: Int,
        chainIds: IntArray?, weights: FloatArray?, style: Int): Unit = attr(
        "chainConstraint",
        ConstraintChain(false, leftId, leftSide, rightId, rightSide, chainIds, weights, style)
    )
    fun circleConstraint(id: Int, angle: Int, radius: Int): Unit =
        attr("circleConstraint", ConstraintCircle(0, id, angle, radius))
    fun constraintId(arg: Int): Unit = attr("constraintId", arg)
    fun applyConstraints(): Unit = attr("applyConstraints", Unit)

    companion object : ConstraintLayoutScope() {
        init {
            Inkremental.registerAttributeSetter(CustomConstraintSetter)
        }
    }
}
