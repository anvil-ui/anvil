package dev.inkremental

import android.content.Context
import android.view.View
import android.view.ViewGroup
import kotlin.test.Test
import kotlin.test.assertEquals

class XmlTest : Utils() {
    private var inflateCount = 0
    @Test
    fun testXml() {
        Inkremental.mount(container, Renderable {
            xml(LAYOUT) {
                attr("text", "foo")
                v<MockView> { attr("tag", "bar") }
            }
        })
        assertEquals(1, inflateCount)
        val layout = container.getChildAt(0) as MockLayout
        assertEquals("foo", layout.text)
        assertEquals(1, layout.childCount)
        assertEquals(ID_HEADER, layout.getChildAt(0).id)
        assertEquals("bar", layout.getChildAt(0).tag)
    }

    companion object {
        private const val ID_HEADER = 100
        private const val LAYOUT = 1
    }

    init {
        Inkremental.registerViewFactory(object : Inkremental.ViewFactory {
            override fun fromClass(c: Context?, v: Class<out View?>): View? = null

            override fun fromXml(parent: ViewGroup, xmlId: Int): View? {
                if (xmlId == LAYOUT) {
                    inflateCount++
                    val layout = MockLayout(parent.context)
                    val header = MockView(parent.context)
                    header.id = ID_HEADER
                    layout.addView(header, 0)
                    return layout
                }
                return null
            }
        })
    }
}
