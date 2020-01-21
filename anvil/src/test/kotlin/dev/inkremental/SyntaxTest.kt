package dev.inkremental

import dev.inkremental.dsl.android.view.ViewScope
import org.junit.Assert
import kotlin.test.Test

class SyntaxTest : Utils() {
    private val showNestedLayout = true
    private val numberOfChildViews = 5

    @Test
    fun testSyntax() {
        Inkremental.mount(container, Renderable {
            v<MockLayout> {
                attr("tag", 1)
                v<MockView> { attr("tag", 2) }
                if (showNestedLayout) {
                    v<MockLayout> {
                        for (i in 0 until numberOfChildViews) {
                            v<MockView> { attr("tag", i) }
                        }
                    }
                }
            }
        })
        val layout = container.getChildAt(0) as MockLayout
        Assert.assertEquals(1, layout.tag)
        val header = layout.getChildAt(0) as MockView
        Assert.assertEquals(2, header.tag)
        val content = layout.getChildAt(1) as MockLayout
        for (i in 0 until numberOfChildViews) {
            val v = content.getChildAt(i) as MockView
            Assert.assertEquals(i, v.tag)
        }
    }

    private fun childViews(count: Int) {
        repeat(numberOfChildViews) {
            v<MockView, ViewScope>(ViewScope) { tag(it) }
        }
    }
}
