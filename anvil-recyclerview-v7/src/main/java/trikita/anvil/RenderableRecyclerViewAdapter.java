package trikita.anvil;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

public abstract class RenderableRecyclerViewAdapter extends RecyclerView.Adapter<RenderableRecyclerViewAdapter.ViewHolder> {

    public interface Item<T> {
        void view(int index, T item);
    }

    public static <T> RenderableRecyclerViewAdapter withItems(final List<T> items, final Item<T> r) {
        return new RenderableRecyclerViewAdapter() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(new FrameLayout(parent.getContext()), new ViewHolder.ItemRenderable() {
                    @Override
                    public void itemView(int position) {
                        view(position);
                    }
                });
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.render(position);
            }

            @Override
            public int getItemCount() {
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

    public final static class ViewHolder extends RecyclerView.ViewHolder {

        private final Anvil.Mount mount;
        private int currentPosition = -1;

        private ViewHolder(View parent, final ItemRenderable renderable) {
            super(parent);
            mount = new Anvil.Mount(parent, new Anvil.Renderable() {
                public void view() {
                    renderable.itemView(currentPosition);
                }
            });
        }

        private void render(int position) {
            currentPosition = position;
            Anvil.render(mount);
        }

        public interface ItemRenderable {
            void itemView(int position);
        }
    }

    @Override
    public long getItemId(int pos) {
        return pos; // just a most common implementation
    }

    public abstract void view(int index);
}
