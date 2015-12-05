package trikita.anvil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Anvil class is a namespace for top-level static methods and interfaces. Most
 * users would only use it to call {@code Anvil.render()}.
 *
 * Internally, Anvil class defines how Renderables are mounted into ViewGroups
 * and how they are lazily rendered, and this is the key functionality of the
 * Anvil library.
 */
public final class Anvil {

	private final static Map<ViewGroup, Mount> mounts = new WeakHashMap<>();
	private static Mount currentMount = null;

	private static Handler anvilUIHandler = null;

	/** Renderable can be mounted and rendered using Anvil library. */
	public interface Renderable {
		/** This method is a place to define the structure of your layout, its view
		 * properties and data bindings. */
		void view();
	}

	/** AttrFunc replaces the old value with the new value in the given view. */
	public interface AttrFunc<T> {
		void apply(View v, T newValue, T oldValue);
	}

	private Anvil() {}

	private static Runnable anvilRenderRunnable = new Runnable() {
		public void run() {
			Anvil.render();
		}
	};

	/** Starts the new rendering cycle updating all mounted
	 * renderables. Update happens in a lazy manner, only the values that has
	 * been changed since last rendering cycle will be actually updated in the
	 * views. This method can be called from any thread, so it's safe to use
	 * {@code Anvil.render()} in background services. */
	public static void render() {
		synchronized (Anvil.class) {
			// If Anvil.render() is called on a non-UI thread, use UI Handler
			if (Looper.myLooper() != Looper.getMainLooper()) {
				if (anvilUIHandler == null) {
					anvilUIHandler = new Handler(Looper.getMainLooper());
				}
				anvilUIHandler.removeCallbacksAndMessages(null);
				anvilUIHandler.post(anvilRenderRunnable);
				return;
			}
		}
		Set<Mount> keys = new HashSet<>();
		keys.addAll(mounts.values());
		for (Mount m : keys) {
			m.render();
		}
	}

	/**
	 * Mounts a renderable function defining the layout into a ViewGroup. The
	 * ViewGroup is assumed to be empty, so the Renderable would define what its
	 * child views would be.
	 * @param v a ViewGroup into which the renderable r will be mounted
	 * @param r a Renderable to mount into a ViewGroup
	 */
	public static ViewGroup mount(ViewGroup v, Renderable r) {
		Mount m = new Mount(v, r);
		m.render();
		mounts.put(v, m);
		return v;
	}

	/**
	 * Unmounts a  mounted renderable. This would also clean up all the child
	 * views inside the parent ViewGroup, which acted as a mount point.
	 * @param m A mount point to unmount from its ViewGroup
	 */
	public static void unmount(ViewGroup v) {
		Mount m = mounts.get(v);
		if (m != null) {
			mounts.remove(v);
			v.removeViews(0, v.getChildCount());
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
	public static View currentView() {
		if (currentMount() == null) {
			return null;
		} else {
			return currentMount.cache.peek().view;
		}
	}

	/** Mount describes a mount point. Mount point is a Renderable function
	 * attached to some ViewGroup. Mount point keeps track of the virtual layout
	 * declared by Renderable */
	static class Mount {
		private final Renderable renderable;
		private final WeakReference<ViewGroup> rootView;
		private final Deque<Node> cache = new ArrayDeque<>();
		private boolean lock = false;

		/** Node is a virual View declared in the Renderable's code */
		private final static class Node {
			// Cache
			private final List<Node> children = new ArrayList<>();
			private final List<Attr> attrs = new ArrayList<>();
			// Iterator state
			private int attr;
			private int child;
			private View view;

			/** Attr keeps the last known value of a certain attribute and a setter function for it.
			 * When the new value should be set, Attr compares it to the previously
			 * known one and applies the setter function is needed. */
			private final static class Attr<T> {
				private AttrFunc<T> func;
				private T value;
				public Attr<T> apply(View v, AttrFunc<T> func, T value) {
					if (this.func != null && this.func.getClass().equals(func.getClass()) &&
							(this.value == value ||
							 value != null && value.equals(this.value))) {
						return this;
							 }
					T oldValue = this.value;
					this.func = func;
					this.value = value;
					if (oldValue != null && value != null && oldValue.getClass().equals(value.getClass())) {
						this.func.apply(v, value, oldValue);
					} else {
						this.func.apply(v, value, null);
					}
					return this;
				}
			}
		}

		Mount(ViewGroup v, Renderable r) {
			this.renderable = r;
			this.rootView = new WeakReference<>(v);
			cache.push(new Node());
		}

		/** Performs lazy rendering of the Renderable layout mounted into a
		 * ViewGroup */
		void render() {
			if (this.lock) {
				return;
			}
			this.lock = true;
			Mount prevMount = Anvil.currentMount;
			Anvil.currentMount = this;
			if (cache.size() != 1) {
				throw new RuntimeException("Invalid view hierarchy: " + cache.size());
			}
			// cache root node reset
			Node root = cache.peek();
			root.attr = 0;
			root.child = 0;
			root.view = rootView.get();
			if (root.view != null) {
				// build view hiearchy if viewgroup still exists
				this.renderable.view();
			}
			// restore previous mount
			root.view = null;
			Anvil.currentMount = prevMount;
			this.lock = false;
			// assert stack depth is 1
			if (cache.size() != 1) {
				throw new RuntimeException("Invalid view hierarchy: " + cache.size());
			}
		}

		void push(Class<? extends View> viewClass, AttributeSet attrs) {
			Node parentNode = cache.peek();
			int index = parentNode.child;

			ViewGroup vg = (ViewGroup) parentNode.view;
			View childView = null;
			if (index < parentNode.children.size()) {
				childView = vg.getChildAt(index);
			}

			Node childNode;
			if (index < vg.getChildCount()) {
				childNode = parentNode.children.get(index);
			} else {
				childNode = new Node();
				parentNode.children.add(index, childNode);
			}
			childNode.attr = 0;
			childNode.child = 0;
			childNode.view = childView;

			if (childView == null || childView.getClass() != viewClass) {
				try {
					childNode.children.clear();
					childNode.attrs.clear();
					View v = viewClass.getConstructor(Context.class).newInstance(vg.getContext(), attrs);
					if (index < vg.getChildCount()) {
						vg.removeViewAt(index);
					}
					vg.addView(v, index);
					childNode.view = v;
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
			cache.push(childNode);
			parentNode.child++;
		}

		void pop() {
			Node node = cache.pop();
			int size = node.children.size();
			if (node.view instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) node.view;
				if (node.child < node.children.size()) {
					vg.removeViews(node.child, node.children.size() - node.child);
				}
			}
			for (int i = size - 1; i >= node.child; i--) {
				node.children.remove(i);
			}
			size = node.attrs.size();
			for (int i = size - 1; i >= node.attr; i--) {
				node.attrs.remove(i);
			}
			node.view = null;
		}

		<T> void attr(AttrFunc<T> func, T value) {
			Node node = cache.peek();
			if (node.attr < node.attrs.size()) {
				@SuppressWarnings("unchecked")
				Node.Attr<T> attr = node.attrs.get(node.attr);
				attr.apply(node.view, func, value);
			} else {
				node.attrs.add(new Node.Attr<T>().apply(node.view, func, value));
			}
			node.attr++;
		}
	}
}
