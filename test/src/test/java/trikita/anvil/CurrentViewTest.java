package trikita.anvil;

import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.BaseDSL.*;

// Ensure that in lambdas and in functions the current view is always accessible
public class CurrentViewTest extends AnvilTestBase {
	@Test
	public void testCurrentView() {
		assertNull(Anvil.currentView());
		Anvil.mount(container, () -> {
			assertTrue(Anvil.currentView() instanceof ViewGroup);
			fooLayout(() -> {
				assertEquals(FooLayout.class, Anvil.currentView().getClass());
				barLayout(() -> {
					assertEquals(BarLayout.class, Anvil.currentView().getClass());
					bazView(() -> {
						assertEquals(BazView.class, Anvil.currentView().getClass());
						fooProp("hello");
						assertEquals("hello", ((BazView) Anvil.currentView()).props.get("foo"));
					});
					assertEquals(BarLayout.class, Anvil.currentView().getClass());
				});
				assertEquals(FooLayout.class, Anvil.currentView().getClass());
			});
		});
		assertNull(Anvil.currentView());
	}

	@Test
	public void testCurrentViewJava6Syntax() {
		Anvil.mount(container, new Anvil.Renderable() {
			public void view() {
				assertTrue(Anvil.currentView() instanceof ViewGroup);
				o (fooLayout(),
					assertCurrentViewClass(FooLayout.class),
					o (barLayout(),
						assertCurrentViewClass(BarLayout.class),
						o (bazView(),
							assertCurrentViewClass(BazView.class),
							fooProp("hello"),
							assertFooProp("hello")),
						assertCurrentViewClass(BarLayout.class)),
					assertCurrentViewClass(FooLayout.class));
			}
		});
	}

	private Void assertFooProp(Object value) {
		assertEquals(value, ((TestView) Anvil.currentView()).props.get("foo"));
		return null;
	}

	private Void assertCurrentViewClass(Class c) {
		assertEquals(c, Anvil.currentView().getClass());
		return null;
	}
}

