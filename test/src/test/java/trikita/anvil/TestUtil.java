package trikita.anvil;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import static trikita.anvil.DSL.*;

public final class TestUtil {

	private static int ID = 0;
	private static int nextId() {
		return ID++;
	}

	// View mock which contains tag
	public static class TestView extends View {
		private Object tag;
		private int id = nextId();

		public TestView(Context c) {
			super(c);
		}

		@Override
		public void setTag(Object tag) {
			this.tag = tag;
			super.setTag(tag);
		}

		@Override
		public Object getTag() {
			return this.tag;
		}

		@Override
		public String toString() {
			return "TestView#"+this.id;
		}
	}

	// ViewGroup mock which contains tag and list of child views
	public static class TestLayout extends FrameLayout {
		private Object tag;
		private List<View> children = new ArrayList<View>();
		private int id = nextId();

		public TestLayout(Context c) {
			super(c);
		}

		@Override
		public void setTag(Object tag) {
			this.tag = tag;
			super.setTag(tag);
		}

		@Override
		public Object getTag() {
			return this.tag;
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
			for (int i = start+count-1; i >= start; i++) {
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
	public static Void testView(Renderable r) {
		return v(TestView.class, r);
	}
	public static ViewClassResult testLayout() {
		return v(TestLayout.class);
	}
	public static Void testLayout(Renderable r) {
		return v(TestLayout.class, r);
	}

	// Helper utility to set tag
	public static Void tag(Object value) {
		return attr(TagFunc.instance, value);
	}

	// AttrFunc implementation for setTag()
	private static class TagFunc implements AttrFunc<Object> {
		private static final TagFunc instance = new TagFunc();
		public void apply(View v, Object x, Object old) {
			v.setTag(x);
		}
	}
}
