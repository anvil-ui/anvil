package trikita.anvil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Anvil {

	private Anvil() {}

	private final static List<Mount> mounts = new ArrayList<>();

	public static View currentView() {
		// TODO: null-safety
		return DSL.current.cache.peek().view;
	}

	private static Runnable anvilRenderRunnable = new Runnable() {
		public void run() {
			Anvil.render();
		}
	};

	private static Handler anvilUIHandler = null;

	public static void render() {
		// If Anvil.render() is called on a non-UI thread, use UI Handler
		if (Looper.myLooper() != Looper.getMainLooper()) {
			if (anvilUIHandler == null) {
				anvilUIHandler = new Handler(Looper.getMainLooper());
			}
			anvilUIHandler.removeCallbacksAndMessages(null);
			anvilUIHandler.post(anvilRenderRunnable);
			return;
		}
		Set<Mount> keys = new HashSet<>();
		keys.addAll(mounts);
		for (Mount m : keys) {
			m.render();
		}
	}

	public static Mount mount(ViewGroup v, Renderable r) {
		Mount m = new Mount(v, r);
		m.render();
		mounts.add(m);
		return m;
	}

	public static void unmount(Mount m) {
		m.cleanup();
		mounts.remove(m);
	}

	private final static class Attr<T> {
		private AttrFunc<T> func;
		private T value;
		public Attr<T> apply(View v, AttrFunc<T> func, T value) {
			if (this.func != null && this.func.getClass().equals(func.getClass()) &&
					(this.value == value ||
					 (value != null && value.equals(this.value)))) {
				return this;
			}
			T oldValue = this.value;
			this.func = func;
			this.value = value;
			// FIXME need to double-check the null-safety
			if (oldValue == null || !oldValue.getClass().equals(value.getClass())) {
				this.func.apply(v, value, null);
			} else {
				this.func.apply(v, value, oldValue);
			}
			return this;
		}
	}

	public static class Mount {
		private final Renderable view;
		private Deque<Node> cache = new ArrayDeque<>();
		private boolean lock = false;

		private final static class Node {
			// Cache
			private final List<Node> children = new ArrayList<Node>();
			private final List<Attr> attrs = new ArrayList<Attr>();
			// Iterator state
			private int attr;
			private int child;
			private View view;
		}

		public Mount(ViewGroup v, Renderable r) {
			this.view = r;
			cache.push(new Node());
			cache.peek().view = v;
		}

		public void render() {
			if (this.lock) {
				return;
			}
			this.lock = true;
			Mount prev = DSL.current;
			DSL.current = this;
			if (cache.size() != 1) {
				throw new RuntimeException("Invalid view hierarchy: " + cache.size());
			}
			// cache root node reset
			Node root = cache.peek();
			root.attr = 0;
			root.child = 0;
			// build view hiearchy
			this.view.view();
			// assert stack depth is 1
			if (cache.size() != 1) {
				throw new RuntimeException("Invalid view hierarchy: " + cache.size());
			}
			DSL.current = prev;
			this.lock = false;
		}

		private void cleanup() {
			// TODO remove all child nodes and clear cache's root child nodes and attrs
		}

		void push(Class<? extends View> viewClass) {
			Node parentNode = cache.peek();
			int index = parentNode.child;

			Node childNode;
			if (index < parentNode.children.size()) {
				childNode = parentNode.children.get(index);
			} else {
				childNode = new Node();
				parentNode.children.add(index, childNode);
			}
			childNode.attr = 0;
			childNode.child = 0;

			ViewGroup vg = (ViewGroup) parentNode.view;
			View childView = null;
			if (index < vg.getChildCount()) {
				childView = vg.getChildAt(index);
				childNode.view = childView;
			}
			if (childView == null || childView.getClass() != viewClass) {
				try {
					childNode.children.clear();
					childNode.attrs.clear();
					Context c = cache.peekLast().view.getContext();
					View v = (View) viewClass.getConstructor(Context.class).newInstance(c);
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
				vg.removeViews(node.child, vg.getChildCount() - node.child);
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
				node.attrs.get(node.attr).apply(node.view, func, value);
			} else {
				node.attrs.add(new Attr().apply(node.view, func, value));
			}
			node.attr++;
		}
	}
}
