package trikita.anvil;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

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

		inflateNode(r.getRootView().getContext(), virt, oldValue, new ParentMount(r.getRootView(), 0));
		stopRendering();
	}

	// Render all mounted views
	public static void render() {
		for (Renderable r: mounts.keySet()) {
			render(r);
		}
	}

	// Mount is a placeholder for android view
	public interface Mount {
		public View get();
		public void set(View v);
	}

	// A real mount point in a parent view at certain index
	public static class ParentMount implements Mount {
		ViewGroup parent;
		int index;
		public ParentMount(ViewGroup parent, int index) {
			this.parent = parent;
			this.index = index;
		}
		public void set(View v) {
			if (index < parent.getChildCount()) {
				parent.removeViewAt(index);
			}
			parent.addView(v, index);
		}
		public View get() {
			if (index < parent.getChildCount()) {
				return parent.getChildAt(index);
			} else {
				return null;
			}
		}
	}

	public static View inflateNode(Context c, ViewNode node, ViewNode oldNode, Mount mount) {
		boolean isNewView = false;
		try {
			View v = mount.get();
			// Reuse view or create a new one
			if (v == null || oldNode == null || oldNode.viewClass == null ||
					node.viewClass != oldNode.viewClass) {
				v = (View) node.viewClass.getConstructor(Context.class).newInstance(c);
				mount.set(v);
			}

			int viewIndex = 0;
			for (int i = 0; i < node.children.size(); i++) {
				ViewNode subnode = node.children.get(i);
				ViewNode oldSubNode =
					(oldNode == null || oldNode.children.size() <= i ?  null : oldNode.children.get(i));
				inflateNode(c, subnode, oldSubNode, new ParentMount((ViewGroup) v, viewIndex));
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

	//
	// In RenderableAdapter one must override getCount(), getItem() and itemView(pos)
	//
	public abstract static class RenderableAdapter extends BaseAdapter implements Renderable {
		private Map<View, ViewNode> activeViews = new WeakHashMap<View, ViewNode>();
		private ViewGroup mCurrentParentView;

		public long getItemId(int pos) {
			return pos; // just a most common implementation
		}
		public ViewGroup getRootView() {
			return mCurrentParentView; // parent view for currently rendered item
		}
		public ViewNode view() {
			return null; // Just to match the interface
		}

		public abstract ViewNode itemView(int pos);

		public View getView(int pos, View v, ViewGroup parent) {
			ViewNode m = activeViews.get(v);
			startRendering(this);
			mCurrentParentView = parent;
			ViewNode n = itemView(pos);
			mCurrentParentView = null;
			ViewGroup.LayoutParams params =
				new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
			View oldView = v;
			v = inflateNode(parent.getContext(), n, m, new AdapterMount(v, params));
			activeViews.put(v, n);
			stopRendering();
			return v;
		}
	}

	public abstract static class RenderableArrayAdapter<T> extends RenderableAdapter {
		private List<T> items;

		public RenderableArrayAdapter(T[] items) {
			this.items = Arrays.asList(items);
		}

		public RenderableArrayAdapter(List<T> items) {
			this.items = items;
		}

		public int getCount() {
			return items.size();
		}

		public T getItem(int pos) {
			return items.get(pos);
		}

		public ViewNode itemView(int pos) {
			return itemView(pos, getItem(pos));
		}

		public abstract ViewNode itemView(int pos, T value);
	}

	// A mount point inside the adapter view.
	// Views can't be added to the adapter view, so we need to define their
	// layoutparams without adding
	public static class AdapterMount implements Mount {
		ViewGroup.LayoutParams params;
		View view;
		public AdapterMount(View v, ViewGroup.LayoutParams params) {
			this.params = params;
			this.view = v;
		}
		public void set(View v) {
			System.out.println("Set " + v);
			if (this.view != v && this.params != null) {
				this.view = v;
				this.view.setLayoutParams(this.params);
			}
		}
		public View get() {
			return this.view;
		}
	}
}
