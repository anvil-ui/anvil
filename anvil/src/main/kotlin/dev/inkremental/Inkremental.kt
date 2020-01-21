package dev.inkremental

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * Anvil class is a namespace for top-level static methods and interfaces. Most
 * users would only use it to call `Anvil.render()`.
 *
 * Internally, Anvil class defines how [Renderable] are mounted into Views
 * and how they are lazily rendered, and this is the key functionality of the
 * Anvil library.
 */
object Inkremental {
    private val mounts: MutableMap<View, Mount> = WeakHashMap()
    private var currentMount: Mount? = null
    private var anvilUIHandler: Handler? = null
    private val viewFactories: MutableList<ViewFactory> = mutableListOf<ViewFactory>().apply {
        add(DefaultViewFactory())
    }

    fun registerViewFactory(viewFactory: ViewFactory) {
        if (!viewFactories.contains(viewFactory)) {
            viewFactories.add(0, viewFactory)
        }
    }

    private val anvilRenderRunnable = Runnable { render() }
    private val attributeSetters: MutableList<AttributeSetter<Any>> = mutableListOf<AttributeSetter<Any>>().apply {
        add(PropertySetter())
    }

    fun registerAttributeSetter(setter: AttributeSetter<Any>) {
        if (!attributeSetters.contains(setter)) {
            attributeSetters.add(0, setter)
        }
    }

    /** Tags: arbitrary data bound to specific views, such as last cached attribute values  */
    private val tags: MutableMap<View, MutableMap<String, Any?>> = WeakHashMap()

    operator fun set(v: View, key: String, value: Any?) {
        var attrs = tags[v]
        if (attrs == null) {
            attrs = HashMap()
            tags[v] = attrs
        }
        attrs[key] = value
    }

    operator fun get(v: View?, key: String): Any? {
        val attrs = tags[v] ?: return null
        return attrs[key]
    }

    /** Starts the new rendering cycle updating all mounted
     * renderables. Update happens in a lazy manner, only the values that has
     * been changed since last rendering cycle will be actually updated in the
     * views. This method can be called from any thread, so it's safe to use
     * `Anvil.render()` in background services.  */
    fun render() { // If Anvil.render() is called on a non-UI thread, use UI Handler
        if (Looper.myLooper() != Looper.getMainLooper()) {
            synchronized(Inkremental::class.java) {
                if (anvilUIHandler == null) {
                    anvilUIHandler = Handler(Looper.getMainLooper())
                }
            }
            anvilUIHandler?.removeCallbacksAndMessages(null)
            anvilUIHandler?.post(anvilRenderRunnable)
            return
        }
        val set: MutableSet<Mount> = mutableSetOf()
        set.addAll(mounts.values)
        for (m in set) {
            render(m)
        }
    }

    /**
     * Mounts a renderable function defining the layout into a View. If host is a
     * viewgroup it is assumed to be empty, so the Renderable would define what
     * its child views would be.
     * @param v a View into which the renderable r will be mounted
     * @param r a Renderable to mount into a View
     */
    fun <T : View> mount(v: T, r: Renderable): T {
        val m = Mount(v, r)
        mounts[v] = m
        render(v)
        return v
    }

    /**
     * Unmounts a  mounted renderable. This would also clean up all the child
     * views inside the parent ViewGroup, which acted as a mount point.
     * @param v A mount point to unmount from its View
     */
    @JvmOverloads
    fun unmount(v: View?, removeChildren: Boolean = true) {
        val m = mounts[v]
        if (m != null) {
            mounts.remove(v)
            if (v is ViewGroup) {
                val viewGroup = v
                val childCount = viewGroup.childCount
                for (i in 0 until childCount) {
                    unmount(viewGroup.getChildAt(i))
                }
                if (removeChildren) {
                    viewGroup.removeViews(0, childCount)
                }
            }
        }
    }

    /**
     * Returns currently rendered Mount point. Must be called from the
     * Renderable's view() method, otherwise it returns null
     * @return current mount point
     */
    fun currentMount(): Mount? {
        return currentMount
    }

    /**
     * Returns currently rendered View. It allows to access the real view from
     * inside the Renderable.
     * @return currently rendered View
     */
    fun <T : View?> currentView(): T? {
        return if (currentMount == null) {
            null
        } else currentMount?.iterator?.currentView() as T?
    }

    fun render(v: View?) {
        val m = mounts[v] ?: return
        render(m)
    }

    fun render(m: Mount) {
        if (m.lock) {
            return
        }
        m.lock = true
        val prev = currentMount
        currentMount = m
        m.iterator.start()
        if (m.renderable != null) {
            m.renderable.view()
        }
        m.iterator.end()
        currentMount = prev
        m.lock = false
    }

