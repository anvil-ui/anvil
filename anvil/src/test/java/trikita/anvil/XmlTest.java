package trikita.anvil;

import android.content.Context;
import android.view.View;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static trikita.anvil.BaseDSL.attr;
import static trikita.anvil.BaseDSL.xml;
import static trikita.anvil.BaseDSL.v;

public class XmlTest extends Utils {
    private final static int ID_HEADER = 100;
    private final static int LAYOUT = 1;

    private int inflateCount = 0;

    public XmlTest() {
        Anvil.registerViewFactory(new Anvil.ViewFactory() {
            public View fromClass(Context c, Class<? extends View> v) { return null; }
            public View fromXml(Context c, int xmlId) {
                System.out.println("fromXml " + c + " " + xmlId);
                if (xmlId == LAYOUT) {
                    inflateCount++;
                    MockLayout layout = new MockLayout(c);
                    MockView header = new MockView(c);
                    header.setId(ID_HEADER);
                    layout.addView(header, 0);
                    return layout;
                }
                return null;
            }
        });
    }

    @Test
    public void testXml() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                xml(LAYOUT, new Anvil.Renderable() {
                    public void view() {
                        attr("text", "foo");
                        v(MockView.class, new Anvil.Renderable() {
                            public void view() {
                                attr("tag", "bar");
                            }
                        });
                    }
                });
            }
        });
        assertEquals(1, inflateCount);
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals("foo", layout.getText());
        assertEquals(1, layout.getChildCount());
        assertEquals(ID_HEADER, layout.getChildAt(0).getId());
        assertEquals("bar", layout.getChildAt(0).getTag());
    }
}
