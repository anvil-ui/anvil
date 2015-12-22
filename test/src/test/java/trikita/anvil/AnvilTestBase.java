package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static trikita.anvil.BaseDSL.*;

public class AnvilTestBase  extends InstrumentationTestCase { //AndroidTestCase {

	protected TestLayout container;

	protected Context getContext() {
		if (getInstrumentation() == null) {
			return null;
		}
		return getInstrumentation().getContext();
	}

	@Test public void testNoop() { /* Just to avoid "no tests found" assertion error */ }
	
	@Before
	public void setUp() {
		this.container = new TestLayout(getContext());
	}

	@After
	public void tearDown() {
		Anvil.unmount(this.container);
		this.container.removeViews(0, this.container.getChildCount());
	}

	// View mock which contains tag
	public static class TestView extends View {
		public final Map<String, Object> props = new HashMap<>();

		public TestView(Context c) { super(c); }
		public TestView(Context c, AttributeSet attrs) { super(c, attrs); }
	}
	public static class FooView extends TestView {
		public FooView(Context c) { super(c); }
		public FooView(Context c, AttributeSet attrs) { super(c, attrs); }
	}
	public static class BarView extends TestView {
		public BarView(Context c) { super(c); }
		public BarView(Context c, AttributeSet attrs) { super(c, attrs); }
	}
	public static class BazView extends TestView {
		public BazView(Context c) { super(c); }
		public BazView(Context c, AttributeSet attrs) { super(c, attrs); }
	}

	// ViewGroup mock which contains tag and list of child views
	public static class TestLayout extends FrameLayout {
		public final Map<String, Object> props = new HashMap<>();
		private List<View> children = new ArrayList<View>();

		public TestLayout(Context c) { super(c); }
		public TestLayout(Context c, AttributeSet attrs) { super(c, attrs); }

		@Override
		public int getChildCount() {
			return children.size();
		}

		@Override
		public View getChildAt(int index) {
			return children.get(index);
		}

		@Override
		public int indexOfChild(View v) {
			return children.indexOf(v);
		}

		@Override
		public void addView(View v, int index) {
			this.children.add(index, v);
			super.addView(v, index);
		}

		@Override
		public void removeView(View v) {
			this.children.remove(v);
			super.removeView(v);
		}

		@Override
		public void removeViews(int start, int count) {
			if (count > 0) {
				this.children.subList(start, start+count).clear();
			}
			super.removeViews(start, count);
		}
	}
	public static class FooLayout extends TestLayout {
		public FooLayout(Context c) { super(c); }
		public FooLayout(Context c, AttributeSet attrs) { super(c, attrs); }
	}
	public static class BarLayout extends TestLayout {
		public BarLayout(Context c) { super(c); }
		public BarLayout(Context c, AttributeSet attrs) { super(c, attrs); }
	}

	// Helper methods for views
	public static ViewClassResult fooView() { return v(FooView.class); }
	public static ViewClassResult barView() { return v(BarView.class); }
	public static ViewClassResult bazView() { return v(BazView.class); }
	public static Void fooView(Anvil.Renderable r) { return v(FooView.class, r); }
	public static Void barView(Anvil.Renderable r) { return v(BarView.class, r); }
	public static Void bazView(Anvil.Renderable r) { return v(BazView.class, r); }

	// Helper methods for layouts
	public static ViewClassResult fooLayout() { return v(FooLayout.class); }
	public static ViewClassResult barLayout() { return v(BarLayout.class); }
	public static Void fooLayout(Anvil.Renderable r) { return v(FooLayout.class, r); }
	public static Void barLayout(Anvil.Renderable r) { return v(BarLayout.class, r); }

	// Helper utility for test view methods
	public static Void fooProp(Object value) {
		return attr(PropFunc.instance, new AbstractMap.SimpleImmutableEntry<>("foo", value));
	}
	public static Void barProp(Object value) {
		return attr(PropFunc.instance, new AbstractMap.SimpleImmutableEntry<>("bar", value));
	}

	// AttrFunc implementation for props<k,v>
	public static class PropFunc implements Anvil.AttrFunc<Map.Entry<String, Object>> {
		private final static PropFunc instance = new PropFunc();
		public void apply(View v, Map.Entry<String, Object> x, Map.Entry<String, Object> old) {
			if (v instanceof TestView) {
				((TestView) v).props.put(x.getKey(), x.getValue());
			} else if (v instanceof TestLayout) {
				((TestLayout) v).props.put(x.getKey(), x.getValue());
			}
		}
	}
}
