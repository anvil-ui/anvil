package trikita.anvil;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kyle on 6/1/15.
 */
public class RefSet {
    private Map<String, View> refs = new HashMap<String, View>();

    public View get(String ref) {
        return refs.get(ref);
    }

    public void set(String ref, View v) {
        refs.put(ref, v);
    }
}
