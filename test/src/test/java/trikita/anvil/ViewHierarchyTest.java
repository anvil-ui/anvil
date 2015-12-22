package trikita.anvil;

import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.BaseDSL.*;

// Test that view hierarchy is created correctly when renderable is mounted.
// This means views are created from the given classes and their attributes are
// set to the given values.
public class ViewHierarchyTest extends AnvilTestBase {
	@Test
	public void testNoViews() {
		Anvil.mount(container, () -> {});
		assertEquals(0, container.getChildCount());
	}

	@Test
	public void testSingleStaticView() {
		Anvil.mount(container, () -> {
			fooView(() -> {
				fooProp("Hello");
				barProp(123);
			});
		});
		assertEquals(1, container.getChildCount());
		assertEquals(FooView.class, container.getChildAt(0).getClass());

		FooView foo = (FooView) container.getChildAt(0);
		assertEquals("Hello", foo.props.get("foo"));
		assertEquals(123, foo.props.get("bar"));
	}

	@Test
	public void testSingleStaticViewJava6Syntax() {
		Anvil.mount(container, new Anvil.Renderable() {
			public void view() {
				o (fooView(),
					fooProp("Hello"),
					barProp(123));
			}
		});
		assertEquals(1, container.getChildCount());
		assertEquals(FooView.class, container.getChildAt(0).getClass());

		FooView foo = (FooView) container.getChildAt(0);
		assertEquals("Hello", foo.props.get("foo"));
		assertEquals(123, foo.props.get("bar"));
	}

	@Test
	public void testMultipleViews() {
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				barLayout(() -> {
					fooView(() -> {});
					barView(() -> {});
					bazView(() -> {});
					fooView(() -> {});
				});
				bazView(() -> {});
			});
		});
		assertEquals(1, container.getChildCount());
		assertEquals(FooLayout.class, container.getChildAt(0).getClass());

		FooLayout foo = (FooLayout) container.getChildAt(0);

		assertEquals(2, foo.getChildCount());
		assertEquals(BarLayout.class, foo.getChildAt(0).getClass());
		assertEquals(BazView.class, foo.getChildAt(1).getClass());

		BarLayout bar = (BarLayout) foo.getChildAt(0);

		assertEquals(4, bar.getChildCount());
		assertEquals(FooView.class, bar.getChildAt(0).getClass());
		assertEquals(BarView.class, bar.getChildAt(1).getClass());
		assertEquals(BazView.class, bar.getChildAt(2).getClass());
		assertEquals(FooView.class, bar.getChildAt(3).getClass());
	}

	@Test
	public void testMultipleViewsJava6Syntax() {
		Anvil.mount(container, new Anvil.Renderable() {
			public void view() {
				o (fooLayout(),
					o (barLayout(),
						o (fooView()),
						o (barView()),
						o (bazView()),
						o (fooView())),
					o (bazView()));
			}
		});
		assertEquals(1, container.getChildCount());
		assertEquals(FooLayout.class, container.getChildAt(0).getClass());

		FooLayout foo = (FooLayout) container.getChildAt(0);

		assertEquals(2, foo.getChildCount());
		assertEquals(BarLayout.class, foo.getChildAt(0).getClass());
		assertEquals(BazView.class, foo.getChildAt(1).getClass());

		BarLayout bar = (BarLayout) foo.getChildAt(0);

		assertEquals(4, bar.getChildCount());
		assertEquals(FooView.class, bar.getChildAt(0).getClass());
		assertEquals(BarView.class, bar.getChildAt(1).getClass());
		assertEquals(BazView.class, bar.getChildAt(2).getClass());
		assertEquals(FooView.class, bar.getChildAt(3).getClass());
	}
}
