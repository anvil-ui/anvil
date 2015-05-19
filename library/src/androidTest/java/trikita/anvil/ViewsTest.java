package trikita.anvil;

import android.test.AndroidTestCase;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import static junit.framework.Assert.*;

import static trikita.anvil.v10.Attrs.*;
import static trikita.anvil.v10.Views.*;
import android.content.Context;
import android.view.View;

public class ViewsTest extends AndroidTestCase {

	// This tests the Views.java syntax sugar for standard views/layouts
	public void testViewsSyntaxSugar() {
		Context c = getContext();
		final ViewGroup rootView = new FrameLayout(c);
		Renderable renderable = new Renderable() {
			public ViewGroup getRootView() {
				return rootView;
			}
			public void shouldCompile() {
				textView();
				frameLayout();
			}
			public ViewNode view() {
				return frameLayout(size(FILL, FILL), textView(text("Hello")));
			}
		};
		Anvil.render(renderable);
		assertEquals(1, rootView.getChildCount());
		View childView = rootView.getChildAt(0);
		assertTrue(childView instanceof FrameLayout);
		View innerView = ((ViewGroup) childView).getChildAt(0);
		assertTrue(innerView instanceof TextView);
		assertEquals("Hello", ((TextView) innerView).getText());
	}
}
