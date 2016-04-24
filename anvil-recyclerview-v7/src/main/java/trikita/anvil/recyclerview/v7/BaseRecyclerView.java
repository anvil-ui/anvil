package trikita.anvil.recyclerview.v7;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static trikita.anvil.BaseDSL.attr;

import trikita.anvil.Anvil;

class BaseRecyclerView {

    public static void linearLayoutManager() {
        linearLayoutManager(LinearLayoutManager.VERTICAL);
    }

    public static void linearLayoutManager(int orientation) {
        linearLayoutManager(orientation, false);
    }

    public static void linearLayoutManager(int orientation, boolean reverseLayout) {
        attr(LinearLayoutManagerFunc.instance,
                new LayoutManagerParams(0, orientation, reverseLayout));
    }

    public static void gridLayoutManager(int spanCount) {
        gridLayoutManager(spanCount, LinearLayoutManager.VERTICAL, false);
    }

    public static void gridLayoutManager(int spanCount, int orientation, boolean reverseLayout) {
        attr(GridLayoutManagerFunc.instance,
                new LayoutManagerParams(spanCount, orientation, reverseLayout));
    }

    private static final class LinearLayoutManagerFunc implements Anvil.AttrFunc<LayoutManagerParams> {
        private static final LinearLayoutManagerFunc instance = new LinearLayoutManagerFunc();

        public void apply(View v, final LayoutManagerParams arg, final LayoutManagerParams old) {
            if (v instanceof RecyclerView) {
                ((RecyclerView) v).setLayoutManager(
                        new LinearLayoutManager(v.getContext(), arg.orientation, arg.reverseLayout));
            }
        }
    }

    private static final class GridLayoutManagerFunc implements Anvil.AttrFunc<LayoutManagerParams> {
        private static final GridLayoutManagerFunc instance = new GridLayoutManagerFunc();

        public void apply(View v, final LayoutManagerParams arg, final LayoutManagerParams old) {
            if (v instanceof RecyclerView) {
                ((RecyclerView) v).setLayoutManager(
                        new GridLayoutManager(v.getContext(),
                            arg.spanCount, arg.orientation, arg.reverseLayout));
            }
        }
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
