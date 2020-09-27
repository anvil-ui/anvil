package dev.inkremental.sample

import android.view.View
import dev.inkremental.Inkremental
import dev.inkremental.dsl.android.init
import dev.inkremental.dsl.android.view.ViewScope

inline fun <T : View> ViewScope.initWith(crossinline viewInit: T.() -> Unit) {
    init {
        val v = Inkremental.currentView<T>()
        v?.viewInit()
    }
}