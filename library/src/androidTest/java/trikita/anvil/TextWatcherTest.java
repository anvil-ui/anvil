package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import static junit.framework.Assert.*;

import static trikita.anvil.v10.Attrs.*;

public class TextWatcherTest extends AndroidTestCase {

	public void testSimpleTextWatcher() {
		final ViewGroup rootView = new FrameLayout(getContext());
		final StringBuilder sb = new StringBuilder("");
		final SimpleTextWatcher watcher = new SimpleTextWatcher() {
			public void onTextChanged(String s) {
				sb.append(s);
			}
		};
		Renderable renderable = new Renderable() {
			public ViewGroup getRootView() {
				return rootView;
			}
			public ViewNode view() {
				return
					v(EditText.class,
						onTextChanged(watcher));
			}
		};
		System.out.println("Hello, text");

		Anvil.render(renderable);
		EditText text = (EditText) rootView.getChildAt(0);
		text.append("foo");
		assertEquals(sb.toString(), "foo");
		sb.replace(0, sb.length(), "");
		text.append("bar");
		assertEquals(sb.toString(), "foobar");
	}
}

