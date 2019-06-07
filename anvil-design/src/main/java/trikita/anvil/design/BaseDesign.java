package trikita.anvil.design;

import static trikita.anvil.BaseDSL.attr;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import trikita.anvil.Anvil;

public class BaseDesign implements Anvil.AttributeSetter {

  static {
    Anvil.registerAttributeSetter(new BaseDesign());
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
    }

    return false;
  }

}
