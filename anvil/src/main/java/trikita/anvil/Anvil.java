package trikita.anvil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.WeakHashMap;

/**
 * Anvil class is a namespace for top-level static methods and interfaces. Most
 * users would only use it to call {@code Anvil.render()}.
 *
 * Internally, Anvil class defines how Renderables are mounted into Views
 * and how they are lazily rendered, and this is the key functionality of the
 * Anvil library.
 */
public final class Anvil {

    private final static Map<View, Mount> mounts = new WeakHashMap<>();
    private static Mount currentMount = null;

    private static Handler anvilUIHandler = null;

    /** Renderable can be mounted and rendered using Anvil library. */
    public interface Renderable {
        /** This method is a place to define the structure of your layout, its view
         * properties and data bindings. */
        void view();
    }

    public interface ViewFactory {
        View fromClass(Context c, Class<? extends View> v);
        View fromXml(Context c, int xmlId);
    }

    private final static List<ViewFactory> viewFactories = new ArrayList<ViewFactory>() {{
        add(new DefaultViewFactory());
    }};

    final static class DefaultViewFactory implements  ViewFactory {
        public View fromClass(Context c, Class<? extends View> viewClass) {
            try {
                return viewClass.getConstructor(Context.class).newInstance(c);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        public View fromXml(Context c, int xmlId) {
            return LayoutInflater.from(c).inflate(xmlId, null, false);
        }
    };

    public static void registerViewFactory(ViewFactory viewFactory) {
        if (!viewFactories.contains(viewFactory)) {
            viewFactories.add(0, viewFactory);
        }
    }

    private Anvil() {}

    private final static Runnable anvilRenderRunnable = new Runnable() {
        public void run() {
            Anvil.render();
        }
    };

    public interface AttributeSetter<T> {
        boolean set(View v, String name, T value, T prevValue);
    }

    private final static List<AttributeSetter> attributeSetters =
            new ArrayList<AttributeSetter>() {{ add(new PropertySetter()); }};

    public static void registerAttributeSetter(AttributeSetter setter) {
        if (!attributeSetters.contains(setter)) {
            attributeSetters.add(0, setter);
        }
    }

    /** Tags: arbitrary data bound to specific views, such as last cached attribute values */
    private static Map<View, Map<String, Object>> tags = new WeakHashMap<>();

    public static void set(View v, String key, Object value) {
        Map<String, Object> attrs = tags.get(v);
        if (attrs == null) {
            attrs = new HashMap<>();
            tags.put(v, attrs);
        }
        attrs.put(key, value);
    }

    public static Object get(View v, String key) {
        Map<String, Object> attrs = tags.get(v);
        if (attrs == null) {
            return null;
        }
        return attrs.get(key);
    }

    /** Starts the new rendering cycle updating all mounted
     * renderables. Update happens in a lazy manner, only the values that has
     * been changed since last rendering cycle will be actually updated in the
     * views. This method can be called from any thread, so it's safe to use
     * {@code Anvil.render()} in background services. */
    public static void render() {
        // If Anvil.render() is called on a non-UI thread, use UI Handler
        if (Looper.myLooper() != Looper.getMainLooper()) {
            synchronized (Anvil.class) {
                if (anvilUIHandler == null) {
                    anvilUIHandler = new Handler(Looper.getMainLooper());
                }
            }
            anvilUIHandler.removeCallbacksAndMessages(null);
            anvilUIHandler.post(anvilRenderRunnable);
            return;
        }
        Set<Mount> set = new HashSet<>();
        set.addAll(mounts.values());
        for (Mount m : set) {
            render(m);
        }
    }

    /**
     * Mounts a renderable function defining the layout into a View. If host is a
     * viewgroup it is assumed to be empty, so the Renderable would define what
     * its child views would be.
     * @param v a View into which the renderable r will be mounted
     * @param r a Renderable to mount into a View
     */
    public static <T extends View> T mount(T v, Renderable r) {
        Mount m = new Mount(v, r);
        mounts.put(v, m);
        render(v);
        return v;
    }

    /**
     * Unmounts a  mounted renderable. This would also clean up all the child
     * views inside the parent ViewGroup, which acted as a mount point.
     * @param v A mount point to unmount from its View
     */
    public static void unmount(View v) {
        unmount(v, true);
    }

    public static void unmount(View v, boolean removeChildren) {
        Mount m = mounts.get(v);
        if (m != null) {
            mounts.remove(v);
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;

                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    unmount(viewGroup.getChildAt(i));
                }
                if (removeChildren) {
                    viewGroup.removeViews(0, childCount);
                }
            }
        }
    }

