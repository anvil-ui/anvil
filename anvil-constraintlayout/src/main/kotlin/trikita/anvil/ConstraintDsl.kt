@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package trikita.anvil

import android.view.View
import androidx.constraintlayout.widget.*
import java.util.*

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
        ConstraintChain(true, leftId, leftSide, rightId, rightSide, chainIds, weights, style))
    fun verticalChain(
        leftId: Int, leftSide: Int, rightId: Int, rightSide: Int,
        chainIds: IntArray?, weights: FloatArray?, style: Int): Unit = attr(
        "chainConstraint",
        ConstraintChain(false, leftId, leftSide, rightId, rightSide, chainIds, weights, style))
    fun circleConstraint(id: Int, angle: Int, radius: Int): Unit =
        attr("circleConstraint", ConstraintCircle(0, id, angle, radius))
    fun constraintId(arg: Int): Unit = attr("constraintId", arg)
    fun applyConstraints(): Unit = attr("applyConstraints", Unit)

    companion object : ConstraintLayoutScope() {
        init {
            Anvil.registerAttributeSetter(ConstraintDslSetter)}
    }
}

abstract class ConstraintHelperScope : ViewScope() {
    fun referencedIds(vararg arg: Int): Unit = attr("referencedIds", arg)
}

fun barrier(configure: BarrierScope.() -> Unit = {}) =
    v<Barrier>(configure.bind(BarrierScope))
abstract class BarrierScope : ConstraintHelperScope() {
    fun type(arg: Int): Unit = attr("type", arg)
    fun allowsGoneWidget(arg: Boolean): Unit = attr("allowsGoneWidget", arg)
    companion object : BarrierScope()
}

fun group(configure: GroupScope.() -> Unit = {}) =
    v<Group>(configure.bind(GroupScope))
abstract class GroupScope : ConstraintHelperScope() {
    companion object : GroupScope()
}

fun placeholder(configure: PlaceholderScope.() -> Unit = {}) =
    v<Placeholder>(configure.bind(PlaceholderScope))
abstract class PlaceholderScope : ViewGroupScope() {
    fun contentId(contentId: Int): Unit = attr("contentId", contentId)
    fun emptyVisibility(visibility: Int): Unit = attr("emptyVisibility", visibility)
    companion object : PlaceholderScope()
}

fun guideline(configure: GuidelineScope.() -> Unit = {}) =
    v<Guideline>(configure.bind(GuidelineScope))
abstract class GuidelineScope : ViewGroupScope() {
    fun orientation(orientation: Int): Unit = attr("orientation", orientation)
    fun guideBegin(margin: Int): Unit = attr("guideBegin", margin)
    fun guideEnd(margin: Int): Unit = attr("guideEnd", margin)
    fun guidelinePercent(ratio: Float): Unit = attr("guidelinePercent", ratio)
    companion object : GuidelineScope()
}

object ConstraintDslSetter : Anvil.AttributeSetter<Any?> {
    override fun set(
        v: View,
        name: String,
        arg: Any?,
        old: Any?
    ): Boolean = when (name) {
        "constraintSet" -> when {
            v is ConstraintLayout && arg is ConstraintSet -> {
                v.setConstraintSet(arg)
                true
            }
            else -> false
        }
        "sideConstraint" -> when {
            v.parent is ConstraintLayout && arg is ConstraintSide -> {
                (v.parent as ConstraintLayout).taggedSides += arg.apply { startId = v.id }
                true
            }
            else -> false
        }
        "chainConstraint" -> when {
            v.parent is ConstraintLayout && arg is ConstraintChain -> {
                (v.parent as ConstraintLayout).taggedChains += arg
                true
            }
            else -> false
        }
        "circleConstraint" -> when {
            v.parent is ConstraintLayout && arg is ConstraintCircle -> {
                (v.parent as ConstraintLayout).taggedCircles += arg.apply { centerId = v.id }
                true
            }
            else -> false
        }
        "applyConstraints" -> when {
            v is ConstraintLayout -> {
                val sides = v.taggedSidesOrNull
                val chains = v.taggedChainsOrNull
                val circles = v.taggedCirclesOrNull
                if (!sides.isNullOrEmpty() || !chains.isNullOrEmpty() || !circles.isNullOrEmpty()) {
                    v.updateConstraints {
                        sides?.let {
                            it.forEach { side ->
                                connect(
                                    side.startId,
                                    side.startSide,
                                    side.endId,
                                    side.endSide
                                )
                            }
                            it.clear()
                        }

                        chains?.let {
                            it.forEach { chain ->
                                if(chain.isHorizontal) {
                                    createHorizontalChain(
                                        chain.leftId,
                                        chain.leftSide,
                                        chain.rightId,
                                        chain.rightSide,
                                        chain.chainIds,
                                        chain.weights,
                                        chain.style
                                    )
                                } else {
                                    createVerticalChain(
                                        chain.leftId,
                                        chain.leftSide,
                                        chain.rightId,
                                        chain.rightSide,
                                        chain.chainIds,
                                        chain.weights,
                                        chain.style
                                    )
                                }
                            }
                            it.clear()
                        }

                        circles?.let {
                            it.forEach { circle ->
                                constrainCircle(
                                    circle.id,
                                    circle.centerId,
                                    circle.radius,
                                    circle.angle.toFloat()
                                )
                            }
                            it.clear()
                        }
                    }
                    false
                } else false
            }
            else -> false
        }
        "maxHeight" -> when {
            v is ConstraintLayout && arg is Int -> {
                v.maxHeight = arg
                true
            }
            else -> false
        }
        "maxWidth" -> when {
            v is ConstraintLayout && arg is Int -> {
                v.maxWidth = arg
                true
            }
            else -> false
        }
        "minHeight" -> when {
            v is ConstraintLayout && arg is Int -> {
                v.minHeight = arg
                true
            }
            else -> false
        }
        "minWidth" -> when {
            v is ConstraintLayout && arg is Int -> {
                v.minWidth = arg
                true
            }
            else -> false
        }
        "optimizationLevel" -> when {
            v is ConstraintLayout && arg is Int -> {
                v.optimizationLevel = arg
                true
            }
            else -> false
        }
        "constraintId" -> when {
            arg is Int -> {
                v.id = arg
                if (v.parent is ConstraintLayout) {
                    (v.parent as ConstraintLayout).onViewAdded(v)
                }
                true
            }
            else -> false
        }
        "referencedIds" -> when {
            v is ConstraintHelper && arg is IntArray -> {
                v.referencedIds = arg
                true
            }
            else -> false
        }
        "type" -> when {
            v is Barrier && arg is Int -> {
                v.type = arg
                true
            }
            else -> false
        }
        "allowsGoneWidget" -> when {
            v is Barrier && arg is Boolean -> {
                v.setAllowsGoneWidget(arg)
                true
            }
            else -> false
        }
        "contentId" -> when {
            v is Placeholder && arg is Int -> {
                v.setContentId(arg)
                true
            }
            else -> false
        }
        "emptyVisibility" -> when {
            v is Placeholder && arg is Int -> {
                v.emptyVisibility = arg
                true
            }
            else -> false
        }
        "orientation" -> when {
            v is Guideline && arg is Int -> {
                val params = v.getLayoutParams() as ConstraintLayout.LayoutParams
                params.orientation = arg
                v.setLayoutParams(params)
                true
            }
            else -> false
        }
        "guideBegin" -> when {
            v is Guideline && arg is Int -> {
                val params = v.getLayoutParams() as ConstraintLayout.LayoutParams
                params.guideBegin = arg
                v.setLayoutParams(params)
                true
            }
            else -> false
        }
        "guideEnd" -> when {
            v is Guideline && arg is Int -> {
                val params = v.getLayoutParams() as ConstraintLayout.LayoutParams
                params.guideEnd = arg
                v.setLayoutParams(params)
                true
            }
            else -> false
        }
        "guidelinePercent" -> when {
            v is Guideline && arg is Float -> {
                val params = v.getLayoutParams() as ConstraintLayout.LayoutParams
                params.guidePercent = arg
                v.setLayoutParams(params)
                true
            }
            else -> false
        }
        else -> false
    }
}

