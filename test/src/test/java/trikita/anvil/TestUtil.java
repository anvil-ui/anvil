package trikita.anvil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import static trikita.anvil.BaseDSL.*;

public final class TestUtil {

	private static int ID = 0;
	private static int nextId() {
		return ID++;
	}

	// View mock which contains tag
	public static class TestView extends View {
		private Object dummy;
		private int id = nextId();

		public TestView(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public void setDummy(Object dummy) {
			this.dummy = dummy;
		}

		public Object getDummy() {
			return this.dummy;
		}

		@Override
		public String toString() {
			return "TestView#"+this.id;
		}
	}

	// ViewGroup mock which contains tag and list of child views
	public static class TestLayout extends FrameLayout {
		private Object dummy;
		private List<View> children = new ArrayList<View>();
		private int id = nextId();

		public TestLayout(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public void setDummy(Object dummy) {
			this.dummy = dummy;
		}

		public Object getDummy() {
			return this.dummy;
		}

		@Override
		public int getChildCount() {
			return children.size();
		}

		@Override
		public View getChildAt(int index) {
			return children.get(index);
		}

		@Override
		public void addView(View v, int index) {
			this.children.add(index, v);
			super.addView(v, index);
		}

		@Override
		public void removeViewAt(int index) {
			this.children.remove(index);
			super.removeViewAt(index);
		}

		@Override
		public void removeViews(int start, int count) {
			for (int i = start+count-1; i >= start; i--) {
				removeViewAt(i);
			}
			super.removeViews(start, count);
		}

		@Override
		public String toString() {
			return "TestLayout#" + this.id;
		}
	}

	// Helper methods for view and layout
	public static ViewClassResult testView() {
		return v(TestView.class);
	}
	public static Void testView(Anvil.Renderable r) {
		return v(TestView.class, r);
	}
	public static ViewClassResult testLayout() {
		return v(TestLayout.class);
	}
	public static Void testLayout(Anvil.Renderable r) {
		return v(TestLayout.class, r);
	}

	// Helper utility to set tag
	public static Void dummy(Object value) {
		return attr(DummyFunc.instance, value);
	}

	// AttrFunc implementation for setTag()
	public static class DummyFunc implements Anvil.AttrFunc<Object> {
		private static final DummyFunc instance = new DummyFunc();
		public void apply(View v, Object x, Object old) {
			if (v instanceof TestView) {
				((TestView) v).setDummy(x);
			} else if (v instanceof TestLayout) {
				((TestLayout) v).setDummy(x);
			}
		}
	}
}
