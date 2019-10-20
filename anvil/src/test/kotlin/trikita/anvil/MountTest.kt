package trikita.anvil

import org.mockito.Mockito
import trikita.anvil.Anvil.Renderable
import java.lang.ref.WeakReference
import kotlin.test.*

class MountTest : Utils() {
    var testLayout = TestRenderable()

    @Test
    fun testMountReturnsMountPoint() {
        assertEquals(container, Anvil.mount(container, empty))
    }

    @Test
    fun testMountRendersViews() {
        Anvil.mount(container, testLayout)
        assertEquals(1, container!!.childCount)
        assertTrue(container!!.getChildAt(0) is MockView)
        val v = container!!.getChildAt(0) as MockView
        assertEquals("bar", v.text)
    }

    @Test
    fun testUnmountRemovesViews() {
        Anvil.mount(container, testLayout)
        assertEquals(1, container!!.childCount)
        Anvil.unmount(container)
        assertEquals(0, container!!.childCount)
    }

    @Test
    fun testMountReplacesViews() {
        Anvil.mount(container, testLayout)
        assertEquals(1, container!!.childCount)
        Anvil.mount(container, empty)
        assertEquals(0, container!!.childCount)
        Anvil.mount(container, testLayout)
        assertEquals(1, container!!.childCount)
    }

    @Test
    fun testMountInfoView() {
        val v = Anvil.mount(MockView(context)) {
            attr("id", 100)
            attr("text", "bar")
            attr("tag", "foo")
        }
        assertEquals(100, v.id)
        assertEquals("foo", v.tag)
        assertEquals("bar", v.text)
    }

    @Test
    fun testMountGC() {
        val layout = Mockito.spy(testLayout)
        Anvil.mount(container, layout)
        Mockito.verify(layout, Mockito.times(1)).view()
        assertEquals(1, container!!.childCount)
        // Once the container is garbage collection all other views should be removed, too
        val ref = WeakReference(container!!.getChildAt(0))
        container = null
        System.gc()
        assertEquals(null, ref.get())
        // Ensure that the associated renderable is no longer called
        Anvil.render()
        Mockito.verify(layout, Mockito.times(1)).view()
    }
}

open class TestRenderable : Renderable {
    override fun view() {
        v<Utils.MockView, ViewScope>(ViewScope) { attr("text", "bar") }
    }
}
