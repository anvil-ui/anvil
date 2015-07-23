package trikita.anvil;

import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.Nodes.*;

@SmallTest
public class SimpleAttrNodeTest {

	@Test
	public void testSimpleAttrNodeEquals() {
		assertEquals(text("hello"), text("hello"));
		assertEquals(text(null), text(null));
		assertTrue(text("a").equals(text("a")));

		assertNotEquals(text("hello"), text("world"));
		assertNotEquals(text("hello"), text(null));
		assertFalse(text("a").equals(null));
		assertFalse(text("a").equals(text("b")));

		assertEquals(compound(null, null, null), compound(null, null, null));
		assertEquals(compound("a", "b", "c"), compound("a", "b", "c"));
		assertNotEquals(compound("a", "b", "c"), compound("a", "c", "b"));

		assertNotEquals(text("a"), compound("a", "b", "c"));
		assertFalse(text("a").equals(compound("a", "b", "c")));
	}

	private AttrNode text(String s) {
		return new SimpleAttrNode(s) { public void apply(View v) {} };
	}

	private AttrNode compound(final String first, final String second, final String third) {
		List<String> list = new ArrayList<String>(){{
			add(first); add(second); add(third);
		}};
		return new SimpleAttrNode(list) { public void apply(View v) {} };
	}
}
