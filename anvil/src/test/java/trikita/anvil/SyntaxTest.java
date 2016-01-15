package trikita.anvil;

import org.junit.Test;
import static org.junit.Assert.*;

import static trikita.anvil.BaseDSL.*;

public class SyntaxTest extends Utils {

    private boolean showNestedLayout = true;
    private int numberOfChildViews = 5;

    @Test
    public void testSyntaxWithLambdas() {
        // With Java 8 or Kotlin the code below looks much better.
        // Here I use old "lambda"-like syntax to avoid dependency
        // on RetroLambda or Kotlin in Anvil core
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                v(MockLayout.class, new Anvil.Renderable() {
                    public void view() {
                        prop("a", 1);
                        v(MockView.class, new Anvil.Renderable() {
                            public void view() {
                                prop("b", 2);
                            }
                        });
                        if (showNestedLayout) {
                            v(MockLayout.class, new Anvil.Renderable() {
                                public void view() {
                                    for (int i = 0; i < numberOfChildViews; i++) {
                                        final int index = i;
                                        v(MockView.class, new Anvil.Renderable() {
                                            public void view() {
                                                prop("index", index);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals(1, layout.props.get("a"));
        MockView header = (MockView) layout.getChildAt(0);
        assertEquals(2, header.props.get("b"));
        MockLayout content = (MockLayout) layout.getChildAt(1);
        for (int i = 0; i < numberOfChildViews; i++) {
            MockView v = (MockView) content.getChildAt(i);
            assertEquals(i, v.props.get("index"));
        }
    }

    @Test
    public void testSyntaxWithoutLambdas() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                o (v(MockLayout.class),
                        prop("a", 1),
                        o (v(MockView.class),
                                prop("b", 2)),
                        o (v(MockLayout.class),
                                showNestedLayout ? childViews(numberOfChildViews) : null));
            }
        });
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals(1, layout.props.get("a"));
        MockView header = (MockView) layout.getChildAt(0);
        assertEquals(2, header.props.get("b"));
        MockLayout content = (MockLayout) layout.getChildAt(1);
        for (int i = 0; i < numberOfChildViews; i++) {
            MockView v = (MockView) content.getChildAt(i);
            assertEquals(i, v.props.get("index"));
        }
    }

    private Void childViews(int count) {
        for (int i = 0; i < numberOfChildViews; i++) {
            o (v(MockView.class), prop("index", i));
        }
        return null;
    }
}
