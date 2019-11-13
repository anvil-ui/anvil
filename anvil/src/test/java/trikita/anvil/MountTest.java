package trikita.anvil;

import android.view.View;

import org.junit.Test;
import static trikita.anvil.BaseDSL.*;
import org.mockito.Mockito;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static junit.framework.Assert.*;

public class MountTest extends Utils {

    Anvil.Renderable testLayout = new Anvil.Renderable() {
        public void view() {
            v(MockView.class, new Anvil.Renderable() {
                public void view() {
                    attr("text", "bar");
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
        assertEquals("bar", v.getText());
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
                attr("id", 100);
                attr("text", "bar");
                attr("tag", "foo");
            }
        });
        assertEquals(100, v.getId());
        assertEquals("foo", v.getTag());
        assertEquals("bar", v.getText());
    }

    @Test
    public void testMountGC() throws ExecutionException {
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
        renderView();
        Mockito.verify(layout, Mockito.times(1)).view();
    }

    private void renderView() throws ExecutionException {
        Future<?> future = Anvil.render();
        waitTaskToFinish(future);
    }

    private void waitTaskToFinish(Future<?> future) throws ExecutionException {
        try {
            future.get();
        } catch (ExecutionException executionException) {
            throw executionException;
        } catch (Exception e) {
        }
    }
}