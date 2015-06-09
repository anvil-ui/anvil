package trikita.anvil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.Map;
import java.util.WeakHashMap;

//
// In RenderableAdapter one must override getCount(), getItem() and itemView(pos)
//
public abstract class RenderableAdapter extends BaseAdapter implements Renderable {
	private Map<View, Nodes.ViewNode> activeViews = new WeakHashMap<View, Nodes.ViewNode>();
	private ViewGroup mCurrentParentView;

	public long getItemId(int pos) {
		return pos; // just a most common implementation
	}

	public ViewGroup getRootView() {
		return mCurrentParentView; // parent view for currently rendered item
	}

	public Nodes.ViewNode view() {
		notifyDataSetChanged(); // force item renderables to render
		return null; // Just to match the interface
	}

	public abstract Nodes.ViewNode itemView(int pos);

	public View getView(int pos, View v, ViewGroup parent) {
		Nodes.ViewNode m = activeViews.get(v);
		Anvil.startRendering(this);
		mCurrentParentView = parent;
		Nodes.ViewNode n = itemView(pos);
		mCurrentParentView = null;
		ViewGroup.LayoutParams params =
			new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		View oldView = v;
		v = Anvil.inflateNode(parent.getContext(), n, m, new AdapterMount(v, params));
		activeViews.put(v, n);
		Anvil.stopRendering();
		return v;
	}

	// A mount point inside the adapter view.
	// Views can't be added to the adapter view, so we need to define their
	// layoutparams without adding
	private static class AdapterMount implements Anvil.Mount {
		ViewGroup.LayoutParams params;
		View view;
		public AdapterMount(View v, ViewGroup.LayoutParams params) {
			this.params = params;
			this.view = v;
		}
		public void set(View v) {
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

