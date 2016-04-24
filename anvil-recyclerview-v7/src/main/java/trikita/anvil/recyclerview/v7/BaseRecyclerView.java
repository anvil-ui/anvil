package trikita.anvil.recyclerview.v7;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import trikita.anvil.Anvil;

import static trikita.anvil.BaseDSL.attr;

class BaseRecyclerView {

    public static void linearLayoutManager() {
        linearLayoutManager(LinearLayoutManager.VERTICAL);
    }

    public static void linearLayoutManager(int orientation) {
        linearLayoutManager(orientation, false);
    }

    public static void linearLayoutManager(int orientation, boolean reverseLayout) {
        linearLayoutManager(new LinearLayoutManagerParams(orientation, reverseLayout));
    }

    private static void linearLayoutManager(LinearLayoutManagerParams params) {
        attr(LinearLayoutManagerFunc.instance, params);
    }

    private static final class LinearLayoutManagerFunc implements Anvil.AttrFunc<LinearLayoutManagerParams> {
        private static final LinearLayoutManagerFunc instance = new LinearLayoutManagerFunc();

        public void apply(View v, final LinearLayoutManagerParams arg, final LinearLayoutManagerParams old) {
            if (v instanceof RecyclerView) {
                ((RecyclerView) v).setLayoutManager(
                        new LinearLayoutManager(v.getContext(), arg.orientation, arg.reverseLayout));
            }
        }
    }

    private static class LinearLayoutManagerParams {
        private int orientation = LinearLayoutManager.VERTICAL;
        private boolean reverseLayout;

        public LinearLayoutManagerParams(int orientation, boolean reverseLayout) {
            this.orientation = orientation;
            this.reverseLayout = reverseLayout;
        }
    }
}
