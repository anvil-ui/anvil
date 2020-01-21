package dev.inkremental

import android.view.View
import android.view.ViewGroup
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import kotlin.test.*


class CurrentViewTest : Utils() {
    @Test
    fun testCurrentView() {
        assertNull(Inkremental.currentView())
        Inkremental.mount(container, {
            assertTrue(Inkremental.currentView<View>() is ViewGroup)
            v<MockLayout, FrameLayoutScope>(FrameLayoutScope) {
                assertTrue(Inkremental.currentView<View>() is MockLayout)
                v<MockView, ViewScope>(ViewScope) {
                    assertTrue(Inkremental.currentView<View>() is MockView)
                    attr("text", "bar")
                    val view: MockView? = Inkremental.currentView<MockView>()// should cast automatically
                    assertEquals("bar", view?.text)
                }
                assertTrue(Inkremental.currentView<View>() is MockLayout)
            }
            assertTrue(Inkremental.currentView<View>() is ViewGroup)
        })
        assertNull(Inkremental.currentView())
    }
}
