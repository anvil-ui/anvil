package trikita.anvil;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Render {

	public interface Renderable {
		ViewNode view();
		ViewGroup getRootView();
	}

	//
	// Little trick to get type-safety.
	// T here is "View" if node is applicable to views and viewgroups
	// T here is "ViewGroup" if node is applicable to viewgroups only
	// Then v(Class<T>, INode<? super T>...) will:
	// - accept View class and INode<View> nodes
	// - accept ViewGroup class and INode<View> nodes
	// - accept ViewGroup class and INode<ViewGroup> nodes
	// - not accept View class and INode<ViewGroup> nodes (ViewGroup is not super of View)
	//
	public interface INode<T> {} // Node type signature

	// Nested views can be used with viewgroups only
	public static class ViewNode implements INode<ViewGroup> {
		Class<? extends View> viewClass;
		List<ViewNode> children = new ArrayList<ViewNode>();
		List<AttrNode> attrs = new ArrayList<AttrNode>();

		public ViewNode(Class<? extends View> c) {
			this.viewClass = c;
		}
	}

	// Attributes can be used with both views and viewgroups
	public static interface AttrNode extends INode<View> {
		void apply(View v);
	}

	// Map of active renderables and their actual view nodes
	private static Map<Renderable, ViewNode> mounts = new WeakHashMap<Renderable, ViewNode>();

	// v() method for viewgroups accepts both attributes and child view nodes
	// v() method for views accepts attributes only
	public static <T extends View> ViewNode v(Class<T> c, INode<? super T> ...args) {
		ViewNode node = new ViewNode(c);
		for (INode<?> n : args) {
			if (n instanceof ViewNode) {
				node.children.add((ViewNode) n);
			} else if (n instanceof AttrNode) {
				node.attrs.add((AttrNode) n);
			}
		}
		return node;
	}
	
	// Helper for attribute node containing only one value
	public static abstract class SimpleAttrNode<T> implements AttrNode {
		protected final T value;

		public SimpleAttrNode(T value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return (getClass() == obj.getClass() &&
					((this.value == null && ((SimpleAttrNode) obj).value == null)
					|| this.value.equals(((SimpleAttrNode) obj).value)));
		}
	}

	//
	// Helpers for android attributes and dimensions
	//
	private static Deque<Renderable> renderStack = new ArrayDeque<Renderable>();

	public static TypedValue attr(int attr) {
		TypedValue tv = new TypedValue();
		renderStack.peek().getRootView().getContext().getTheme().resolveAttribute(attr, tv, true);
		return tv;
	}

	public static int attrPx(int attr) {
		TypedValue tv = attr(attr);
		return Math.round(getResources().getDimension(tv.resourceId));
	}

	public static float dip(float value) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
    return value * (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT);
	}

	public static int dip(int value) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
    return Math.round(value * (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}

	private static Resources getResources() {
		return renderStack.peek().getRootView().getResources();
	}

	public static boolean startRendering(Renderable r) {
		if (renderStack.contains(r)) {
			return false;
		}
		renderStack.push(r);
		return true;
	}

	public static void stopRendering() {
		renderStack.pop();
	}

	public static void render(Renderable r) {
		if (!startRendering(r)) {
			return; // Don't render same renderer recursively
		}
		ViewNode oldValue = mounts.get(r);
		ViewNode virt = r.view();
		mounts.put(r, virt);

		inflateNode(r.getRootView().getContext(), virt, oldValue, r.getRootView(), 0, null);
		stopRendering();
	}

	// Render all mounted views
	public static void render() {
		for (Renderable r: mounts.keySet()) {
			render(r);
		}
	}

	public static View inflateNode(Context c, ViewNode node, ViewNode oldNode,
			ViewGroup parent, int index, ViewGroup.LayoutParams defaultParams) {
		boolean isNewView = false;
		try {
			View oldView = null;
			if (index >= 0) {
				if (index < parent.getChildCount()) {
					oldView = parent.getChildAt(index);
				}
			}
			// Reuse view or create a new one
			View v;
			if (oldNode == null || oldNode.viewClass == null || node.viewClass != oldNode.viewClass) {
				isNewView = true;
				v = (View) node.viewClass.getConstructor(Context.class).newInstance(c);
				if (defaultParams != null) { // negative index means don't add to parent
					v.setLayoutParams(defaultParams);
				}
				if (index >= 0) {
					if (oldView != null) {
						parent.removeViewAt(index); // replace old view
					}
					parent.addView(v, index);
				}
			} else {
				v = oldView;
			}

			int viewIndex = 0;
			for (int i = 0; i < node.children.size(); i++) {
				ViewNode subnode = node.children.get(i);
				ViewNode oldSubNode =
					(oldNode == null || oldNode.children.size() <= i ?  null : oldNode.children.get(i));
				inflateNode(c, subnode, oldSubNode, (ViewGroup) v, viewIndex, null);
				viewIndex++;
			}

			// Some views could be removed since last render
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				if (viewIndex != 0 && vg.getChildCount() >= viewIndex) {
					vg.removeViews(viewIndex-1, vg.getChildCount() - viewIndex);
				}
			}
			for (int i = 0; i < node.attrs.size(); i++) {
				AttrNode subnode = node.attrs.get(i);
				AttrNode oldSubNode = 
					(oldNode == null || oldNode.attrs.size() <= i ?  null : oldNode.attrs.get(i));
				if (isNewView || oldSubNode == null || subnode.equals(oldSubNode) == false) {
					subnode.apply(v);
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
