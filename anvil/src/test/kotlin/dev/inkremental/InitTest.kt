package dev.inkremental

import android.view.View
import dev.inkremental.dsl.android.init
import dev.inkremental.dsl.android.view.ViewScope
import kotlin.test.*


class InitTest : Utils() {
    var called: MutableMap<String, Boolean?> = mutableMapOf()

    @Test
    fun testInit() {
        println("============================")
        Inkremental.mount(container, {
            //init(makeFunc("once"))
            v<MockView, ViewScope>(ViewScope) { init(makeFunc("setUpView")) }
        })
        println("============================")
        //assertTrue(called["once"]!!)
        assertTrue(called["setUpView"]!!)
        called.clear()
        Inkremental.render()
        //assertFalse(called.containsKey("once"))
        assertFalse(called.containsKey("setUpView"))
    }

    // new function will be created each time, but only the first one should be called
    private fun makeFunc(id: String): (View) -> Unit = {
        if (called.containsKey(id)) {
            throw RuntimeException("Init func called more than once!")
        }
        called[id] = true
    }
}
