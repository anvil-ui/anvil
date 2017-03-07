package trikita.anvil.recyclerview.v7;

import static trikita.anvil.BaseDSL.attr;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import trikita.anvil.Anvil;

class BaseRecyclerView implements Anvil.AttributeSetter {

    static {
        Anvil.registerAttributeSetter(new BaseRecyclerView());
    }

    public static void linearLayoutManager() {
        linearLayoutManager(LinearLayoutManager.VERTICAL);
    }

    public static void linearLayoutManager(int orientation) {
        linearLayoutManager(orientation, false);
    }

    public static void linearLayoutManager(int orientation, boolean reverseLayout) {
        attr("linearLayoutManager",
                new LayoutManagerParams(0, orientation, reverseLayout));
    }

    public static void gridLayoutManager(int spanCount) {
        gridLayoutManager(spanCount, LinearLayoutManager.VERTICAL, false);
    }

    public static void gridLayoutManager(int spanCount, int orientation, boolean reverseLayout) {
        attr("gridLayoutManager",
                new LayoutManagerParams(spanCount, orientation, reverseLayout));
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        if (name.equals("linearLayoutManager") || name.equals("gridLayoutManager")) {
            if (v instanceof RecyclerView && value instanceof LayoutManagerParams) {
                LayoutManagerParams p = (LayoutManagerParams) value;
                ((RecyclerView) v).setLayoutManager(
                        new LinearLayoutManager(v.getContext(), p.orientation, p.reverseLayout));
                return true;
            }
        }
        return false;
    }

    private static class LayoutManagerParams {
        private final int spanCount;
        private final int orientation;
        private final boolean reverseLayout;

        public LayoutManagerParams(int spanCount, int orientation, boolean reverseLayout) {
            this.spanCount = spanCount;
            this.orientation = orientation;
            this.reverseLayout = reverseLayout;
        }

        public int hashCode() {
            return (spanCount << 4) + (orientation << 1) + (reverseLayout ? 1 : 0);
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LayoutManagerParams)) return false;
            LayoutManagerParams params = (LayoutManagerParams) o;
            return params.spanCount == spanCount && params.orientation == orientation &&
                params.reverseLayout == reverseLayout;
        }
    }
}