private const val CONSTRAINTS_KEY: Int = 1 + 2 shl 24
private const val CHAIN_KEY = 1 + 3 shl 24
private const val CIRCLE_KEY = 1 + 4 shl 24

private val ConstraintLayout.taggedSidesOrNull: MutableList<ConstraintSide>?
    get() = getTag(CONSTRAINTS_KEY) as? LinkedList<ConstraintSide>

private val ConstraintLayout.taggedSides: MutableList<ConstraintSide>
    get() = taggedSidesOrNull ?: LinkedList<ConstraintSide>().also { setTag(CONSTRAINTS_KEY, it) }

private val ConstraintLayout.taggedChainsOrNull: MutableList<ConstraintChain>?
    get() = getTag(CHAIN_KEY) as? LinkedList<ConstraintChain>

private val ConstraintLayout.taggedChains: MutableList<ConstraintChain>
    get() = taggedChainsOrNull ?: LinkedList<ConstraintChain>().also { setTag(CHAIN_KEY, it) }

private val ConstraintLayout.taggedCirclesOrNull: MutableList<ConstraintCircle>?
    get() = getTag(CIRCLE_KEY) as? LinkedList<ConstraintCircle>

private val ConstraintLayout.taggedCircles: MutableList<ConstraintCircle>
    get() = taggedCirclesOrNull ?: LinkedList<ConstraintCircle>().also { setTag(CIRCLE_KEY, it) }

private data class ConstraintSide(
    var startId: Int,
    val startSide: Int,
    val endId: Int,
    val endSide: Int
)

private data class ConstraintCircle(
    var centerId: Int,
    val id: Int,
    val angle: Int,
    val radius: Int
)

private data class ConstraintChain(
    val isHorizontal: Boolean,
    val leftId: Int,
    val leftSide: Int,
    val rightId: Int,
    val rightSide: Int,
    val chainIds: IntArray?,
    val weights: FloatArray?,
    val style: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConstraintChain

        if (isHorizontal != other.isHorizontal) return false
        if (leftId != other.leftId) return false
        if (leftSide != other.leftSide) return false
        if (rightId != other.rightId) return false
        if (rightSide != other.rightSide) return false
        if (chainIds != null) {
            if (other.chainIds == null) return false
            if (!chainIds.contentEquals(other.chainIds)) return false
        } else if (other.chainIds != null) return false
        if (weights != null) {
            if (other.weights == null) return false
            if (!weights.contentEquals(other.weights)) return false
        } else if (other.weights != null) return false
        if (style != other.style) return false

        return true
    }

    override fun hashCode(): Int {
        var result = leftId
        result = 31 * result + if(isHorizontal) 1 else 0
        result = 31 * result + leftSide
        result = 31 * result + rightId
        result = 31 * result + rightSide
        result = 31 * result + (chainIds?.contentHashCode() ?: 0)
        result = 31 * result + (weights?.contentHashCode() ?: 0)
        result = 31 * result + style
        return result
    }
}

private fun ConstraintLayout.updateConstraints(action: ConstraintSet.() -> Unit) {
    val cs = ConstraintSet()
    cs.clone(this)
    action(cs)
    cs.applyTo(this)
}