    //waiting for Kotlin 1.4
//    /** Renderable can be mounted and rendered using Anvil library.  */
//    interface Renderable {
//        /** This method is a place to define the structure of your layout, its view
//         * properties and data bindings.  */
//        fun view()
//    }

    interface ViewFactory {
        fun fromClass(c: Context?, v: Class<out View?>): View?
        fun fromXml(parent: ViewGroup, xmlId: Int): View?
    }

    internal class DefaultViewFactory : ViewFactory {
        override fun fromClass(c: Context?, viewClass: Class<out View?>): View? {
            return try {
                viewClass.getConstructor(Context::class.java).newInstance(c)
            } catch (e: InvocationTargetException) {
                throw RuntimeException(e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException(e)
            } catch (e: InstantiationException) {
                throw RuntimeException(e)
            }
        }

        override fun fromXml(parent: ViewGroup, xmlId: Int): View? {
            return LayoutInflater.from(parent.context).inflate(xmlId, parent, false)
        }
    }

    interface AttributeSetter<T> {
        operator fun set(v: View, name: String, value: T?, prevValue: T?): Boolean
    }

    /** Mount describes a mount point. Mount point is a Renderable function
     * attached to some ViewGroup. Mount point keeps track of the virtual layout
     * declared by Renderable  */
    class Mount(v: View, val renderable: Renderable?) {
        var lock = false
        private val rootView: WeakReference<View> = WeakReference(v)
        internal val iterator = Iterator()

        @SuppressLint("Assert")
        internal inner class Iterator {
            var views: Deque<View?> = ArrayDeque()
            var indices: Deque<Int> = ArrayDeque()
            fun start() {
                assert(views.size == 0)
                assert(indices.size == 0)
                indices.push(0)
                val v = rootView.get()
                if (v != null) {
                    views.push(v)
                }
            }

            fun start(c: Class<out View?>?, layoutId: Int) {
                val i = indices.peek()
                val parentView = (views.peek() ?: return) as? ViewGroup
                        ?: throw RuntimeException("child views are allowed only inside view groups")
                val vg = parentView
                var v: View? = null
                if (i < vg.childCount) {
                    v = vg.getChildAt(i)
                }
                val context = rootView.get()!!.context
                if (c != null && (v == null || v.javaClass != c)) {
                    vg.removeView(v)
                    for (vf in viewFactories) {
                        v = vf.fromClass(context, c)
                        if (v != null) {
                            set(v, "_anvil", 1)
                            vg.addView(v, i)
                            break
                        }
                    }
                } else if (c == null && (v == null || Integer.valueOf(layoutId) != get(v, "_layoutId"))) {
                    vg.removeView(v)
                    for (vf in viewFactories) {
                        v = vf.fromXml(vg, layoutId)
                        if (v != null) {
                            set(v, "_anvil", 1)
                            set(v, "_layoutId", layoutId)
                            vg.addView(v, i)
                            break
                        }
                    }
                }
                assert(v != null)
                views.push(v)
                indices.push(indices.pop() + 1)
                indices.push(0)
            }

            fun end() {
                val index = indices.peek()
                val v = views.peek()
                if (v != null && v is ViewGroup && get(v, "_layoutId") == null &&
                        (mounts[v] == null || mounts[v] === this@Mount)) {
                    val vg = v
                    if (index < vg.childCount) {
                        removeNonAnvilViews(vg, index, vg.childCount - index)
                    }
                }
                indices.pop()
                if (v != null) {
                    views.pop()
                }
            }

            fun <T : Any> attr(name: String, value: T?) {
                val currentView = views.peek() ?: return
                val currentValue = get(currentView, name) as T?
                if (currentValue == null || currentValue != value) {
                    for (setter in attributeSetters) {
                        if (setter.set(currentView, name, value, currentValue)) {
                            set(currentView, name, value)
                            return
                        }
                    }
                }
            }

            private fun removeNonAnvilViews(vg: ViewGroup, start: Int, count: Int) {
                val end = start + count - 1
                for (i in end downTo start) {
                    val v = vg.getChildAt(i)
                    if (get(v, "_anvil") != null) {
                        vg.removeView(v)
                    }
                }
            }

            fun skip() {
                var i: Int
                val vg = views.peek() as ViewGroup?
                i = indices.pop()
                while (i < vg!!.childCount) {
                    val v = vg.getChildAt(i)
                    if (get(v, "_anvil") != null) {
                        indices.push(i)
                        return
                    }
                    i++
                }
                indices.push(i)
            }

            fun currentView(): View? {
                return views.peek()
            }
        }

    }
}