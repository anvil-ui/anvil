package trikita.anvil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static trikita.anvil.BaseDSL.*;

public class IncrementalRenderTest extends Utils {

    private String fooValue = "a";
    private boolean showView = true;

    @Test
    public void testConstantsRenderedOnce() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o(v(MockLayout.class), prop("foo", "bar"));
            }
        });
        assertEquals(1, (int) createdViews.get(MockLayout.class));
        assertEquals(1, (int) changedAttrs.get("foo"));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockLayout.class));
        assertEquals(1, (int) changedAttrs.get("foo"));
    }

    @Test
    public void testConstantsRenderedOnceWithFactoryFunc() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o(v(MockLayout.FACTORY), prop("foo", "bar"));
            }
        });
        assertEquals(1, (int) createdViews.get(MockLayout.class));
        assertEquals(1, (int) changedAttrs.get("foo"));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockLayout.class));
        assertEquals(1, (int) changedAttrs.get("foo"));
    }

    @Test
    public void testDynamicAttributeRenderedLazily() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o(v(MockLayout.class), prop("foo", fooValue));
            }
        });
        assertEquals(1, (int) changedAttrs.get("foo"));
        Anvil.render();
        assertEquals(1, (int) changedAttrs.get("foo"));
        fooValue = "b";
        Anvil.render();
        assertEquals(2, (int) changedAttrs.get("foo"));
        Anvil.render();
        assertEquals(2, (int) changedAttrs.get("foo"));
    }

    @Test
    public void testDynamicViewRenderedLazily() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o(v(MockLayout.class),
                        o(v(MockLayout.class)),
                        showView ?
                                o(v(MockView.class)) :
                                null);
            }
        });
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals(2, layout.getChildCount());
        assertEquals(1, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockView.class));
        showView = false;
        Anvil.render();
        assertEquals(1, layout.getChildCount());
        assertEquals(1, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockView.class));
        showView = true;
        Anvil.render();
        assertEquals(2, layout.getChildCount());
        assertEquals(2, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(2, (int) createdViews.get(MockView.class));
    }

    @Test
    public void testDynamicViewRenderedLazilyWithFactoryFunc() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o(v(MockLayout.FACTORY),
                        o(v(MockLayout.FACTORY)),
                        showView ?
                                o(v(MockView.FACTORY)) :
                                null);
            }
        });
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals(2, layout.getChildCount());
        assertEquals(1, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockView.class));
        showView = false;
        Anvil.render();
        assertEquals(1, layout.getChildCount());
        assertEquals(1, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(1, (int) createdViews.get(MockView.class));
        showView = true;
        Anvil.render();
        assertEquals(2, layout.getChildCount());
        assertEquals(2, (int) createdViews.get(MockView.class));
        Anvil.render();
        assertEquals(2, (int) createdViews.get(MockView.class));
    }

    private String firstMountValue = "foo";
    private String secondMountValue = "bar";

    @Test
    public void testRenderUpdatesAllMounts() {
        MockLayout rootA = new MockLayout(getContext());
        MockLayout rootB = new MockLayout(getContext());
        Anvil.mount(rootA, new Anvil.Renderable() {
            public void view() {
                prop("foo", firstMountValue);
            }
        });
        Anvil.mount(rootB, new Anvil.Renderable() {
            public void view() {
                prop("bar", secondMountValue);
            }
        });
        assertEquals("foo", rootA.props.get("foo"));
        assertEquals("bar", rootB.props.get("bar"));

        firstMountValue = "baz";
        secondMountValue = "qux";
        Anvil.render();

        assertEquals("baz", rootA.props.get("foo"));
        assertEquals("qux", rootB.props.get("bar"));
    }
}
