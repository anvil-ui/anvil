package trikita.anvil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import static trikita.anvil.Nodes.*;
import java.util.ArrayList;

/**
 * <p>
 * This class consists of the static methods to contrl UI rendering process.
 * </p>
 * <p>
 * Inside it manages a collection of {@link Renderable} components. Every
 * component that is now visible there is a mount point (e.g. a parent
 * viewgroup into which the component is rendered).
 * </p>
 * <p>
 * Because the components can be nested there is also a current render stack
 * which helps to prevent infinite recursive rendering. So during the render
 * cycle each component is rendered only once.
 * </p>
 */
public final class Anvil {

	private Anvil() {}

	/**
	 * A map of active renderables and their actual view nodes. References to the
	 * components are weak, so when the component is removed from the views
	 * hierarchy - the map item will be removed as well
	 */
	private static Map<Renderable, ViewNode> mounts =
		new WeakHashMap<Renderable, ViewNode>();
	/**
	 * A stack of the components being rendering during the current rendering
	 * cycle. After rendering iteration is complete - the stack must be empty.
	 */
	private static Deque<Renderable> renderStack = new ArrayDeque<Renderable>();

	/** A flag that tells the renderer to skip the next cycle */
	private static boolean skipNextRender = false;

	/** Returns the current renderable component. This method is intended to be
	 * called from inside the {@link Renderable#view} method. From the current
	 * renderable the root view and associated resources can be extracted.
	 * @return a component being rendered right now
	 */
	public static Renderable getCurrentRenderable() {
		return renderStack.peek();
	}

	/**
	 * A low-level method to start rendering cycle for the certain renderable.
	 * Useful for components that use their own rendering algorithm (e.g.
	 * AdapterView)
	 * @param r the component that needs to be rendered
	 * @return true if the component is not being rendered at the moment,
	 *         false otherwise
	 */
	public static boolean startRendering(Renderable r) {
		if (renderStack.contains(r)) {
			return false;
		}
		renderStack.push(r);
		return true;
	}

	/**
	 * A low-level method to stop the current rendering cycle
	 */
	public static void stopRendering() {
		renderStack.pop();
	}

	/**
	 * Tells rendered to skip the next rendering cycle
	 */
	public static void skipRender() {
		skipNextRender = true;
	}

	/**
	 * Perform rendering of a single renderable
	 * @param r the component that needs to be rendered
	 */
	public static void render(Renderable r) {
		if (!startRendering(r)) {
			return; // Don't render same renderer recursively
		}
		ViewNode oldValue = mounts.get(r);
		ViewNode virt = r.view();
		mounts.put(r, virt);

		inflateNode(r.getRootView().getContext(), virt, oldValue, new ViewMount(r.getRootView(), 0));
		stopRendering();
	}

	/**
	 * Perform rendering of all active renderables. This function is
	 * guaranteed to be executed on the UI thread.
	 */
	public static void render() {
		if (Looper.myLooper() != Looper.getMainLooper()) {
			// Anvil.render() is called on a non-UI thread, use handler
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				public void run() {
					Anvil.render();
				}
			});
			return;
		}
		if (skipNextRender) {
			skipNextRender = false;
			return;
		}
		// We need to copy the keyset, otherwise a concurrent modification may
		// happen if Renderables are nested
		Set<Renderable> set = new HashSet<Renderable>();
		set.addAll(mounts.keySet());
		for (Renderable r: set) {
			render(r);
		}
	}

	/**
	 * This class is a placeholder for a single view. Normally it is implemented
	 * as a ViewGroup.
	 */
	public interface Mount {
		public View get();
		public void set(View v);
	}

	/** A {@link Mount} point implementation as a regular ViewGroup */
	public static class ViewMount implements Mount {
		private ViewGroup parent;
		private int index;
		public ViewMount(ViewGroup parent, int index) {
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

	/**
	 * <p>
	 * This is the most important method. It implements a very stupid diffing
	 * algorithm to render the tree of nodes into a given mount point.
	 * </p>
	 * <p>
	 * Diffing algorithms loops over all nodes recursively. If an old view node
	 * was missing or had a different view class - it will be replaced by the
	 * freshly inflated view.
	 * </p>
	 * <p>
	 * Then child views are rendered recursively.
	 * </p>
	 * <p>
	 * Then the count of child views is compared, and if the newly rendered
	 * layout has the smaller number of child views - the extra views are removed
	 * from the layout.
	 * </p>
	 * <p>
	 * Finally, the attribute nodes are applied to the view. A common
	 * implementation of an attribute node is to modify the view attribute only
	 * if the value has been changed since the last rendering iteration. So the
	 * view rendered multiple times with the same attribute values will not be
	 * updated at all.
	 * </p>
	 * <p>
	 * The major drawback of this algorithm is that one can replace one view by
	 * another with the same class but different set of attributes. Then the
	 * attributes from the previous run won't be undone. In practice, it's
	 * unlikely to happen due to a nature of how the view hierarchy is declared
	 * in code - the order, types and count of the nodes is static in the most
	 * cases.
	 * </p>
	 *
	 * @param c Context that is used to inflate views
	 * @param node Virtual view hierarchy that represents the current state
	 * @param oldNode Virtual view hierarchy that represents the previousl
	 *                rendered state
	 * @param mount A mount point for the rendered view
	 * @return The rendered view layout which is also put into the mount point
	 */
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

			if (v instanceof RenderableView && node.children.size() > 0) {
				RenderableView rv = (RenderableView) v;
				rv.mChildNodes = node.children;
				node.children = new ArrayList<>();
				node.children.add(rv.view());
			}

			int viewIndex = 0;
			for (int i = 0; i < node.children.size(); i++) {
				ViewNode subnode = node.children.get(i);
				ViewNode oldSubNode =
					(oldNode == null || oldNode.children.size() <= i ?  null : oldNode.children.get(i));
				inflateNode(c, subnode, oldSubNode, new ViewMount((ViewGroup) v, viewIndex));
				viewIndex++;
			}

			// Some views could be removed since last render
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				if (viewIndex != 0 && vg.getChildCount() > viewIndex) {
					vg.removeViews(viewIndex, vg.getChildCount() - viewIndex);
				}
			}
			for (int i = 0; i < node.attrs.size(); i++) {
				AttrNode subnode = node.attrs.get(i);
				AttrNode oldSubNode = 
					(oldNode == null || oldNode.attrs.size() <= i ? null : oldNode.attrs.get(i));
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

	/**
	 * A default state factory. Calls Anvil.render() on every state change.
	 */
	private static State.Factory stateFactory =
		new State.Factory(new State.Listener() {
			public void onStateChanged(State state) {
				Anvil.render();
			}
		});

	/**
	 * A helper for making new State objects
	 */
	public static State newState(boolean on) {
		return stateFactory.newState(on);
	}
}
