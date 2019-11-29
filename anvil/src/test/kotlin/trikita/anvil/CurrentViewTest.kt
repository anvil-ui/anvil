package trikita.anvil

import android.view.View
import android.view.ViewGroup
import kotlin.test.*


class CurrentViewTest : Utils() {
    @Test
    fun testCurrentView() {
        assertNull(Anvil.currentView())
        Anvil.mount(container) {
            assertTrue(Anvil.currentView<View>() is ViewGroup)
            v<MockLayout, ViewGroupScope>(ViewGroupScope) {
                assertTrue(Anvil.currentView<View>() is MockLayout)
                v<MockView, ViewScope>(ViewScope) {
                    assertTrue(Anvil.currentView<View>() is MockView)
                    attr("text", "bar")
                    val view: MockView = Anvil.currentView() // should cast automatically
                    assertEquals("bar", view.text)
                }
                assertTrue(Anvil.currentView<View>() is MockLayout)
            }
            assertTrue(Anvil.currentView<View>() is ViewGroup)
        }
        assertNull(Anvil.currentView())
    }
}
