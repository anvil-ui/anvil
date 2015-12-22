package trikita.anvil;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.test.UiThreadTest;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.DSL.*;

import trikita.anvil.test.R;

public class XmlLayoutTest extends AnvilTestBase {
	@Test
	@UiThreadTest
	public void testMountOverride() {
		// Renderables mounted to the same viewgroup should replace each other, so
		// only the last one will be called during Anvil.render()
		int calls[] = new int[]{0, 0, 0, 0};
		Anvil.mount(container, () -> {
			calls[0]++;
		});
		Anvil.mount(container, () -> {
			calls[1]++;
			fooView(() -> {});
		});
		FooView foo = (FooView) container.getChildAt(0);
		Anvil.render();
		// This mount should not create another foo view, but instead reuse one from the previous mount
		Anvil.mount(container, () -> {
			calls[2]++;
			fooView(() -> {});
			barView(() -> {});
		});
		Anvil.render();
		Anvil.render();
		Anvil.render();

		assertEquals(foo, container.getChildAt(0));

		assertEquals(1, calls[0]);
		assertEquals(2, calls[1]);
		assertEquals(4, calls[2]);
	}

	@Test
	@UiThreadTest
	public void testViewFromXml() {
		if (!instrumentationTest()) {
			return;
		}
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				fooView(() -> {});
				xml(R.layout.static_layout, () -> {
					backgroundColor(Color.RED);
					barView(() -> {});
				});
				bazView(() -> {});
			});
		});

		FooLayout foo = (FooLayout) container.getChildAt(0);
		assertEquals(LinearLayout.class, foo.getChildAt(1).getClass());

		LinearLayout xmlLayout = (LinearLayout) foo.getChildAt(1);

		assertEquals(Color.RED, ((ColorDrawable) xmlLayout.getBackground()).getColor());

		assertEquals(TextView.class, xmlLayout.getChildAt(0).getClass());
		assertEquals(FrameLayout.class, xmlLayout.getChildAt(1).getClass());
		assertEquals(BarView.class, xmlLayout.getChildAt(2).getClass());
	}

	@Test
	@UiThreadTest
	public void testViewFromXmlWithIds() {
		if (!instrumentationTest()) {
			return;
		}
		Anvil.mount(container, () -> {
			xml(R.layout.static_layout, () -> {
				withId(R.id.header, () -> {
					text("Hello");
				});
				withId(R.id.content, () -> {
					backgroundColor(Color.RED);
					fooView(() -> {});
					barView(() -> {});
				});
			});
		});

		LinearLayout layout = (LinearLayout) container.getChildAt(0);

		assertEquals("Hello", ((TextView) layout.findViewById(R.id.header)).getText());
		assertEquals(Color.RED, ((ColorDrawable) layout.findViewById(R.id.content).getBackground()).getColor());
		assertEquals(2, ((ViewGroup) layout.findViewById(R.id.content)).getChildCount());
	}

	@Test
	@UiThreadTest
	public void testViewWithIds() {
		if (!instrumentationTest()) {
			return;
		}
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				fooView(() -> {
					id(100);
				});
				barView(() -> {
					id(101);
					fooProp("this will be overridden");
				});
				withId(100, () -> {
					fooProp("Hello");
				});
				withId(101, () -> {
					fooProp("this will be overridden, too");
				});
				bazView(() -> {
					id(102);
				});
				withId(101, () -> {
					fooProp("World");
				});
			});
		});

		FooLayout foo = (FooLayout) container.getChildAt(0);

		assertEquals(3, foo.getChildCount());
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BarView.class, foo.getChildAt(1).getClass());
		assertEquals(BazView.class, foo.getChildAt(2).getClass());

		assertEquals("Hello", ((TestView) foo.getChildAt(0)).props.get("foo"));
		assertEquals("World", ((TestView) foo.getChildAt(1)).props.get("foo"));
	}

	@Test
	public void testViewFromXmlWithIdsJava6Syntax() {
		if (!instrumentationTest()) {
			return;
		}
		Anvil.Renderable renderText = new Anvil.Renderable() {
			public void view() {
				text("Hello");
			}
		};
		Anvil.Renderable renderContent = new Anvil.Renderable() {
			public void view() {
				backgroundColor(Color.RED);
				o (fooView());
				o (barView());
			}
		};
		Anvil.mount(container, new Anvil.Renderable() {
			public void view() {
				o (xml(R.layout.static_layout),
					backgroundColor(Color.BLUE),
					withId(R.id.header, renderText),
					withId(R.id.content, renderContent));
			}
		});

		LinearLayout layout = (LinearLayout) container.getChildAt(0);

		assertEquals(Color.BLUE, ((ColorDrawable) layout.getBackground()).getColor());

		assertEquals("Hello", ((TextView) layout.findViewById(R.id.header)).getText());
		assertEquals(Color.RED, ((ColorDrawable) layout.findViewById(R.id.content).getBackground()).getColor());
		assertEquals(2, ((ViewGroup) layout.findViewById(R.id.content)).getChildCount());
	}

	// Instrumentation tests running on the device have real context, while unit tests return null
	private boolean instrumentationTest() {
		return getContext() != null;
	}
}
