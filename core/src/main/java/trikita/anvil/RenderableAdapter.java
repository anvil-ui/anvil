package trikita.anvil;

import android.widget.BaseAdapter;

import java.util.Map;

public abstract class RenderableAdapter
	extends BaseAdapter implements Renderable {
	
	public void view() {}
	//private Map<View, Mount> mounts = new WeakHashMap<View, Mount>();

	//public View getView(int pos, View v, ViewGroup parent) {
		//Mount m = mounts.get(v);
		//Anvil.startRendering(this);
		//mCurrentParentView = parent;
		//Nodes.ViewNode n = itemView(pos);
		//mCurrentParentView = null;
		//ViewGroup.LayoutParams params =
			//new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					//ViewGroup.LayoutParams.WRAP_CONTENT);
		//View oldView = v;
		//v = Anvil.inflateNode(parent.getContext(), n, m, new AdapterMount(v, params));
		//activeViews.put(v, n);
		//Anvil.stopRendering();
		//return v;
	//}

	//public abstract void view() {
		//notifyDataSetChanged(); // force item renderables to render
	//}

	//public long getItemId(int pos) {
		//return pos; // just a most common implementation
	//}

	//// A mount point inside the adapter view.
	//// Views can't be added to the adapter view, so we need to define their
	//// layoutparams without adding
	////private static class AdapterMount implements Anvil.Mount {
		////ViewGroup.LayoutParams params;
		////View view;
		////public AdapterMount(View v, ViewGroup.LayoutParams params) {
			////this.params = params;
			////this.view = v;
		////}
		////public void set(View v) {
			////if (this.view != v && this.params != null) {
				////this.view = v;
				////this.view.setLayoutParams(this.params);
			////}
		////}
		////public View get() {
			////return this.view;
		////}
	////}
}
