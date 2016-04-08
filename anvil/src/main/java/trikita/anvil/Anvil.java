package trikita.anvil;

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

	/** AttrFunc replaces the old value with the new value in the given view. */
	public interface AttrFunc<T> {
		void apply(View v, T newValue, T oldValue);
	}

	interface ViewFactory {
		View fromClass(Context c, Class<? extends View> v);
		View fromXml(Context c, int xmlId);
		View fromId(View v, int viewId);
	}

	final static ViewFactory viewFactory = new ViewFactory() {
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
		public View fromId(View v, int viewId) {
			return v.findViewById(viewId);
		}
	};

	private Anvil() {}

	private final static Runnable anvilRenderRunnable = new Runnable() {
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
		Set<Mount> keys = new HashSet<>();
		keys.addAll(mounts.values());
		for (Mount m : keys) {
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
		Mount m = mounts.get(v);
		if (m == null) {
			m = new Mount(v, r);
			mounts.put(v, m);
		} else {
			m.setRenderable(r);
		}
		render(m);
		return v;
	}

	/**
	 * Unmounts a  mounted renderable. This would also clean up all the child
	 * views inside the parent ViewGroup, which acted as a mount point.
	 * @param v A mount point to unmount from its View
	 */
	public static void unmount(View v) {
		Mount m = mounts.get(v);
		if (m != null) {
			mounts.remove(v);
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				vg.removeViews(0, vg.getChildCount());
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
		if (currentMount() == null) {
			return null;
		}
		Node node = currentMount.stack.peek();
		return (T) (node == null ? currentMount.rootView.get() : node.view);
	}

	static void render(Mount m) {
		if (m.lock) {
			return;
		}
		m.lock = true;
		Mount prev = Anvil.currentMount;
		Anvil.currentMount = m;
		m.render();
		Anvil.currentMount = prev;
		m.lock = false;
	}

	/** Mount describes a mount point. Mount point is a Renderable function
	 * attached to some ViewGroup. Mount point keeps track of the virtual layout
	 * declared by Renderable */
	static class Mount {
		private boolean lock = false;

		private final WeakReference<View> rootView;
		private final Node rootNode = new Node();
		private final Deque<Node> stack = new ArrayDeque<>();

		private Renderable renderable;

		Mount(View v, Renderable r) {
			this.renderable = r;
			this.rootView = new WeakReference<>(v);
		}

		void setRenderable(Renderable r) {
			this.renderable = r;
		}

		void render() {
			assert this.stack.size() == 0;
			this.stack.push(this.rootNode.reset());
			// render view hierarchy if viewgroup still exists
			if ((this.rootNode.view = this.rootView.get()) != null) {
				this.renderable.view();
			}
			end();
			this.rootNode.view = null;
			assert this.stack.size() == 0;
		}

		Node startNode() {
			Node parent = this.stack.peek();
			parent.childIndex++;
			int i = parent.childIndex;
			if (i >= parent.children.size()) {
				parent.children.add(new Node());
			}
			Node node = parent.children.get(i).reset();
			ViewGroup vg = (ViewGroup) parent.view;
			node.parentView = vg;
			if (node.viewIndex >= 0 && node.viewIndex < vg.getChildCount()) {
				node.view = vg.getChildAt(node.viewIndex);
			}
			this.stack.push(node);
			return node;
		}

		// Create/replace view object from the given view class
		void startFromClass(Class<? extends View> viewClass) {
			Node node = startNode();
			View view = node.view;
			if (node.viewClass != viewClass) {
				node.layoutId = 0;
				node.viewClass = viewClass;
				node.children.clear();
				node.attrs.clear();
				if (view != null) {
					node.parentView.removeView(view);
				}
				View v = viewFactory.fromClass(node.parentView.getContext(), viewClass);
				if (node.viewIndex == -1) {
					node.viewIndex = node.parentView.getChildCount();
				}
				node.parentView.addView(v, node.viewIndex);
				node.view = v;
			}
		}

		// Create/replace view from XML resource
		void startFromLayout(int layoutId) {
			Node node = startNode();
			if (node.layoutId != layoutId) {
				node.layoutId = layoutId;
				node.viewClass = null;
				node.children.clear();
				node.attrs.clear();
				if (node.view != null) {
					node.parentView.removeView(node.view);
				}
				View v = viewFactory.fromXml(node.parentView.getContext(), layoutId);
				if (node.viewIndex == -1) {
					node.viewIndex = node.parentView.getChildCount();
				}
				node.parentView.addView(v, node.viewIndex);
				node.view = v;
			}
		}

		void end() {
			Node node = stack.pop();
			int size = node.children.size();
			if (node.view instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) node.view;
				if (node.childIndex < size - 1) {
					vg.removeViews(node.childIndex + 1, size - node.childIndex - 1);
					node.children.subList(node.childIndex + 1, size).clear();
				}
			}
			if (node.attrIndex < node.attrs.size() - 1) {
				node.attrs.subList(node.attrIndex + 1, node.attrs.size()).clear();
			}
			node.view = null;
			node.parentView = null;
		}

		<T> void attr(AttrFunc<T> func, T value) {
			Node node = this.stack.peek();
			node.attrIndex++;
			if (node.attrIndex < node.attrs.size()) {
				@SuppressWarnings("unchecked")
				Attr<T> attr = node.attrs.get(node.attrIndex);
				attr.apply(node.view, func, value);
			} else {
				node.attrs.add(new Attr<T>().apply(node.view, func, value));
			}
		}
	}

	/** Node is a virtual View declared in the Renderable's code */
	private final static class Node {
		// Tree-like hierarchy of nodes
		private final List<Node> children = new ArrayList<>();
		private final List<Attr> attrs = new ArrayList<>();

		// Index of the real view inside the parent viewgroup
		private int viewIndex = -1;

		// view class or layout id given when the node was last updated
		private Class<? extends View> viewClass;
		private int layoutId;

		// Iterator state
		private int attrIndex = -1;
		private int childIndex = -1;

		// View cache (valid only as long as the view is on the stack)
		private View view;
		private ViewGroup parentView;

		Node reset() {
			this.attrIndex = this.childIndex = -1;
			this.view = this.parentView = null;
			return this;
		}
	}

	/** Attr keeps the last known value of a certain attribute and a setter function for it.
	 * When the new value should be set, Attr compares it to the previously
	 * known one and applies the setter function is needed. */
	private final static class Attr<T> {
		private AttrFunc<T> func;
		private T value;
		public Attr<T> apply(View v, AttrFunc<T> func, T value) {
			if (this.func != null && this.func.getClass().equals(func.getClass()) &&
					(this.value == value || value != null && value.equals(this.value))) {
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
