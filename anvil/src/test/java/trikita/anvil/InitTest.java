package trikita.anvil;

import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.BaseDSL.*;

public class InitTest extends Utils {
    Map<String, Boolean> called = new HashMap<>();

    @Test
    public void testInit() {
        System.out.println("============================");
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                init(makeFunc("once"));
                v(MockView.class, new Anvil.Renderable() {
                    public void view() {
                        init(makeFunc("setUpView"));
                    }
                });
            }
        });
        System.out.println("============================");
        assertTrue(called.get("once"));
        assertTrue(called.get("setUpView"));
        called.clear();
        Anvil.render();
        assertFalse(called.containsKey("once"));
        assertFalse(called.containsKey("setUpView"));
    }

    // new function will be created each time, but only the first one should be called
    private Runnable makeFunc(final String id) {
        return new Runnable() {
            public void run() {
                if (called.containsKey(id)) {
                    throw new RuntimeException("Init func called more than once!");
                }
                called.put(id, true);
            }
        };
    }
}

