package trikita.anvil.recyclerview.v7;

import static trikita.anvil.BaseDSL.attr;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
                new LayoutManagerParams(0, orientation, reverseLayout, null));
    }

    public static void gridLayoutManager(int spanCount) {
        gridLayoutManager(spanCount, LinearLayoutManager.VERTICAL, false);
    }

    public static void gridLayoutManager(int spanCount, int orientation, boolean reverseLayout) {
        attr("gridLayoutManager",
                new LayoutManagerParams(spanCount, orientation, reverseLayout, null));
    }

    public static void gridLayoutManager(int spanCount, int orientation, boolean reverseLayout, GridLayoutManager.SpanSizeLookup spanSizeLookup ) {
        attr("gridLayoutManager",
                new LayoutManagerParams(spanCount, orientation, reverseLayout, spanSizeLookup));
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        if (name.equals("linearLayoutManager")) {
            if (v instanceof RecyclerView && value instanceof LayoutManagerParams) {
                LayoutManagerParams p = (LayoutManagerParams) value;
                ((RecyclerView) v).setLayoutManager(
                        new LinearLayoutManager(v.getContext(), p.orientation, p.reverseLayout));
                return true;
            }
        }
        if (name.equals("gridLayoutManager")) {
            if (v instanceof RecyclerView && value instanceof LayoutManagerParams) {
                LayoutManagerParams p = (LayoutManagerParams) value;
                GridLayoutManager lm = new GridLayoutManager(v.getContext(), p.spanCount, p.orientation, p.reverseLayout);
                if (p.spanSizeLookup != null){
                    lm.setSpanSizeLookup(p.spanSizeLookup);
                }
                ((RecyclerView) v).setLayoutManager(lm);
                return true;
            }
        }
        return false;
    }

    private static class LayoutManagerParams {
        private final int spanCount;
        private final int orientation;
        private final boolean reverseLayout;
        private final GridLayoutManager.SpanSizeLookup spanSizeLookup;

        public LayoutManagerParams(int spanCount, int orientation, boolean reverseLayout, GridLayoutManager.SpanSizeLookup sizeLookup) {
            this.spanCount = spanCount;
            this.orientation = orientation;
            this.reverseLayout = reverseLayout;
            this.spanSizeLookup = sizeLookup;
        }

        public int hashCode() {
            return (spanCount << 4) + (orientation << 1) + (reverseLayout ? 1 : 0) + spanSizeLookup.hashCode();
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LayoutManagerParams)) return false;
            LayoutManagerParams params = (LayoutManagerParams) o;
            return params.spanCount == spanCount && params.orientation == orientation &&
                params.reverseLayout == reverseLayout && spanSizeLookup == params.spanSizeLookup;
        }
    }
}
