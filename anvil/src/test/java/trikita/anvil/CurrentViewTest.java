package trikita.anvil;

import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;
import static trikita.anvil.BaseDSL.*;

public class CurrentViewTest extends Utils {
    @Test
    public void testCurrentView() {
        assertNull(Anvil.currentView());
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                assertTrue(Anvil.currentView() instanceof ViewGroup);
                v(MockLayout.class, new Anvil.Renderable() {
                    public void view() {
                        assertTrue(Anvil.currentView() instanceof MockLayout);
                        v(MockView.class, new Anvil.Renderable() {
                            public void view() {
                                assertTrue(Anvil.currentView() instanceof MockView);
                                attr("text", "bar");
                                MockView view = Anvil.currentView(); // should cast automatically
                                assertEquals("bar", view.getText());
                            }
                        });
                        assertTrue(Anvil.currentView() instanceof MockLayout);
                    }
                });
                assertTrue(Anvil.currentView() instanceof ViewGroup);
            }
        });
        assertNull(Anvil.currentView());
    }
}
