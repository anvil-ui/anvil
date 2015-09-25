package trikita.anvil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class RenderableAdapter extends BaseAdapter {

	public abstract void view(int index);
	
	private int currentPosition = -1;

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		if (v == null) {
			final FrameLayout vg = new FrameLayout(parent.getContext());
			currentPosition = pos;
			Anvil.Mount m = new Anvil.Mount(vg, new Renderable() {
				public void view() {
					RenderableAdapter.this.view(currentPosition);
				}
			});
			m.render();
			vg.setTag(m);
			v = vg;
		} else {
			Anvil.Mount m = (Anvil.Mount) v.getTag();
			currentPosition = pos;
			m.render();
		}
		return v;
	}

	@Override
	public long getItemId(int pos) {
		return pos; // just a most common implementation
	}
}
