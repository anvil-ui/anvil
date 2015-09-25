package trikita.anvil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class RenderableAdapter extends BaseAdapter {

	public abstract void view(int index);
	
	private Map<View, Integer> positions = new WeakHashMap<>();

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		if (v == null) {
			v = new RenderableView(parent.getContext()) {
				public void view() {
					RenderableAdapter.this.view(positions.get(this));
				}
			};
		} else {
			((RenderableView) v).mount.render();
		}
		positions.put(v, pos);
		return v;
	}

	@Override
	public long getItemId(int pos) {
		return pos; // just a most common implementation
	}
}