    /**
     * Returns currently rendered Mount point. Must be called from the
     * Renderable's view() method, otherwise it returns null
     * @return current mount point
     */
    static Mount currentMount() {
        return currentMount;
    }

    /**
     * Returns currently rendered View. It allows to access the real view from
     * inside the Renderable.
     * @return currently rendered View
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T currentView() {
        if (currentMount == null) {
            return null;
        }
        return (T) currentMount.iterator.currentView();
    }

    public static void render(View v) {
        Mount m = mounts.get(v);
        if (m == null) {
            return;
        }
        render(m);
    }

    static void render(Mount m) {
        if (m.lock) {
            return;
        }
        m.lock = true;
        Mount prev = currentMount;
        currentMount = m;
        m.iterator.start();
        if (m.renderable != null) {
            m.renderable.view();
        }
        m.iterator.end();
        currentMount = prev;
        m.lock = false;
    }

    /** Mount describes a mount point. Mount point is a Renderable function
     * attached to some ViewGroup. Mount point keeps track of the virtual layout
     * declared by Renderable */
    static class Mount {
        private boolean lock = false;

        private final WeakReference<View> rootView;
        private final Renderable renderable;

        final Iterator iterator = new Iterator();

        Mount(View v, Renderable r) {
            this.renderable = r;
            this.rootView = new WeakReference<>(v);
        }

        @SuppressLint("Assert")
        class Iterator {
            Deque<View> views = new ArrayDeque<>();
            Deque<Integer> indices = new ArrayDeque<>();

            private void start() {
                assert views.size() == 0;
                assert indices.size() == 0;
                indices.push(0);
                views.push(rootView.get());
            }

            void start(Class<? extends View> c, int layoutId, Object key) {
                assert views.peek() instanceof ViewGroup;
                ViewGroup vg = (ViewGroup) views.peek();
                int i = indices.peek();
                View v = null;
                if (i < vg.getChildCount()) {
                    v = vg.getChildAt(i);
                }
                Context context = rootView.get().getContext();
                if (c != null && (v == null || !v.getClass().equals(c))) {
                    vg.removeView(v);
                    for (ViewFactory vf : viewFactories) {
                        v = vf.fromClass(context, c);
                        if (v != null) {
                            set(v, "_anvil", 1);
                            vg.addView(v, i);
                            break;
                        }
                    }
                } else if (c == null && (v == null || get(v, "_layoutId") != Integer.valueOf(layoutId))) {
                    vg.removeView(v);
                    for (ViewFactory vf : viewFactories) {
                        v = vf.fromXml(context, layoutId);
                        if (v != null) {
                            set(v, "_anvil", 1);
                            set(v, "_layoutId", layoutId);
                            vg.addView(v, i);
                            break;
                        }
                    }
                }
                assert v != null;
                views.push(v);
                indices.push(indices.pop() + 1);
                indices.push(0);
            }

            void end() {
                int index = indices.peek();
                View v = views.peek();
                if (v != null && v instanceof ViewGroup &&
                        get(v, "_layoutId") == null &&
                        (mounts.get(v) == null || mounts.get(v) == Mount.this)) {
                    ViewGroup vg = (ViewGroup) v;
                    if (index < vg.getChildCount()) {
                        vg.removeViews(index, vg.getChildCount() - index);
                    }
                }
                indices.pop();
                views.pop();
            }

            <T> void attr(String name, T value) {
                View currentView = views.peek();
                @SuppressWarnings("unchecked")
                T currentValue = (T) get(currentView, name);
                if (currentValue == null || !currentValue.equals(value)) {
                    for (AttributeSetter setter : attributeSetters) {
                        if (setter.set(currentView, name, value, currentValue)) {
                            set(currentView, name, value);
                            return;
                        }
                    }
                }
            }

            public void skip() {
                int i;
                ViewGroup vg = (ViewGroup) views.peek();
                for (i = indices.pop(); i < vg.getChildCount(); i++) {
                    View v = vg.getChildAt(i);
                    if (get(v, "_anvil") != null) {
                        indices.push(i);
                        return;
                    }
                }
                indices.push(i);
            }

            public View currentView() {
                return views.peek();
            }
        }
    }
}
