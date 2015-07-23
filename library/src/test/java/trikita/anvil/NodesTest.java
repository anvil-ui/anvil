package trikita.anvil;

import android.graphics.Color;
import android.widget.Button;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.Nodes.*;
import static trikita.anvil.v15.Attrs.*;
import android.widget.FrameLayout;
import java.util.ArrayList;

public class NodesTest {

	@Test
	public void testViewSimple() {
		ViewNode node = v(Button.class);
		assertEquals(Button.class, node.viewClass);
		assertEquals(0, node.children.size());
		assertEquals(0, node.attrs.size());
	}

	@Test
	public void testViewWithAttrs() {
		ViewNode node =
			v(Button.class,
				text("hello"),
				backgroundColor(Color.BLACK));
		assertEquals(Button.class, node.viewClass);
		assertEquals(0, node.children.size());
		assertEquals(2, node.attrs.size());
		assertTrue(node.attrs.get(0) instanceof SimpleAttrNode);
		assertTrue(node.attrs.get(1) instanceof SimpleAttrNode);
	}

	@Test
	public void testViewNested() {
		ViewNode node =
			v(FrameLayout.class,
				backgroundColor(Color.BLACK),
				v(Button.class),
				v(Button.class,
					text("Click me")));

		assertEquals(FrameLayout.class, node.viewClass);
		assertEquals(2, node.children.size());
		assertEquals(1, node.attrs.size());

		assertEquals(Button.class, node.children.get(0).viewClass);
		assertEquals(0, node.children.get(0).attrs.size());

		assertEquals(Button.class, node.children.get(1).viewClass);
		assertEquals(1, node.children.get(1).attrs.size());
	}

	@Test
	public void testViewAddAttrs() {
		ViewNode node =
			v(Button.class, text("Click me"))
				.addAttrs(new AttrNode[]{backgroundColor(Color.BLACK)})
				.addAttrs(new ArrayList<AttrNode>() {{
					add(textSize(12));
					add(textColor(Color.WHITE));
				}})
				.addAttrs(tag(0), enabled(false));

		assertEquals(6, node.attrs.size());
	}

	@Test
	public void testViewAddViews() {
		ViewNode node =
			v(FrameLayout.class,
				v(Button.class))
				.addViews(new ViewNode[]{v(Button.class)})
				.addViews(new ArrayList<ViewNode>() {{
					add(v(Button.class));
					add(v(Button.class));
				}})
				.addViews(v(Button.class), v(Button.class));

		assertEquals(6, node.children.size());
	}

	@Test
	public void testShouldNotCompile() {
		//
		// XXX uncomment these to ensure that compile-type safety is working
		//
		//v(TextView.class, v(FrameLayout.class));
		//v(TextView.class, v(TextView.class));
		//textView(textView());
		//textView(frameLayout());
		System.out.println("view with mockito " + new android.view.View(null));
	}
}
