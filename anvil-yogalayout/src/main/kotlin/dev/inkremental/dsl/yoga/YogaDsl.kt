@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.yoga

import android.view.View
import com.facebook.yoga.*
import com.facebook.yoga.android.YogaLayout
import dev.inkremental.dsl.android.view.ViewGroupScope
import trikita.anvil.*

fun yogaLayout(configure: YogaLayoutScope.() -> Unit = {}) =
    v<YogaLayout>(configure.bind(YogaLayoutScope))
abstract class YogaLayoutScope : ViewGroupScope() {
    fun flexDirection(direction: YogaFlexDirection?): Unit = attr("flexDirection", direction)
    fun alignItems(align: YogaAlign?): Unit = attr("alignItems", align)
    fun alignContent(alignContent: Float?): Unit = attr("alignContent", alignContent)
    fun alignSelf(alignSelf: Float?): Unit = attr("alignSelf", alignSelf)
    fun aspectRatio(aspectRatio: Float?): Unit = attr("aspectRatio", aspectRatio)
    fun borderLeft(left: Float?): Unit = attr("borderLeft", left)
    fun borderRight(left: Float?): Unit = attr("borderRight", left)
    fun borderTop(left: Float?): Unit = attr("borderTop", left)
    fun borderBottom(left: Float?): Unit = attr("borderBottom", left)
    fun borderStart(left: Float?): Unit = attr("borderStart", left)
    fun borderEnd(left: Float?): Unit = attr("borderEnd", left)
    fun borderHorizontal(left: Float?): Unit = attr("borderHorizontal", left)
    fun borderVertical(left: Float?): Unit = attr("borderVertical", left)
    fun borderAll(left: Float?): Unit = attr("borderAll", left)
    fun marginAutoLeft(): Unit = attr("marginAutoLeft", YogaEdge.LEFT)
    fun marginAutoRight(): Unit = attr("marginAutoRight", YogaEdge.RIGHT)
    fun marginAutoTop(): Unit = attr("marginAutoTop", YogaEdge.TOP)
    fun marginAutoBottom(): Unit = attr("marginAutoBottom", YogaEdge.BOTTOM)
    fun marginAutoStart(): Unit = attr("marginAutoStart", YogaEdge.START)
    fun marginAutoEnd(): Unit = attr("marginAutoEnd", YogaEdge.END)
    fun marginAutoHorizontal(): Unit = attr("marginAutoHorizontal", YogaEdge.HORIZONTAL)
    fun marginAutoVertical(): Unit = attr("marginAutoVertical", YogaEdge.VERTICAL)
    fun marginAutoAll(): Unit = attr("marginAutoAll", YogaEdge.ALL)
    fun direction(direction: YogaDirection?): Unit = attr("direction", direction)
    fun display(display: YogaDisplay?): Unit = attr("display", display)
    fun flexBasis(display: Float?): Unit = attr("flexBasis", display)
    fun flexDirection(display: Float?): Unit = attr("flexDirection", display)
    fun flexGrow(display: Float?): Unit = attr("flexGrow", display)
    fun flexShrink(display: Float?): Unit = attr("flexShrink", display)
    fun yogaHeight(display: Float?): Unit = attr("yogaHeight", display)
    fun yogaWidth(display: Float?): Unit = attr("yogaWidth", display)
    fun justifyContent(justify: YogaJustify?): Unit = attr("justifyContent", justify)
    fun marginLeft(margin: Float?): Unit = attr("marginLeft", margin)
    fun marginRight(margin: Float?): Unit = attr("marginRight", margin)
    fun marginTop(margin: Float?): Unit = attr("marginTop", margin)
    fun marginBottom(margin: Float?): Unit = attr("marginBottom", margin)
    fun marginStart(margin: Float?): Unit = attr("marginStart", margin)
    fun marginEnd(margin: Float?): Unit = attr("marginEnd", margin)
    fun marginHorizontal(margin: Float?): Unit = attr("marginHorizontal", margin)
    fun marginVertical(margin: Float?): Unit = attr("marginVertical", margin)
    fun marginAll(margin: Float?): Unit = attr("marginAll", margin)
    fun paddingAll(margin: Float?): Unit = attr("paddingAll", margin)
    fun paddingLeft(margin: Float?): Unit = attr("paddingLeft", margin)
    fun paddingRight(margin: Float?): Unit = attr("paddingRight", margin)
    fun paddingTop(margin: Float?): Unit = attr("paddingTop", margin)
    fun paddingBottom(margin: Float?): Unit = attr("paddingBottom", margin)
    fun paddingStart(margin: Float?): Unit = attr("paddingStart", margin)
    fun paddingEnd(margin: Float?): Unit = attr("paddingEnd", margin)
    fun paddingHorizontal(margin: Float?): Unit = attr("paddingHorizontal", margin)
    fun paddingVertical(margin: Float?): Unit = attr("paddingVertical", margin)
    fun positionType(positionType: YogaPositionType?): Unit = attr("positionType", positionType)
    fun wrap(wrap: YogaWrap?): Unit = attr("wrap", wrap)
    fun flex(weight: Float): Unit = attr("flex", weight)
    fun flexBasisPercent(percent: Float): Unit = attr("flexBasisPercent", percent)
    fun heightPercent(percent: Float): Unit = attr("heightPercent", percent)
    fun marginLeftPercent(percent: Float): Unit = attr("marginLeftPercent", percent)
    fun marginRightPercent(percent: Float): Unit = attr("marginRightPercent", percent)
    fun marginTopPercent(percent: Float): Unit = attr("marginTopPercent", percent)
    fun marginBottomPercent(percent: Float): Unit = attr("marginBottomPercent", percent)
    fun marginStartPercent(percent: Float): Unit = attr("marginStartPercent", percent)
    fun marginEndPercent(percent: Float): Unit = attr("marginEndPercent", percent)
    fun marginHorizontalPercent(percent: Float): Unit = attr("marginHorizontalPercent", percent)
    fun marginVerticalPercent(percent: Float): Unit = attr("marginVerticalPercent", percent)
    fun marginAllPercent(percent: Float): Unit = attr("marginAllPercent", percent)
    fun paddingLeftPercent(percent: Float): Unit = attr("paddingLeftPercent", percent)
    fun paddingRightPercent(percent: Float): Unit = attr("paddingRightPercent", percent)
    fun paddingTopPercent(percent: Float): Unit = attr("paddingTopPercent", percent)
    fun paddingBottomPercent(percent: Float): Unit = attr("paddingBottomPercent", percent)
    fun paddingStartPercent(percent: Float): Unit = attr("paddingStartPercent", percent)
    fun paddingEndPercent(percent: Float): Unit = attr("paddingEndPercent", percent)
    fun paddingHorizontalPercent(percent: Float): Unit = attr("paddingHorizontalPercent", percent)
    fun paddingVerticalPercent(percent: Float): Unit = attr("paddingVerticalPercent", percent)
    fun paddingAllPercent(percent: Float): Unit = attr("paddingAllPercent", percent)
    fun positionLeftPercent(percent: Float): Unit = attr("positionLeftPercent", percent)
    fun positionRightPercent(percent: Float): Unit = attr("positionRightPercent", percent)
    fun positionTopPercent(percent: Float): Unit = attr("positionTopPercent", percent)
    fun positionBottomPercent(percent: Float): Unit = attr("positionBottomPercent", percent)
    fun positionStartPercent(percent: Float): Unit = attr("positionStartPercent", percent)
    fun positionEndPercent(percent: Float): Unit = attr("positionEndPercent", percent)
    fun positionHorizontalPercent(percent: Float): Unit = attr("positionHorizontalPercent", percent)
    fun positionVerticalPercent(percent: Float): Unit = attr("positionVerticalPercent", percent)
    fun positionAllPercent(percent: Float): Unit = attr("positionAllPercent", percent)
    fun widthPercent(percent: Float): Unit = attr("widthPercent", percent)
    fun maxWidthPercent(percent: Float): Unit = attr("maxWidthPercent", percent)
    fun maxHeightPercent(percent: Float): Unit = attr("maxHeightPercent", percent)
    fun minHeightPercent(percent: Float): Unit = attr("minHeightPercent", percent)
    fun minWidthPercent(percent: Float): Unit = attr("minWidthPercent", percent)

