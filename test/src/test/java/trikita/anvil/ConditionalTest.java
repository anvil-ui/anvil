package trikita.anvil;

import android.test.UiThreadTest;
import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

// Renderables may contain conditional expressions or loops.
// Ensure that views can be added/removed depending on the condition.
// Ensure that attributes can be added/removed depending on the condition.
public class ConditionalTest extends AnvilTestBase {
	@Test
	@UiThreadTest
	public void testAddRemoveView() {
		boolean flag[] = new boolean[]{true};
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				fooView(() -> {});
				if (flag[0]) {
					barView(() -> {});
				}
				bazView(() -> {});
			});
		});
		FooLayout foo = (FooLayout) container.getChildAt(0);
		assertEquals(3, foo.getChildCount());
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BarView.class, foo.getChildAt(1).getClass());
		assertEquals(BazView.class, foo.getChildAt(2).getClass());

		flag[0] = false;
		Anvil.render();

		assertEquals(2, foo.getChildCount());
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BazView.class, foo.getChildAt(1).getClass());

		flag[0] = true;
		Anvil.render();

		assertEquals(3, foo.getChildCount());
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BarView.class, foo.getChildAt(1).getClass());
		assertEquals(BazView.class, foo.getChildAt(2).getClass());
	}

	@Test
	@UiThreadTest
	public void testReplaceView() {
		boolean flag[] = new boolean[]{true};
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				// fooView should actually be the same object, because it has identical
				// declaration in both branches
				if (flag[0]) {
					fooView(() -> {});
					barView(() -> {});
				} else {
					fooView(() -> {});
					bazView(() -> {});
				}
			});
		});
		FooLayout foo = (FooLayout) container.getChildAt(0);
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BarView.class, foo.getChildAt(1).getClass());
		FooView ref = (FooView) foo.getChildAt(0);

		flag[0] = false;
		Anvil.render();

		assertEquals(ref, foo.getChildAt(0));
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BazView.class, foo.getChildAt(1).getClass());

		flag[0] = true;
		Anvil.render();

		assertEquals(ref, foo.getChildAt(0));
		assertEquals(FooView.class, foo.getChildAt(0).getClass());
		assertEquals(BarView.class, foo.getChildAt(1).getClass());
	}

	@Test
	@UiThreadTest
	public void testAddRemoveAttribute() {
		boolean flag[] = new boolean[]{true};
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				fooView(() -> {
					fooProp("a");
					if (!flag[0]) {
						barProp("b");
					}
				});
			});
		});

		FooView foo = (FooView) ((ViewGroup) container.getChildAt(0)).getChildAt(0);
		assertEquals("a", foo.props.get("foo"));
		assertNull(foo.props.get("bar"));

		flag[0] = false;
		Anvil.render();

		assertEquals("b", foo.props.get("bar"));

		flag[0] = true;
		Anvil.render();

		// YES, the view will keep its properties, they won't be restored to the previous values
		assertEquals("b", foo.props.get("bar"));
	}

	@Test
	@UiThreadTest
	public void testViewsInALoop() {
		int count[] = new int[]{4};
		Anvil.mount(container, () -> {
			fooLayout(() -> {
				barView(() -> {});
				for (int i = 0; i < count[0]; i++) {
					final int value = i;
					fooView(() -> {
						fooProp(value);
					});
				}
				bazView(() -> {});
			});
		});

		// Try different count values to add/remove views
		for (int c : new int[]{4, 2, 5, 0, 100, 1}) {
			count[0] = c;
			Anvil.render();
			FooLayout foo = (FooLayout) container.getChildAt(0);
			assertEquals(c+2, foo.getChildCount());
			assertEquals(BarView.class, foo.getChildAt(0).getClass());
			for (int i = 0; i < c; i++) {
				assertEquals(FooView.class, foo.getChildAt(i+1).getClass());
				assertEquals(i, ((FooView) foo.getChildAt(i+1)).props.get("foo"));
			}
			assertEquals(BazView.class, foo.getChildAt(c+1).getClass());
		}
	}
}

