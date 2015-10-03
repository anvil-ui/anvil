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
		Anvil.mount(rootView, new Anvil.Renderable() {
			public void view() {
				called[0]++;
				o (testView(),
					dummy(foo));
			}
		});

		// View should be rendered correctly and attributes should be set
		assertEquals(1, rootView.getChildCount());
		assertEquals(TestView.class, rootView.getChildAt(0).getClass());
		assertEquals(foo, ((TestView) rootView.getChildAt(0)).getDummy());
		assertEquals(1, called[0]);

		// On second run view should not be updated
		View viewRef = rootView.getChildAt(0);

		Anvil.render();

		assertEquals(1, rootView.getChildCount());
		assertEquals(viewRef, rootView.getChildAt(0));
		assertEquals(foo, ((TestView) rootView.getChildAt(0)).getDummy());
		assertEquals(2, called[0]);

		Anvil.unmount(rootView);
	}
}

