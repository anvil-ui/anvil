package dev.inkremental

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.view.ViewScope
import kotlin.math.roundToInt
import kotlin.reflect.KClass

fun v(c: KClass<out View>, r: () -> Unit = {}) {
    Inkremental.currentMount()?.iterator?.start(c.java, 0)
    r()
    end()
}
inline fun <reified T: View> v(noinline r: () -> Unit = {}) = v(T::class, r)

inline fun <reified T: View, reified S: ViewScope> v(s: S, noinline r: S.() -> Unit = {}) = v(T::class, r.bind(s))

inline fun <reified S: ViewScope>xml(@LayoutRes layoutId: Int, s: S, noinline r: S.() -> Unit = {}) {
    start(layoutId)
    r.bind(s).invoke()
    end()
}

fun xml(@LayoutRes layoutId: Int, r: () -> Unit = {}) {
    start(layoutId)
    r()
    end()
}
fun start(@LayoutRes layoutId: Int) = Inkremental.currentMount()?.iterator?.start(null, layoutId)
fun end() = Inkremental.currentMount()?.iterator?.end()
fun skip() = Inkremental.currentMount()?.iterator?.skip()

fun <T, U> ((T) -> U).bind(value: T): () -> U = { this(value) }

fun <T : Any> attr(name: String, value: T?) {
    Inkremental.currentMount()?.iterator?.attr<T>(name, value)
}

val r: Resources
    get() = Inkremental.currentView<View>()!!.resources

fun dip(value: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, value,
    r.displayMetrics
)

fun dip(value: Int): Int = dip(value.toFloat()).roundToInt()

fun sip(value: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP, value,
    r.displayMetrics
)

fun sip(value: Int): Int = sip(value.toFloat()).roundToInt()

fun withId(@IdRes id: Int, r: () -> Unit): View {
    var v = Inkremental.currentView<View>()
    requireNotNull(v) { "Anvil.currentView() is null" }
    v = v.findViewById(id)
    requireNotNull(v) { "No view found for ID $id" } // TODO convert id to string
    return Inkremental.mount(v, r)
}

val isPortrait: Boolean
    get() = r.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

abstract class RootViewScope {

    val Int.dp : Dip
        get() = Dip(this)

    val Float.sp : Sp
        get() = Sp(this)

    val Int.px : Px
        get() = Px(this)

    val Int.sizeDp : Size.EXACT
        get() = Size.EXACT(this.dp.toPx())

    val Int.sizePx : Size.EXACT
        get() = Size.EXACT(this.px)

    fun Dip.toPx() : Px {
        return Px(dip(this.value))
    }

}

fun renderable(
    context: Context,
    r: () -> Unit
): View = object : RenderableView(context) {
    override var renderable: () -> Unit = {
        r()
    }
}

fun Activity.renderable(
    r: () -> Unit
): View = renderable(this, r)

fun Activity.renderableContentView(
    r: () -> Unit
): View = renderable(r).also { setContentView(it) }

fun Fragment.renderable(
    r: () -> Unit
): View = renderable(if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) context else activity, r)
