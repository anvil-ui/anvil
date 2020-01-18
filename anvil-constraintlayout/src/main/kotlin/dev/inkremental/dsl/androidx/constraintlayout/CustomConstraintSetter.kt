@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.androidx.constraintlayout

import android.view.View
import androidx.constraintlayout.widget.*
import dev.inkremental.Anvil
import java.util.*

object CustomConstraintSetter : Anvil.AttributeSetter<Any> {
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

private fun ConstraintLayout.updateConstraints(action: ConstraintSet.() -> Unit) {
    val cs = ConstraintSet()
    cs.clone(this)
    action(cs)
    cs.applyTo(this)
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
