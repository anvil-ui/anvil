package trikita.anvil.material;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import trikita.anvil.Anvil;

import static trikita.anvil.BaseDSL.attr;

public class BaseMaterial implements Anvil.AttributeSetter {

    static {
        Anvil.registerAttributeSetter(new BaseMaterial());
    }

    public static Void collapseMode(int collapseMode) {
        return attr("collapseMode", collapseMode);
    }

    public static Void scrollFlags(int collapseMode) {
        return attr("scrollFlags", collapseMode);
    }

    public static Void behavior(CoordinatorLayout.Behavior behavior) {
        return attr("behavior", behavior);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Void appBarElevation(float elevation) {
        return attr("appBarElevation", elevation);
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        switch (name) {

            case "collapseMode":
                if (v.getLayoutParams() instanceof CollapsingToolbarLayout.LayoutParams && value instanceof Integer) {
                    ((CollapsingToolbarLayout.LayoutParams) v.getLayoutParams()).setCollapseMode((int) value);
                    return true;
                }
                break;
            case "scrollFlags":
                if (v.getLayoutParams() instanceof AppBarLayout.LayoutParams && value instanceof Integer) {
                    ((AppBarLayout.LayoutParams) v.getLayoutParams()).setScrollFlags((int) value);
                    return true;
                }
                break;
            case "behavior":
                if (v.getLayoutParams() instanceof CoordinatorLayout.LayoutParams && (value == null || value instanceof CoordinatorLayout.Behavior)) {
                    ((CoordinatorLayout.LayoutParams) v.getLayoutParams()).setBehavior((CoordinatorLayout.Behavior) value);
                    return true;
                }
                break;
            case "appBarElevation":
                if (v instanceof AppBarLayout && (value == null || value instanceof Float)) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        StateListAnimator stateListAnimator = new StateListAnimator();
                        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(v, "elevation", (Float) value));
                        v.setStateListAnimator(stateListAnimator);
                    }
                    return true;
                }
                break;
        }

        return false;
    }

}
