package trikita.anvil;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kyle on 6/1/15.
 */
public class RefSet {
    private Map<String, View> refs = new HashMap();
    private Map<String, List<RefSetObserver>> observers = new HashMap();

    public interface RefSetObserver {
        public void update(View v);
    }

    public View get(String ref) {
        return refs.get(ref);
    }

    public void set(String ref, View v) {
        refs.put(ref, v);
        if(null != observers.get(ref))
            for( RefSetObserver r : observers.get(ref) )
                r.update(v);
    }

    public void onUpdate(String ref, RefSetObserver o) {
        List refObservers = observers.get(ref);
        if( null == refObservers ) {
            refObservers = new ArrayList();
            observers.put(ref, refObservers);
        }
        refObservers.add(o);
    }
}
