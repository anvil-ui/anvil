package trikita.v;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Render {

	interface Renderable {
		Node view();
		ViewGroup getRootView();
	}

	interface AttributeSetter {
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
		List<Node> attrs = new ArrayList<Node>();
		Class<? extends View> viewClass;
		AttributeSetter setter;

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

	public static void render(Renderable r) {
		Node oldValue = mounts.get(r);

		Node virt = r.view();
		mounts.put(r, virt);

		int count = r.getRootView().getChildCount();
		View oldView = null;
		if (count > 0) {
			oldView = r.getRootView().getChildAt(0);
		}
		View v = inflateNode(r.getRootView().getContext(), virt, oldValue, oldView);

		// if inflate returned a different view - add it to the parent
		if (count == 0) {
			r.getRootView().addView(v); // New view
		} else if (count > 0 && r.getRootView().getChildAt(0) != v) {
			r.getRootView().removeAllViews();
			r.getRootView().addView(v); // Old view has been changed
		}
	}

	// Render all mounted views
	public static void render() {
		for (Renderable r: mounts.keySet()) {
			render(r);
		}
	}

	public static View inflateNode(Context c, Node node, Node oldNode, View oldView) {
		if (node.viewClass == null) {
			throw new RuntimeException("Root is not a view!");
		}
		boolean isNewView = false;
		try {
			// Reuse view or create a new one
			View v;
			if (oldNode == null || oldNode.viewClass == null || node.viewClass != oldNode.viewClass) {
				isNewView = true;
				v = (View) node.viewClass.getConstructor(Context.class).newInstance(c);
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
					ViewGroup vg = (ViewGroup) v;
					View oldSubView = null;
					if (vg.getChildCount() > viewIndex) {
						oldSubView = vg.getChildAt(viewIndex);
					}
					View subview = inflateNode(c, subnode, oldSubNode, oldSubView);
					if (subview != oldSubView) {
						if (vg.getChildCount() > viewIndex) {
							vg.removeViewAt(viewIndex);
						}
						vg.addView(subview, viewIndex);
					}
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
