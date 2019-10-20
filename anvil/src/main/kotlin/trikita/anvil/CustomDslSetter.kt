@file:Suppress("DEPRECATION", "UNCHECKED_CAST")

package trikita.anvil

import android.animation.Animator
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.*
import android.widget.*
import trikita.anvil.*
import java.util.*

// weight constants
const val FILL = ViewGroup.LayoutParams.MATCH_PARENT
const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT
const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT

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

fun ViewScope.init(action: (View) -> Unit) = attr("init", action)
fun ViewScope.size(w: Int, h: Int) = attr("size", w to h)
fun ViewScope.tag(key: Int, value: Any?) = attr("tag", key to value)

fun ViewScope.padding(l: Int, t: Int, r: Int, b: Int) = attr("padding", listOf(l, t, r, b))
fun ViewScope.padding(p: Int) = padding(p, p, p, p)
fun ViewScope.padding(h: Int, v: Int) = padding(h, v, h, v)

fun ViewScope.margin(l: Int, t: Int, r: Int, b: Int) = attr("margin", listOf(l, t, r, b))
fun ViewScope.margin(m: Int) = margin(m, m, m, m)
fun ViewScope.margin(h: Int, v: Int) = margin(h, v, h, v)

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

fun ViewScope.anim(animator: Animator, trigger: Boolean) = attr("anim", AnimatorPair(animator, trigger))

fun TextViewScope.typeface(assetPath: String) = attr("typeface", assetPath)
fun TextViewScope.typeface(assetPath: String?, style: Int) = attr("typeface", assetPath to style)

fun TextViewScope.compoundDrawables(l: Drawable, t: Drawable, r: Drawable, b: Drawable) = attr("compoundDrawables", listOf(l, t, r, b))
fun TextViewScope.compoundDrawablesWithIntrinsicBounds(l: Drawable, t: Drawable, r: Drawable, b: Drawable) = attr("compoundDrawablesWithIntrinsicBounds", listOf(l, t, r, b))
fun TextViewScope.compoundDrawablesWithIntrinsicBounds(l: Int, t: Int, r: Int, b: Int) = attr("compoundDrawablesWithIntrinsicBoundsResource", listOf(l, t, r, b))
fun TextViewScope.shadowLayer(radius: Float, dx: Float, dy: Float, color: Int) = attr("shadowLayer", listOf<Number>(radius, dx, dy, color))

fun ViewScope.visibility(visible: Boolean) = visibility(if(visible) View.VISIBLE else View.GONE)

fun RadioGroupScope.check(id: Int) = attr("check", id)

fun ViewScope.weight(weight: Float) = attr("weight", weight)
fun ViewScope.layoutGravity(gravity: Int) = attr("layoutGravity", gravity)

typealias SeekBarChangeListener = (seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit
fun SeekBarScope.onSeekBarChange(listener: SeekBarChangeListener) = attr("onSeekBarChange", listener)

typealias ItemSelectedListener = (parent: AdapterView<*>, view: View?, position: Int, id: Long) -> Unit
fun AdapterViewScope.onItemSelected(listener: ItemSelectedListener) = attr("onItemSelected", listener)
fun AutoCompleteTextViewScope.onItemSelected(listener: ItemSelectedListener) = attr("onItemSelected", listener)

fun TextViewScope.text(text: CharSequence?) = attr("text", text)
fun TextViewScope.onTextChanged(watcher: (CharSequence) -> Unit) = attr("onTextChanged", watcher)
fun TextViewScope.onTextChanged(watcher: TextWatcher) = attr("onTextChanged", watcher)
fun TextViewScope.inputExtras(extras: Int) = attr("inputExtras", extras)

object CustomDslSetter : Anvil.AttributeSetter<Any?> {
    override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean = when(name) {
        "init" -> when {
            value is Function<*> -> {
                if (Anvil.get(v, "_initialized") == null) {
                    Anvil.set(v, "_initialized", true)
                    (value as (View) -> Any?)(v)
                }
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
                p.width = value.first as Int
                p.height = value.second as Int
                v.layoutParams = p
                true
            }
            else -> false
        }
        "padding" -> when {
            value is List<*> -> {
                val (l, t, r, b) = value as List<Int>
                v.setPadding(l, t, r, b)
                true
            }
            else -> false
        }
        "margin" -> when {
            v.layoutParams is ViewGroup.MarginLayoutParams && value is List<*> -> {
                val p = v.layoutParams as ViewGroup.MarginLayoutParams
                val (l, t, r, b) = value as List<Int>
                p.leftMargin = l
                p.topMargin = t
                p.rightMargin = r
                p.bottomMargin = b
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
                    value.animator?.let {
                        it.setTarget(v)
                        it.start()
                    }
                }
                true
            }
            else -> false
        }
        "textSize" -> when {
            v is TextView && value is Float -> {
                v.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
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
                v.onItemSelectedListener = ItemSelectedWrapper(value as ItemSelectedListener)
                true
            }
            v is AutoCompleteTextView && value is Function<*> -> {
                v.onItemSelectedListener = ItemSelectedWrapper(value as ItemSelectedListener)
                true
            }
            else -> false
        }
        "text" -> when {
            v is TextView && value is CharSequence? -> {
                if(v != TextWatcherProxy.currentInputTextView) {
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
                if(existing != null) {
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
                if(existing != null) {
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
        else -> false
    }
}

private class AnimatorPair(var animator: Animator?, var trigger: Boolean) {
    override fun hashCode(): Int = if(trigger) 1 else 0
    override fun equals(other: Any?): Boolean {
        if(other == null || other !is AnimatorPair) return false
        if(this.trigger != other.trigger) {
            other.animator?.takeIf { it.isRunning }?.cancel()
            return false
        }
        this.animator = other.animator
        return true
    }
}

private class SeekBarChangeWrapper(private val listener: SeekBarChangeListener) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        listener(seekBar, progress, fromUser)
        Anvil.render()
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    override fun hashCode(): Int = listener.hashCode()
    override fun equals(other: Any?): Boolean = listener == other
}

private class ItemSelectedWrapper(private val listener: ItemSelectedListener) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        listener(parent, view, position, id)
        Anvil.render()
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
        if(text != string) {
            watcher?.onTextChanged(s, start, before, count)
            simpleWatcher(s)
            text = string
            Anvil.render()
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
