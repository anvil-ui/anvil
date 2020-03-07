package dev.inkremental

import dev.inkremental.dsl.android.view.ViewScope
import org.mockito.Mockito
import java.lang.ref.WeakReference
import kotlin.test.*

class MountTest : Utils() {
    var testLayout = TestRenderable
    var mountContainer : MockLayout = MockLayout(context)

    @Test
    fun testMountReturnsMountPoint() {
        assertEquals(container, Inkremental.mount(container, empty))
    }

    @Test
    fun testMountRendersViews() {
        Inkremental.mount(container, testLayout)
        assertEquals(1, container.childCount)
        assertTrue(container.getChildAt(0) is MockView)
        val v = container.getChildAt(0) as MockView
        assertEquals("bar", v.text)
    }

    @Test
    fun testUnmountRemovesViews() {
        Inkremental.mount(container, testLayout)
        assertEquals(1, container.childCount)
        Inkremental.unmount(container)
        assertEquals(0, container.childCount)
    }

    @Test
    fun testMountReplacesViews() {
        Inkremental.mount(container, testLayout)
        assertEquals(1, container.childCount)
        Inkremental.unmount(container)
        Inkremental.mount(container, empty)
        assertEquals(0, container.childCount)
        Inkremental.unmount(container)
        Inkremental.mount(container, testLayout)
        assertEquals(1, container.childCount)
        Inkremental.unmount(container)
    }

    @Test
    fun testMountInfoView() {
        val v = Inkremental.mount(MockView(context)) {
            attr("id", 100)
            attr("text", "bar")
            attr("tag", "foo")
        }
        assertEquals(100, v.id)
        assertEquals("foo", v.tag)
        assertEquals("bar", v.text)
    }

    @Ignore("Don't know how to empty non-null reference yet")
    @Test
    fun testMountGC() {
        val layout = Mockito.spy(testLayout)
        Inkremental.mount(mountContainer, layout)
        Mockito.verify(layout, Mockito.times(1)).invoke()
        assertEquals(1, mountContainer.childCount)
        // Once the container is garbage collection all other views should be removed, too
        val ref = WeakReference(mountContainer.getChildAt(0))
//        mountContainer = null
        System.gc()
        assertEquals(null, ref.get())
        // Ensure that the associated renderable is no longer called
        Inkremental.render()
        Mockito.verify(layout, Mockito.times(1)).invoke()
    }
}

val TestRenderable =  {
    v<Utils.MockView, ViewScope>(ViewScope) { attr("text", "bar") }
}
