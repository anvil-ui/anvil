package trikita.anvil;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.Nodes.*;
import static trikita.anvil.v15.Views.*;

public class ViewsTest {

	@Test
	public void testView() {
		ViewNode node = button();
		assertEquals(Button.class, node.viewClass);
		assertEquals(0, node.children.size());
		assertEquals(0, node.attrs.size());
	}

	@Test
	public void testLayout() {
		ViewNode node = linearLayout(button(), textView());
		assertEquals(LinearLayout.class, node.viewClass);
		assertEquals(2, node.children.size());
		assertEquals(0, node.attrs.size());
		assertEquals(Button.class, node.children.get(0).viewClass);
		assertEquals(TextView.class, node.children.get(1).viewClass);
	}

	@Test
	public void testShouldNotCompile() {
		//
		// XXX uncomment these to ensure that compile-type safety is working
		//
		//textView(textView());
		//textView(frameLayout());
	}
}

