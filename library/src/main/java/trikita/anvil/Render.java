package trikita.anvil;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import android.util.TypedValue;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableRow;

public class Render {

	public interface Renderable {
		Node view();
		ViewGroup getRootView();
	}

	public interface AttributeSetter {
		void set(View v);
	}

	public static abstract class SimpleSetter<T> implements AttributeSetter {
		T value;

		public SimpleSetter(T value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return (getClass() == obj.getClass() &&
					((this.value == null && ((SimpleSetter) obj).value == null)
					|| this.value.equals(((SimpleSetter) obj).value)));
		}

		abstract public void set(View v);
	}

	public static class Node {
		protected List<Node> attrs = new ArrayList<Node>();
		protected Class<? extends View> viewClass;
		protected AttributeSetter setter;

		public Node(Class<? extends View> c) {
			this.viewClass = c;
		}

		public Node(AttributeSetter setter) {
			this.setter = setter;
		}
	}

	private static Map<Renderable, Node> mounts = new WeakHashMap<Renderable, Node>();

	public static Node v(final Class<? extends View> cls, final Node ...nodes) {
		return new Node(cls) {{
			for (Node n : nodes) {
				if (n != null) {
					attrs.add(n);
				}
			}
		}};
	}

	//
	// Helpers for android attributes and dimensions
	//
	public static TypedValue attr(Renderable r, int attr) {
		TypedValue tv = new TypedValue();
		r.getRootView().getContext().getTheme().resolveAttribute(attr, tv, true);
		return tv;
	}

	public static int attrPx(Renderable r, int attr) {
		TypedValue tv = attr(r, attr);
		return Math.round(r.getRootView().getResources().getDimension(tv.resourceId));
	}

	public static float dp(Renderable r, float value) {
		DisplayMetrics dm = r.getRootView().getResources().getDisplayMetrics();
    return value * (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT);
	}

	public static int dp(Renderable r, int value) {
		DisplayMetrics dm = r.getRootView().getResources().getDisplayMetrics();
    return Math.round(value * (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	private static boolean isRendering = false;

	public static void render(Renderable r) {
		if (isRendering) {
			return; // Don't render recursively
		}
		isRendering = true;
		Node oldValue = mounts.get(r);
		Node virt = r.view();
		mounts.put(r, virt);

		inflateNode(r.getRootView().getContext(), virt, oldValue, r.getRootView(), 0);

		isRendering = false;
	}

	// Render all mounted views
	public static void render() {
		for (Renderable r: mounts.keySet()) {
			render(r);
		}
	}

	public static View inflateNode(Context c, Node node, Node oldNode, ViewGroup parent, int index) {
		if (node.viewClass == null) {
			throw new RuntimeException("Root is not a view!");
		}
		boolean isNewView = false;
		try {
			int count = parent.getChildCount();
			View oldView = null;
			if (index < count) {
				oldView = parent.getChildAt(index);
			}
			// Reuse view or create a new one
			View v;
			if (oldNode == null || oldNode.viewClass == null || node.viewClass != oldNode.viewClass) {
				isNewView = true;
				v = (View) node.viewClass.getConstructor(Context.class).newInstance(c);
				if (oldView != null) {
					parent.removeViewAt(index); // replace old view
				}
				parent.addView(v, index);
			} else {
				v = oldView;
			}

			int viewIndex = 0;
			for (int i = 0; i < node.attrs.size(); i++) {
				Node subnode = node.attrs.get(i);
				Node oldSubNode =
					(oldNode == null || oldNode.attrs.size() <= i ?  null : oldNode.attrs.get(i));
				if (subnode.setter != null) {
					if (isNewView || oldSubNode == null || subnode.setter.equals(oldSubNode.setter) == false) {
						subnode.setter.set(v);
					}
				} else {
					inflateNode(c, subnode, oldSubNode, (ViewGroup) v, viewIndex);
					viewIndex++;
				}
			}
			// Some views could be removed since last render
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				if (viewIndex != 0 && vg.getChildCount() >= viewIndex) {
					vg.removeViews(viewIndex-1, vg.getChildCount() - viewIndex);
				}
			}
			return v;
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
}
