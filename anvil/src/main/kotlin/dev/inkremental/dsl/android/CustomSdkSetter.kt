@file:Suppress("DEPRECATION", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")

package dev.inkremental.dsl.android

import android.animation.Animator
import android.animation.AnimatorSet
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import dev.inkremental.Inkremental
import dev.inkremental.attr
import dev.inkremental.dip
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.android.widget.*
import java.util.*

// weight constants
sealed class Size {
    object MATCH : Size()
    object WRAP : Size()
    class EXACT(val size: Px) : Size()
}

// gravity constants
const val TOP = Gravity.TOP
const val BOTTOM = Gravity.BOTTOM
const val LEFT = Gravity.LEFT
const val RIGHT = Gravity.RIGHT
const val CENTER_VERTICAL = Gravity.CENTER_VERTICAL
const val GROW_VERTICAL = Gravity.FILL_VERTICAL
const val CENTER_HORIZONTAL = Gravity.CENTER_HORIZONTAL
const val GROW_HORIZONTAL = Gravity.FILL_HORIZONTAL
const val CENTER = CENTER_VERTICAL or CENTER_HORIZONTAL
const val GROW = GROW_VERTICAL or GROW_HORIZONTAL
const val CLIP_VERTICAL = Gravity.CLIP_VERTICAL
const val CLIP_HORIZONTAL = Gravity.CLIP_HORIZONTAL
const val START = Gravity.START
const val END = Gravity.END

const val INIT_LITERAL = "init"

inline class Sp(val value: Float)
inline class Dip(val value: Int)
inline class Px(val value: Int)

fun ViewScope.init(action: (View) -> Unit) = attr(INIT_LITERAL, action)
fun ViewScope.size(w: Size, h: Size) = attr("size", w to h)
fun ViewScope.tag(key: Int, value: Any?) = attr("tag", key to value)

fun ViewScope.padding(l: Dip, t: Dip, r: Dip, b: Dip) = attr("padding", listOf(l.value, t.value, r.value, b.value))
fun ViewScope.padding(p: Dip) = padding(p, p, p, p)
fun ViewScope.padding(h: Dip, v: Dip) = padding(h, v, h, v)
fun ViewScope.paddingTop(t: Dip) = padding(0.dp, t, 0.dp, 0.dp)
fun ViewScope.paddingBottom(t: Dip) = padding(0.dp, 0.dp, 0.dp, t)
fun ViewScope.paddingLeft(t: Dip) = padding(t, 0.dp, 0.dp, 0.dp)
fun ViewScope.paddingRight(t: Dip) = padding(0.dp, 0.dp, t, 0.dp)

fun ViewScope.margin(l: Dip, t: Dip, r: Dip, b: Dip) = attr("margin", listOf(l.value, t.value, r.value, b.value))
fun ViewScope.margin(m: Dip) = margin(m, m, m, m)
fun ViewScope.margin(h: Dip, v: Dip) = margin(h, v, h, v)
fun ViewScope.marginTop(t: Dip) = margin(0.dp, t, 0.dp, 0.dp)
fun ViewScope.marginBottom(t: Dip) = margin(0.dp, 0.dp, 0.dp, t)
fun ViewScope.marginLeft(t: Dip) = margin(t, 0.dp, 0.dp, 0.dp)
fun ViewScope.marginRight(t: Dip) = margin(0.dp, 0.dp, t, 0.dp)

fun ViewScope.align(verb: Int) = align(verb, -1)
fun ViewScope.above(subject: Int) = align(RelativeLayout.ABOVE, subject)
fun ViewScope.alignBaseline(subject: Int) = align(RelativeLayout.ALIGN_BASELINE, subject)
fun ViewScope.alignBottom(subject: Int) = align(RelativeLayout.ALIGN_BOTTOM, subject)
fun ViewScope.alignEnd(subject: Int) = align(RelativeLayout.ALIGN_END, subject)
fun ViewScope.alignLeft(subject: Int) = align(RelativeLayout.ALIGN_LEFT, subject)
fun ViewScope.alignParentBottom() = align(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
fun ViewScope.alignParentEnd() = align(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
fun ViewScope.alignParentLeft() = align(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
fun ViewScope.alignParentRight() = align(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
fun ViewScope.alignParentStart() = align(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
fun ViewScope.alignParentTop() = align(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
fun ViewScope.alignRight(subject: Int) = align(RelativeLayout.ALIGN_RIGHT, subject)
fun ViewScope.alignStart(subject: Int) = align(RelativeLayout.ALIGN_START, subject)
fun ViewScope.alignTop(subject: Int) = align(RelativeLayout.ALIGN_TOP, subject)
fun ViewScope.below(subject: Int) = align(RelativeLayout.BELOW, subject)
fun ViewScope.centerHorizontal() = align(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
fun ViewScope.centerInParent() = align(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
fun ViewScope.centerVertical() = align(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
fun ViewScope.toEndOf(subject: Int) = align(RelativeLayout.END_OF, subject)
fun ViewScope.toLeftOf(subject: Int) = align(RelativeLayout.LEFT_OF, subject)
fun ViewScope.toRightOf(subject: Int) = align(RelativeLayout.RIGHT_OF, subject)
fun ViewScope.toStartOf(subject: Int) = align(RelativeLayout.START_OF, subject)
fun ViewScope.align(verb: Int, subject: Int) = attr("align", verb to subject)

fun ViewScope.anim(trigger: Boolean, animator: Animator) = attr("anim", AnimatorPair(animator, trigger))
fun ViewScope.anim(trigger: Boolean, animatorIn: Animator, animatorOut: Animator) = attr("animInOut", AnimatorTriple(animatorIn, animatorOut, trigger))

fun TextViewScope.textSize(sizeSp: Sp) = attr("textSizeSp", sizeSp.value)
fun TextViewScope.textSize(size: Dip) = attr("textSizeDip", size.value)
fun TextViewScope.textSize(sizePx: Px) = attr("textSizePx", sizePx.value)
fun TextViewScope.typeface(assetPath: String) = attr("typeface", assetPath)
fun TextViewScope.typeface(assetPath: String?, style: Int) = attr("typeface", assetPath to style)

fun TextViewScope.compoundDrawables(l: Drawable, t: Drawable, r: Drawable, b: Drawable) = attr("compoundDrawables", listOf(l, t, r, b))
fun TextViewScope.compoundDrawablesWithIntrinsicBounds(l: Drawable, t: Drawable, r: Drawable, b: Drawable) = attr("compoundDrawablesWithIntrinsicBounds", listOf(l, t, r, b))
fun TextViewScope.compoundDrawablesWithIntrinsicBounds(l: Int, t: Int, r: Int, b: Int) = attr("compoundDrawablesWithIntrinsicBoundsResource", listOf(l, t, r, b))
fun TextViewScope.shadowLayer(radius: Float, dx: Float, dy: Float, color: Int) = attr("shadowLayer", listOf<Number>(radius, dx, dy, color))

fun ViewScope.visibility(visible: Boolean) = visibility(if (visible) View.VISIBLE else View.GONE)

fun RadioGroupScope.check(id: Int) = attr("check", id)

fun ViewScope.weight(weight: Float) = attr("weight", weight)
fun ViewScope.layoutGravity(gravity: Int) = attr("layoutGravity", gravity)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun ViewScope.elevate(arg: Dip): Unit = attr("elevation", arg.value)

typealias SeekBarChangeListener = (seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit
fun SeekBarScope.onSeekBarChange(listener: SeekBarChangeListener) = attr("onSeekBarChange", listener)

typealias ItemSelectedListener = (parent: AdapterView<*>, view: View?, position: Int, id: Long) -> Unit
fun AdapterViewScope.onItemSelected(listener: ItemSelectedListener) = attr("onItemSelected", listener)
fun AutoCompleteTextViewScope.onItemSelected(listener: ItemSelectedListener) = attr("onItemSelected", listener)

fun TextViewScope.text(text: CharSequence?) = attr("text", text)
fun TextViewScope.onTextChanged(watcher: (CharSequence) -> Unit) = attr("onTextChanged", watcher)
fun TextViewScope.onTextChanged(watcher: TextWatcher) = attr("onTextChanged", watcher)
fun TextViewScope.inputExtras(extras: Int) = attr("inputExtras", extras)
fun TextViewScope.minWidth(arg: Dip): Unit = attr("minWidth", arg.value)

fun SwitchViewScope.switchMinWidth(arg: Dip): Unit = attr("switchMinWidth", arg.value)

object CustomSdkSetter : Inkremental.AttributeSetter<Any> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when (name) {
        INIT_LITERAL -> when (value) {
            is Function<*> -> {
                (value as (View) -> Any?)(v)
                true
            }
            else -> false
        }
        "tag" -> when {
            value is Pair<*, *> -> {
                v.setTag(value.first as Int, value.second)
                true
            }
            else -> false
        }
        "size" -> when {
            value is Pair<*, *> -> {
                val p = v.layoutParams
                val width = value.first
                val height = value.second
                when (width) {
                    is Size.EXACT -> {
                        p.width = width.size.value
                    }
                    is Size.MATCH -> {
                        p.width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    is Size.WRAP -> {
                        p.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    }
                }
                when (height) {
                    is Size.EXACT -> {
                        p.height = height.size.value
                    }
                    is Size.MATCH -> {
                        p.height = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    is Size.WRAP -> {
                        p.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    }
                }
                v.layoutParams = p
                true
            }
            else -> false
        }
        "padding" -> when {
            value is List<*> -> {
                val (l, t, r, b) = value as List<Int>
                v.setPadding(
                    dip(l),
                    dip(t),
                    dip(r),
                    dip(b)
                )
                true
            }
            else -> false
        }
        "margin" -> when {
            v.layoutParams is ViewGroup.MarginLayoutParams && value is List<*> -> {
                val p = v.layoutParams as ViewGroup.MarginLayoutParams
                val (l, t, r, b) = value as List<Int>
                p.leftMargin = dip(l)
                p.topMargin = dip(t)
                p.rightMargin = dip(r)
                p.bottomMargin = dip(b)
                v.layoutParams = p
                true
            }
            else -> false
        }
        "align" -> when {
            v.layoutParams is RelativeLayout.LayoutParams && value is Pair<*, *> -> {
                val p = v.layoutParams as RelativeLayout.LayoutParams
                val (verb, subject) = value as Pair<Int, Int>
                p.addRule(verb, subject)
                true
            }
            else -> false
        }
        "anim" -> when {
            value is AnimatorPair -> {
                if(value.trigger) {
                    value.animator.apply {
                        if (this !is AnimatorSet){
                            setTarget(v)
                        }
                        start()
                    }
                } else {
                    value.animator.cancel()
                }
                true
            }
            else -> false
        }
        "animInOut" -> when (value) {
            is AnimatorTriple -> {
                if (value.trigger) {
                    if (value.animatorIn !is AnimatorSet) {
                        value.animatorIn.setTarget(v)
                    }
                    value.animatorOut.cancel()
                    value.animatorIn.start()
                } else {
                    if (value.animatorOut !is AnimatorSet) {
                        value.animatorOut.setTarget(v)
                    }
                    value.animatorIn.cancel()
                    value.animatorOut.start()
                }
                true
            }
            else -> false
        }
        "textSizeSp" -> when {
            v is TextView &&  value is Float-> {
                v.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)
                true
            }
            else -> false
        }
        "textSizeDip" -> when {
            v is TextView && value is Int -> {
                v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, value.toFloat())
                true
            }
            else -> false
        }
        "textSizePx" -> when {
            v is TextView && value is Int -> {
                v.setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())
                true
            }
            else -> false
        }
        "typeface" -> when {
            v is TextView && value is String -> {
                v.typeface = Typeface.createFromAsset(v.context.assets, value)
                true
            }
            v is TextView && value is Pair<*, *> -> {
                val (path, style) = value as Pair<String?, Int>
                val typeface = path?.let { Typeface.createFromAsset(v.context.assets, it) }
                v.setTypeface(typeface, style)
                true
            }
            else -> false
        }
        "compoundDrawables" -> when {
            v is TextView && value is List<*> -> {
                val (l, t, r, b) = value as List<Drawable>
                v.setCompoundDrawables(l, t, r, b)
                true
            }
            else -> false
        }
        "compoundDrawablesWithIntrinsicBounds" -> when {
            v is TextView && value is List<*> -> {
                val (l, t, r, b) = value as List<Drawable>
                v.setCompoundDrawablesWithIntrinsicBounds(l, t, r, b)
                true
            }
            else -> false
        }
        "compoundDrawablesWithIntrinsicBoundsResource" -> when {
            v is TextView && value is List<*> -> {
                val (l, t, r, b) = value as List<Int>
                v.setCompoundDrawablesWithIntrinsicBounds(l, t, r, b)
                true
            }
            else -> false
        }
        "shadowLayer" -> when {
            v is TextView && value is List<*> -> {
                val (radius, dx, dy, color) = value as List<Number>
                v.setShadowLayer(radius.toFloat(), dx.toFloat(), dy.toFloat(), color.toInt())
                true
            }
            else -> false
        }
        "check" -> when {
            v is RadioGroup && value is Int -> {
                v.check(value)
                true
            }
            else -> false
        }
        "weight" -> when {
            v.layoutParams is LinearLayout.LayoutParams && value is Float -> {
                (v.layoutParams as LinearLayout.LayoutParams).weight = value
                true
            }
            else -> false
        }
        "layoutGravity" -> when {
            v.layoutParams is LinearLayout.LayoutParams && value is Int -> {
                // TODO should we set new layoutParams back here?
                (v.layoutParams as LinearLayout.LayoutParams).gravity = value
                true
            }
            v.layoutParams is FrameLayout.LayoutParams && value is Int -> {
                // TODO should we set new layoutParams back here?
                (v.layoutParams as FrameLayout.LayoutParams).gravity = value
                true
            }
            else -> false
        }
        "onSeekBarChange" -> when {
            v is SeekBar && value is Function<*> -> {
                v.setOnSeekBarChangeListener(SeekBarChangeWrapper(value as SeekBarChangeListener))
                true
            }
            else -> false
        }
        "onItemSelected" -> when {
            v is AdapterView<*> && value is Function<*> -> {
                v.onItemSelectedListener =
                    ItemSelectedWrapper(value as ItemSelectedListener)
                true
            }
            v is AutoCompleteTextView && value is Function<*> -> {
                v.onItemSelectedListener =
                    ItemSelectedWrapper(value as ItemSelectedListener)
                true
            }
            else -> false
        }
        "text" -> when {
            v is TextView && value is CharSequence? -> {
                if (v != TextWatcherProxy.currentInputTextView) {
                    v.text = value
                }
                true
            }
            // TODO do we need to process TextSwitcher here?
            else -> false
        }
        "onTextChanged" -> when {
            v is TextView && value is Function<*> -> {
                value as (CharSequence) -> Unit
                val existing = TextWatcherProxy.watchers.keys.firstOrNull { it.hasImpl(prevValue) }
                if (existing != null) {
                    existing.setImpl(value)
                } else {
                    val proxy = TextWatcherProxy(v).setImpl(value)
                    TextWatcherProxy.watchers[proxy] = Unit
                    v.addTextChangedListener(proxy)
                }
                true
            }
            v is TextView && value is TextWatcher -> {
                val existing = TextWatcherProxy.watchers.keys.firstOrNull { it.hasImpl(prevValue) }
                if (existing != null) {
                    existing.setImpl(value)
                } else {
                    val proxy = TextWatcherProxy(v).setImpl(value)
                    TextWatcherProxy.watchers[proxy] = Unit
                    v.addTextChangedListener(proxy)
                }
                true
            }
            else -> false
        }
        "inputExtras" -> when {
            v is TextView && value is Int -> {
                try {
                    v.setInputExtras(value)
                    true
                } catch (e: org.xmlpull.v1.XmlPullParserException) {
                    e.printStackTrace()
                    false
                } catch (e: java.io.IOException) {
                    e.printStackTrace()
                    false
                }
            }
            else -> false
        }
        "minWidth" -> when {
            v is TextView && value is Int -> {
                v.minWidth = dip(value)
                true
            }
            else -> false
        }
        "switchMinWidth" -> when {
            v is Switch && value is Int -> {
                v.switchMinWidth = dip(value)
                true
            }
            else -> false
        }
        "elevation" -> when {
            value is Int -> {
                v.elevation = dip(value).toFloat()
                true
            }
            else -> false
        }
        else -> false
    }
}

private class AnimatorTriple(var animatorIn: Animator, var animatorOut: Animator, val trigger: Boolean) {

    override fun hashCode(): Int {
        return if (trigger) 0 else 1
    }

    override fun equals(o: Any?): Boolean {
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val triple = o as AnimatorTriple
        if (triple.trigger != trigger) {
            if (triple.animatorIn.isRunning) {
                triple.animatorIn.cancel()
            }
            if (triple.animatorOut.isRunning) {
                triple.animatorOut.cancel()
            }
            return false
        }
        animatorIn = triple.animatorIn
        animatorOut = triple.animatorOut
        return true
    }

}

private class AnimatorPair(var animator: Animator, var trigger: Boolean) {
    override fun hashCode(): Int = if(trigger) 1 else 0
    override fun equals(other: Any?): Boolean {
        if(other == null || other !is AnimatorPair) return false
        if(this.trigger != other.trigger) {
            other.animator.takeIf { it.isRunning }?.cancel()
            return false
        }
        this.animator = other.animator
        return true
    }
}

private class SeekBarChangeWrapper(private val listener: SeekBarChangeListener) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        listener(seekBar, progress, fromUser)
        Inkremental.render()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun hashCode(): Int = listener.hashCode()
    override fun equals(other: Any?): Boolean = listener == other
}

private class ItemSelectedWrapper(private val listener: ItemSelectedListener) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        listener(parent, view, position, id)
        Inkremental.render()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun hashCode(): Int = listener.hashCode()
    override fun equals(other: Any?): Boolean = listener == other
}

class TextWatcherProxy(private val view: TextView) : TextWatcher {
    private var watcher: TextWatcher? = null
    private var simpleWatcher: (CharSequence) -> Unit = {}
    private var text: String = ""

    companion object {
        val watchers = WeakHashMap<TextWatcherProxy, Unit>()
        var currentInputTextView: TextView? = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        watcher?.beforeTextChanged(s, start, count, after)
    }

    override fun afterTextChanged(s: Editable) {
        watcher?.afterTextChanged(s)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val string = s.toString()
        val old = currentInputTextView
        currentInputTextView = view
        if (text != string) {
            watcher?.onTextChanged(s, start, before, count)
            simpleWatcher(s)
            text = string
            Inkremental.render()
        }
        currentInputTextView = old
    }

    fun setImpl(watcher: TextWatcher): TextWatcherProxy = apply {
        this.watcher = watcher
        this.simpleWatcher = {}
    }

    fun setImpl(watcher: (CharSequence) -> Unit): TextWatcherProxy = apply {
        this.watcher = null
        this.simpleWatcher = watcher
    }

    fun hasImpl(watcher: Any?): Boolean = watcher != null && (watcher == this.watcher || watcher == this.simpleWatcher)
}
