package trikita.anvil;

import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.DSL.*;
import static trikita.anvil.TestUtil.*;

public class LambdaTest extends AndroidTestCase {
	@Test
	public void testSingleView() {
		final ViewGroup rootView = new TestLayout(getContext());

		Renderable r = () -> {
			testLayout(() -> {
				tag("123");
				testView(() -> {
					tag(123);
				});
				testView(() -> {
					tag(456);
				});
			});
		};
		Anvil.Mount m = Anvil.mount(rootView, r);

		// View should be rendered correctly and attributes should be set
		assertEquals(1, rootView.getChildCount());
		assertEquals(TestLayout.class, rootView.getChildAt(0).getClass());
		assertEquals("123", rootView.getChildAt(0).getTag());

		TestLayout layout = (TestLayout) rootView.getChildAt(0);
		assertEquals(2, layout.getChildCount());
		assertEquals(123, layout.getChildAt(0).getTag());
		assertEquals(456, layout.getChildAt(1).getTag());

		Anvil.unmount(m);
	}
}


