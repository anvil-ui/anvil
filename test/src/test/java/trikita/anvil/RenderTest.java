package trikita.anvil;

import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.DSL.*;
import static trikita.anvil.TestUtil.*;

public class RenderTest extends AndroidTestCase {
	@Test
	public void testSingleView() {
		final Object foo = new Object();
		final ViewGroup rootView = new TestLayout(getContext());

		final int[] called = {0};
		Anvil.Mount m = Anvil.mount(rootView, new Renderable() {
			public void view() {
				called[0]++;
				o (testView(),
					tag(foo));
			}
		});

		// View should be rendered correctly and attributes should be set
		assertEquals(1, rootView.getChildCount());
		assertEquals(TestView.class, rootView.getChildAt(0).getClass());
		assertEquals(foo, rootView.getChildAt(0).getTag());
		assertEquals(1, called[0]);

		// On second run view should not be updated
		View viewRef = rootView.getChildAt(0);

		m.render();

		assertEquals(1, rootView.getChildCount());
		assertEquals(viewRef, rootView.getChildAt(0));
		assertEquals(foo, rootView.getChildAt(0).getTag());
		assertEquals(2, called[0]);

		Anvil.unmount(m);
	}
}

