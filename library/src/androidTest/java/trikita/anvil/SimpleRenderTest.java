package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.v10.Attrs.*;

public class SimpleRenderTest extends AndroidTestCase {

	@Test
	public void testSimpleRender() {
		Context c = getContext();
		final ViewGroup rootView = new FrameLayout(c);
		Renderable renderable = new Renderable() {
			public ViewGroup getRootView() {
				return rootView;
			}
			public ViewNode view() {
				return
					v(TextView.class,
						text("Hello"));
			}
		};
		Anvil.render(renderable);
		// rootView has one child and the child is a text view and its text is "Hello"
		assertEquals(1, rootView.getChildCount());
		View childView = rootView.getChildAt(0);
		assertTrue(childView instanceof TextView);
		assertEquals("Hello", ((TextView) childView).getText());
	}

	@Test
	public void textSimpleDiffRender() {
		Context c = getContext();
		final ViewGroup rootView = new FrameLayout(c);
		final StringBuilder sb = new StringBuilder("Hello");
		Renderable renderable = new Renderable() {
			public ViewGroup getRootView() {
				return rootView;
			}
			public ViewNode view() {
				return
					v(TextView.class,
						text(sb.toString()));
			}
		};
		Anvil.render(renderable);

		// rootView has one child and the child is a text view and its text is "Hello"
		assertEquals(1, rootView.getChildCount());
		View childView = rootView.getChildAt(0);
		assertTrue(childView instanceof TextView);
		assertEquals("Hello", ((TextView) childView).getText());

		// now child view should remain the same and the text should be changed
		sb.replace(0, sb.length(), "World");
		Anvil.render(renderable);
		assertEquals(1, rootView.getChildCount());
		assertEquals(rootView.getChildAt(0), childView);
		assertEquals("World", ((TextView) childView).getText());
	}
}
