package trikita.anvil

import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.reflect.KClass

fun v(c: KClass<out View>, r: () -> Unit = {}) {
    Anvil.currentMount().iterator.start(c.java, 0)
    r()
    end()
}
inline fun <reified T: View> v(noinline r: () -> Unit = {}) = v(T::class, r)

inline fun <reified T: View, reified S: ViewScope> v(s: S, noinline r: S.() -> Unit = {}) = v(T::class, r.bind(s))

fun xml(@LayoutRes layoutId: Int, r: () -> Unit = {}) {
    Anvil.currentMount().iterator.start(null, layoutId)
    r()
    end()
}
fun end() = Anvil.currentMount().iterator.end()
fun skip() = Anvil.currentMount().iterator.skip()

fun <T, U> ((T) -> U).bind(value: T): () -> U = { this(value) }

fun <T> attr(name: String, value: T?) {
    Anvil.currentMount().iterator.attr<T>(name, value)
}

val r: Resources
    get() = Anvil.currentView<View>()!!.resources

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
    var v = Anvil.currentView<View>()
    requireNotNull(v) { "Anvil.currentView() is null" }
    v = v.findViewById(id)
    requireNotNull(v) { "No view found for ID $id" } // TODO convert id to string
    return Anvil.mount(v, r)
}

val isPortrait: Boolean
    get() = r.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
