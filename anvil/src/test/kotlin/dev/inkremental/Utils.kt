package dev.inkremental

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import org.junit.After
import org.junit.Before

@TargetApi(Build.VERSION_CODES.KITKAT)
open class Utils {
    var createdViews: MutableMap<Class<*>, Int?> = mutableMapOf()
    var changedAttrs: MutableMap<String, Int?> = mutableMapOf()
    var empty = Renderable {  }
    lateinit var container: MockLayout
    @Before
    fun setUp() {
        changedAttrs.clear()
        createdViews.clear()
        container = MockLayout(context)
    }

    @After
    fun tearDown() {
        Inkremental.unmount(container)
    }

    val context: Context?
        get() = null

    class MockView(c: Context?) : View(c) {
        private var id = 0
        private var tag: Any? = null
        var text: String? = null

        override fun setId(id: Int) {
            this.id = id
        }

        override fun getId(): Int {
            return id
        }

        override fun getTag(): Any {
            return tag!!
        }

        override fun setTag(tag: Any) {
            this.tag = tag
        }

        override fun toString(): String {
            return "MockView$" + this.hashCode()
        }
    }

    open class MockLayout(c: Context?) : FrameLayout(c) {
        private val children: MutableList<View> =
            ArrayList()
        private var id = 0
        private var tag: Any? = null
        var text: String? = null

        override fun getChildCount(): Int {
            return children.size
        }

        override fun getChildAt(index: Int): View {
            return children[index]
        }

        override fun indexOfChild(v: View): Int {
            return children.indexOf(v)
        }

        override fun addView(v: View, index: Int) {
            children.add(index, v)
            super.addView(v, index)
        }

        override fun removeView(v: View?) {
            children.remove(v)
            super.removeView(v)
        }

        override fun removeViews(start: Int, count: Int) {
            if (count > 0) {
                children.subList(start, start + count).clear()
            }
            super.removeViews(start, count)
        }

        override fun setId(id: Int) {
            this.id = id
        }

        override fun getId(): Int {
            return id
        }

        override fun getTag(): Any {
            return tag!!
        }

        override fun setTag(tag: Any) {
            this.tag = tag
        }

        override fun toString(): String {
            return "MockLayout$" + this.hashCode()
        }
    }

    init {
        Inkremental.registerAttributeSetter(object : Inkremental.AttributeSetter<Any> {
            override fun set(v: View, name: String, value: Any?, prevValue: Any?): Boolean {
                changedAttrs[name] = if (!changedAttrs.containsKey(name)) 1 else changedAttrs[name]!! + 1
                return false
            }
        })
        Inkremental.registerViewFactory(object : Inkremental.ViewFactory {
            override fun fromClass(c: Context?, v: Class<out View?>): View? {
                createdViews[v] = if (!createdViews.containsKey(v)) 1 else createdViews[v]!! + 1
                return null
            }
            override fun fromXml(parent: ViewGroup, xmlId: Int): View? = null
        })
    }
}
