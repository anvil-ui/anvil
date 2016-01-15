package trikita.anvil;

import android.content.Context;
import android.view.View;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static trikita.anvil.BaseDSL.xml;
import static trikita.anvil.BaseDSL.v;

public class XmlTest extends Utils {
    private final static int ID_HEADER = 100;
    private final static int LAYOUT = 1;

    private int inflateCount = 0;

    @Override
    public View fromXml(Context c, int xmlId) {
        if (xmlId == LAYOUT) {
            inflateCount++;
            MockLayout layout = new MockLayout(c);
            MockView header = new MockView(c);
            header.setId(ID_HEADER);
            layout.addView(header, 0);
            return layout;
        } else {
            return super.fromXml(c, xmlId);
        }
    }

    @Test
    public void testXml() {
        Anvil.mount(container, new Anvil.Renderable() {
            public void view() {
                xml(LAYOUT, new Anvil.Renderable() {
                    public void view() {
                        prop("layout", "foo");
                        v(MockView.class, empty);
                    }
                });
            }
        });
        assertEquals(1, inflateCount);
        MockLayout layout = (MockLayout) container.getChildAt(0);
        assertEquals(2, layout.getChildCount());
        assertEquals(ID_HEADER, layout.getChildAt(0).getId());
        assertEquals("foo", layout.props.get("layout"));
    }
}
