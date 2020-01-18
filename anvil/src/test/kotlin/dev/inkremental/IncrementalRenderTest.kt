package dev.inkremental

import kotlin.test.*

class IncrementalRenderTest: Utils() {
    private var fooValue = "a"
    private var showView = true

    @Test
    fun testConstantsRenderedOnce() {
        Anvil.mount(container, Renderable { v<MockLayout> { attr("text", "bar") } })
        assertEquals(1, createdViews[MockLayout::class.java])
        assertEquals(1, changedAttrs["text"])
        Anvil.render()
        assertEquals(1, createdViews[MockLayout::class.java])
        assertEquals(1, changedAttrs["text"])
    }

    @Test
    fun testDynamicAttributeRenderedLazily() {
        Anvil.mount(container, Renderable { v<MockLayout> { attr("text", fooValue) } })
        assertEquals(1, changedAttrs["text"])
        Anvil.render()
        assertEquals(1, changedAttrs["text"])
        fooValue = "b"
        Anvil.render()
        assertEquals(2, changedAttrs["text"])
        Anvil.render()
        assertEquals(2, changedAttrs["text"])
    }

    @Test
    fun testDynamicViewRenderedLazily() {
        Anvil.mount(container, Renderable {
            v<MockLayout> {
                v<MockLayout>()
                if(showView) {
                    v<MockView>()
                }
            }
        })
        val layout = container.getChildAt(0) as MockLayout
        assertEquals(2, layout.childCount)
        assertEquals(1, createdViews[MockView::class.java])
        Anvil.render()
        assertEquals(1, createdViews[MockView::class.java])
        showView = false
        Anvil.render()
        assertEquals(1, layout.getChildCount())
        assertEquals(1, createdViews[MockView::class.java])
        Anvil.render()
        assertEquals(1, createdViews[MockView::class.java])
        showView = true
        Anvil.render()
        assertEquals(2, layout.getChildCount())
        assertEquals(2, createdViews[MockView::class.java])
        Anvil.render()
        assertEquals(2, createdViews[MockView::class.java])
    }

    private var firstMountValue = "foo"
    private var secondMountValue = "bar"

    @Test
    fun testRenderUpdatesAllMounts() {
        val rootA = MockLayout(context)
        val rootB = MockLayout(context)
        Anvil.mount(rootA, Renderable { attr("text", firstMountValue) })
        Anvil.mount(rootB, Renderable { attr("tag", secondMountValue) })
        assertEquals("foo", rootA.text)
        assertEquals("bar", rootB.tag)

        firstMountValue = "baz"
        secondMountValue = "qux"
        Anvil.render()

        assertEquals("baz", rootA.text)
        assertEquals("qux", rootB.tag)
    }
}