    companion object : YogaLayoutScope() {
        init {
            Anvil.registerAttributeSetter(YogaDslSetter)}
    }
}

object YogaDslSetter : Anvil.AttributeSetter<Any?> {
    override fun set(
        v: View,
        name: String,
        arg: Any?,
        old: Any?
    ): Boolean = when (name) {
        "flexDirection" -> when {
            v is YogaLayout && arg is YogaFlexDirection -> {
                v.yogaNode.flexDirection = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "alignItems" -> when {
            v.parent is YogaLayout && arg is YogaAlign -> {
                (v as YogaLayout).yogaNode.alignItems = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "alignContent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.alignContent = YogaAlign.fromInt(Math.round(arg))
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "alignSelf" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.alignSelf = YogaAlign.fromInt(Math.round(arg))
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "aspectRatio" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.aspectRatio = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "borderLeft" -> when {
            setBorder(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "borderRight" -> when {
            setBorder(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "borderTop" -> when {
            setBorder(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "borderBottom" -> when {
            setBorder(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "borderStart" -> when {
            setBorder(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "borderEnd" -> when {
            setBorder(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "borderHorizontal" -> when {
            setBorder(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "borderVertical" -> when {
            setBorder(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "borderAll" -> when {
            setBorder(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "marginAutoLeft", "marginAutoStart", "marginAutoRight", "marginAutoTop", "marginAutoBottom", "marginAutoEnd", "marginAutoHorizontal", "marginAutoVertical", "marginAutoAll" -> when {
            setMarginAuto(
                v,
                arg
            )
            -> {
                true
            }
            else -> false
        }
        "direction" -> when {
            v.parent is YogaLayout && arg is YogaDirection -> {
                (v as YogaLayout).yogaNode.setDirection(arg as YogaDirection?)
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "display" -> when {
            v.parent is YogaLayout && arg is YogaDisplay -> {
                (v as YogaLayout).yogaNode.display = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "flexBasis" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setFlexBasis(arg)
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "flexGrow" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.flexGrow = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "flexShrink" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.flexShrink = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "yogaHeight" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setHeight(arg)
                true
            }
            else -> false
        }
        "yogaWidth" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setWidth(arg)
                true
            }
            else -> false
        }
        "flex" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v.parent as YogaLayout).getYogaNodeForView(v).flex = arg
                true
            }
            else -> false
        }
        "justifyContent" -> when {
            v.parent is YogaLayout && arg is YogaJustify -> {
                (v as YogaLayout).yogaNode.justifyContent = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "marginLeft" -> when {
            setMargin(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "marginStart" -> when {
            setMargin(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "marginRight" -> when {
            setMargin(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "marginTop" -> when {
            setMargin(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "marginBottom" -> when {
            setMargin(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "marginEnd" -> when {
            setMargin(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "marginHorizontal" -> when {
            setMargin(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "marginVertical" -> when {
            setMargin(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "marginAll" -> when {
            setMargin(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "paddingLeft" -> when {
            setPadding(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "paddingStart" -> when {
            setPadding(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "paddingRight" -> when {
            setPadding(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "paddingTop" -> when {
            setPadding(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "paddingBottom" -> when {
            setPadding(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "paddingEnd" -> when {
            setPadding(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "paddingHorizontal" -> when {
            setPadding(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "paddingVertical" -> when {
            setPadding(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "paddingAll" -> when {
            setPadding(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "positionLeft" -> when {
            setPosition(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "positionStart" -> when {
            setPosition(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "positionRight" -> when {
            setPosition(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "positionTop" -> when {
            setPosition(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "positionBottom" -> when {
            setPosition(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "positionEnd" -> when {
            setPosition(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "positionHorizontal" -> when {
            setPosition(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "positionVertical" -> when {
            setPosition(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "positionAll" -> when {
            setPosition(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "positionType" -> when {
            v.parent is YogaLayout && arg is YogaPositionType -> {
                (v as YogaLayout).yogaNode.positionType = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "wrap" -> when {
            v.parent is YogaLayout && arg is YogaWrap -> {
                (v as YogaLayout).yogaNode.wrap = arg
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "flexBasisPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setFlexBasisPercent(arg)
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "heightPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setHeightPercent(arg)
                val lp = v.getLayoutParams()
                v.setLayoutParams(lp)
                true
            }
            else -> false
        }
        "marginLeftPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "marginRightPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "marginTopPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "marginBottomPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "marginStartPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "marginEndPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "marginHorizontalPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "marginVerticalPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "marginAllPercent" -> when {
            setMarginPercent(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "positionLeftPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "positionRightPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "positionTopPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "positionBottomPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "positionStartPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "positionEndPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "positionHorizontalPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "positionVerticalPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "positionAllPercent" -> when {
            setPositionPercent(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "paddingLeftPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.LEFT) -> {
                true
            }
            else -> false
        }
        "paddingRightPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.RIGHT) -> {
                true
            }
            else -> false
        }
        "paddingTopPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.TOP) -> {
                true
            }
            else -> false
        }
        "paddingBottomPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.BOTTOM) -> {
                true
            }
            else -> false
        }
        "paddingStartPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.START) -> {
                true
            }
            else -> false
        }
        "paddingEndPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.END) -> {
                true
            }
            else -> false
        }
        "paddingHorizontalPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.HORIZONTAL) -> {
                true
            }
            else -> false
        }
        "paddingVerticalPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.VERTICAL) -> {
                true
            }
            else -> false
        }
        "paddingAllPercent" -> when {
            setPaddingPercent(v, arg, YogaEdge.ALL) -> {
                true
            }
            else -> false
        }
        "widthPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setWidthPercent(arg)
                true
            }
            else -> false
        }
        "minWidthPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setMinWidthPercent(arg)
                true
            }
            else -> false
        }
        "minHeightPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setMinHeightPercent(arg)
                true
            }
            else -> false
        }
        "maxWidthPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setMaxWidthPercent(arg)
                true
            }
            else -> false
        }
        "maxHeightPercent" -> when {
            v.parent is YogaLayout && arg is Float -> {
                (v as YogaLayout).yogaNode.setMaxHeightPercent(arg)
                true
            }
            else -> false
        }
        else -> false
    }
}

private fun View.yogaNode(): YogaNode = (parent as YogaLayout).getYogaNodeForView(this)

private fun setBorder(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setBorder(edge, arg)
        return true
    }
    return false
}

private fun setMarginAuto(v: View, arg: Any?): Boolean {
    if (v.parent is YogaLayout && arg is YogaEdge) {
        v.yogaNode().setMarginAuto(arg)
        return true
    }
    return false
}

private fun setMarginPercent(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setMarginPercent(edge, arg)
        return true
    }
    return false
}

private fun setPaddingPercent(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setPaddingPercent(edge, arg)
        return true
    }
    return false
}

private fun setPositionPercent(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setPositionPercent(edge, arg)
        return true
    }
    return false
}

private fun setMargin(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setMargin(edge, arg)
        return true
    }
    return false
}

private fun setPadding(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setPadding(edge, arg)
        return true
    }
    return false
}

private fun setPosition(v: View, arg: Any?, edge: YogaEdge): Boolean {
    if (v.parent is YogaLayout && arg is Float) {
        v.yogaNode().setPosition(edge, arg)
        return true
    }
    return false
}
