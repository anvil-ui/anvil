package trikita.anvil;

import android.view.View;

import org.junit.Test;
import static trikita.anvil.BaseDSL.*;
import org.mockito.Mockito;

import java.lang.ref.WeakReference;

import static junit.framework.Assert.*;

public class MountTest extends Utils {

    Anvil.Renderable testLayout = new Anvil.Renderable() {
        public void view() {
            v(MockView.class, new Anvil.Renderable() {
                public void view() {
                    prop("foo", "bar");
                }
            });
        }
    };

    @Test
    public void testMountReturnsMountPoint() {
        assertEquals(container, Anvil.mount(container, empty));
    }

    @Test
    public void testMountRendersViews() {
        Anvil.mount(container, testLayout);
        assertEquals(1, container.getChildCount());
        assertTrue(container.getChildAt(0) instanceof MockView);
        MockView v = (MockView) container.getChildAt(0);
        assertEquals("bar", v.props.get("foo"));
    }

    @Test
    public void testUnmountRemovesViews() {
        Anvil.mount(container, testLayout);
        assertEquals(1, container.getChildCount());
        Anvil.unmount(container);
        assertEquals(0, container.getChildCount());
    }

    @Test
    public void testMountReplacesViews() {
        Anvil.mount(container, testLayout);
        assertEquals(1, container.getChildCount());
        Anvil.mount(container, empty);
        assertEquals(0, container.getChildCount());
        Anvil.mount(container, testLayout);
        assertEquals(1, container.getChildCount());
    }

    @Test
    public void testMountInfoView() {
        MockView v = Anvil.mount(new MockView(getContext()), new Anvil.Renderable() {
            public void view() {
                prop("foo", "bar");
            }
        });
        assertEquals("bar", v.props.get("foo"));
    }

    @Test
    public void testMountGC() {
        Anvil.Renderable layout = Mockito.spy(testLayout);
        Anvil.mount(container, layout);
        Mockito.verify(layout, Mockito.times(1)).view();
        assertEquals(1, container.getChildCount());
        // Once the container is garbage collection all other views should be removed, too
        WeakReference<View> ref = new WeakReference<>(container.getChildAt(0));
        container = null;
        System.gc();
        assertEquals(null, ref.get());
        // Ensure that the associated renderable is no longer called
        Anvil.render();
        Mockito.verify(layout, Mockito.times(1)).view();
    }
}