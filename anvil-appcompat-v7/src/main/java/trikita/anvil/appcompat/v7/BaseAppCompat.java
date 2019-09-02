package trikita.anvil.appcompat.v7;

import android.support.v7.widget.Toolbar;
import android.view.View;
import trikita.anvil.Anvil;

public class BaseAppCompat implements Anvil.AttributeSetter {

    static {
        Anvil.registerAttributeSetter(new BaseAppCompat());
    }

    @Override
    public boolean set(View v, String name, Object value, Object prevValue) {
        switch (name) {
            case "layoutGravity":
                if (v.getLayoutParams() instanceof Toolbar.LayoutParams && value instanceof Integer) {
                    ((Toolbar.LayoutParams) v.getLayoutParams()).gravity = (int) value;
                    return true;
                }
                break;
        }

        return false;
    }

}