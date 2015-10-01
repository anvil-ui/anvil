package trikita.anvil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import java.util.List;

public abstract class RenderableAdapter extends BaseAdapter {
	
	private int currentPosition = -1;

	public interface Item<T> {
		public void view(int index, T item);
	}

	public static <T> RenderableAdapter withItems(final List<T> items, final Item<T> r) {
		return new RenderableAdapter() {
			public int getCount() {
				return items.size();
			}
			public T getItem(int pos) {
				return items.get(pos);
			}
			public void view(int pos) {
				r.view(pos, getItem(pos));
			}
		};
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			currentPosition = pos;
			FrameLayout vg = new FrameLayout(parent.getContext());
			Anvil.Mount m = new Anvil.Mount(vg, new Anvil.Renderable() {
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

	public abstract void view(int index);
}
