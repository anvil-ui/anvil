package trikita.anvil;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RenderableRecyclerViewAdapter
        extends RecyclerView.Adapter<RenderableRecyclerViewAdapter.MountHolder> {

    public static <T> RenderableRecyclerViewAdapter withItems(final List<T> items,
            final RenderableAdapter.Item<T> r) {
        return new RenderableRecyclerViewAdapter() {
            {
                setHasStableIds(false);
            }
            public int getItemCount() {
                return items.size();
            }
            public int getItemViewType(int pos) {
                Object item = items.get(pos);
                return item == null ? 0 : item.getClass().hashCode();
            }
            public void view(RecyclerView.ViewHolder holder) {
                int i = holder.getLayoutPosition();
                r.view(i, items.get(i));
            }
        };
    }

    @Override
    public MountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout root = new FrameLayout(parent.getContext());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new MountHolder(root);
    }

    @Override
    public void onBindViewHolder(final MountHolder h, int position) {
        if (h.mount == null) {
            h.mount = new Anvil.Mount((ViewGroup) h.itemView, new Anvil.Renderable() {
                public void view() {
                    RenderableRecyclerViewAdapter.this.view(h);
                }
            });
            Anvil.render(h.mount);
        } else {
            Anvil.render(h.mount);
        }
    }

    @Override
    public long getItemId(int pos) {
        return pos; // just a most common implementation
    }

    public static class MountHolder extends RecyclerView.ViewHolder {
        private Anvil.Mount mount;

        public MountHolder(ViewGroup itemView) {
            super(itemView);
        }
    }

    public abstract void view(RecyclerView.ViewHolder holder);
}
