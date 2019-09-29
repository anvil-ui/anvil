package trikita.anvil.yoga.layout;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.android.YogaLayout;

import trikita.anvil.Anvil;
import trikita.anvil.BaseDSL;

public class YogaDSL implements Anvil.AttributeSetter {

    static {
        Anvil.registerAttributeSetter(new YogaDSL());
    }

    public static Void yogaLayout(Anvil.Renderable r) {
        return BaseDSL.v(YogaLayout.class, r);
    }

    public static Void flexDirection(YogaFlexDirection direction) {
        return BaseDSL.attr("flexDirection", direction);
    }

    public static Void alignItems(YogaAlign align) {
        return BaseDSL.attr("alignItems", align);
    }

    public static Void flex(float weight) {
        return BaseDSL.attr("flex", weight);
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        switch (name) {
            case "flexDirection":
                if (v instanceof YogaLayout && value instanceof YogaFlexDirection) {
                    ((YogaLayout) v).getYogaNode().setFlexDirection((YogaFlexDirection) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "alignItems":
                if (v.getParent() instanceof YogaLayout && value instanceof YogaAlign) {
                    ((YogaLayout) v).getYogaNode().setAlignItems((YogaAlign) value);
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    v.setLayoutParams(lp);
                    return true;
                }
                break;
            case "flex":
                if (v.getParent() instanceof YogaLayout && value instanceof Float) {
                    ((YogaLayout) v.getParent()).getYogaNodeForView(v).setFlex((Float) value);
                    return true;
                }
                break;
        }
        return false;
    }
}
