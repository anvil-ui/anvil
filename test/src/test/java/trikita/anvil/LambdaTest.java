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

		Anvil.Renderable r = () -> {
			testLayout(() -> {
				dummy("123");
				testView(() -> {
					dummy(123);
				});
				testView(() -> {
					dummy(456);
				});
			});
		};
		Anvil.mount(rootView, r);

		// View should be rendered correctly and attributes should be set
		assertEquals(1, rootView.getChildCount());
		assertEquals(TestLayout.class, rootView.getChildAt(0).getClass());
		assertEquals("123", ((TestLayout) rootView.getChildAt(0)).getDummy());

		TestLayout layout = (TestLayout) rootView.getChildAt(0);
		assertEquals(2, layout.getChildCount());
		assertEquals(123, ((TestView) layout.getChildAt(0)).getDummy());
		assertEquals(456, ((TestView) layout.getChildAt(1)).getDummy());

		Anvil.unmount(rootView);
	}
}


