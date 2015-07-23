package trikita.anvil;

import android.content.Context;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

import static trikita.anvil.Nodes.*;
import static trikita.anvil.v15.Attrs.*;

@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class AnvilTest {

	@Mock
	Context mContext;

	@Test
	public void testSimpleInflate() {
		ViewNode viewNode = v(Button.class);
		View view = Anvil.inflateNode(mContext, viewNode, null, new ContainerMount());
		assertEquals(view.getClass(), Button.class);
	}

	public static class ContainerMount implements Anvil.Mount {
		private View mView;
		public void set(View v) {
			mView = v;
		}
		public View get() {
			return mView;
		}
	}

	public static class TestView extends View {
		private Object mTag = null;
		private int mTagUpdatesCount = 0;

		public TestView(Context c) {
			super(c);
		}

		@Override
		public void setTag(Object obj) {
			mTag = obj;
			mTagUpdatesCount++;
		}

		@Override
		public Object getTag() {
			return mTag;
		}

		public int getTagUpdatesCount() {
			return mTagUpdatesCount;
		}
	}

	public static class TestLayout extends FrameLayout {
		private ArrayList<View> mViews = new ArrayList<>();

		public TestLayout(Context c) {
			super(c);
		}

		@Override
		public int getChildCount() {
			return mViews.size();
		}


		@Override
		public void addView(View v, int index) {
			mViews.add(index, v);
		}

		@Override
		public void removeViewAt(int index) {
			mViews.remove(index);
		}

		@Override
		public View getChildAt(int index) {
			return mViews.get(index);
		}

		@Override
		public void removeViews(int index, int count) {
			for (int i = index+count; i >= index; i--) {
				mViews.remove(i);
			}
		}
	}
	
	@Test
	public void testSimpleInflateWithAttrs() {
		ViewNode viewNode =
			v(TestView.class,
				tag("hello"));

		TestView view = (TestView) Anvil.inflateNode(mContext, viewNode, null, new ContainerMount());
		assertEquals("hello", view.getTag());
		assertEquals(1, view.getTagUpdatesCount());
	}

	@Test
	public void testSimpleInflateWithChildViews() {
		ViewNode viewNode =
			v(TestLayout.class,
				v(TestView.class,
					tag("foo")),
				v(TestView.class,
					tag("bar")));

		TestLayout layout = (TestLayout) Anvil.inflateNode(mContext, viewNode, null, new ContainerMount());
		assertEquals(2, layout.getChildCount());
		assertEquals(TestView.class, layout.getChildAt(0).getClass());
		assertEquals("foo", layout.getChildAt(0).getTag());
		assertEquals(TestView.class, layout.getChildAt(1).getClass());
		assertEquals("bar", layout.getChildAt(1).getTag());
	}

	@Test
	public void testInflateAttr() {
		ViewNode a =
			v(TestView.class,
					tag("hello"));

		ViewNode b =
			v(TestView.class,
					tag("hello"));

		ViewNode c =
			v(TestView.class,
					tag("world"));

		Anvil.Mount mount = new ContainerMount();
		TestView first = (TestView) Anvil.inflateNode(mContext, a, null, mount);
		TestView second = (TestView) Anvil.inflateNode(mContext, b, a, mount);

		assertEquals(second, first); // View is the same
		assertEquals(1, second.getTagUpdatesCount()); // Tag was set only once

		TestView third = (TestView) Anvil.inflateNode(mContext, c, b, mount);
		assertEquals(third, first); // View is the same
		assertEquals(2, third.getTagUpdatesCount()); // Tag was updated once again
	}

	@Test
	public void testInflateViews() {
		ViewNode a =
			v(TestLayout.class);

		ViewNode b =
			v(TestLayout.class,
				v(TestView.class,
					tag("foo")));

		ViewNode c =
			v(TestLayout.class,
				v(TestView.class,
					tag("bar")));

		Anvil.Mount mount = new ContainerMount();
		TestLayout first = (TestLayout) Anvil.inflateNode(mContext, a, null, mount);
		assertEquals(0, first.getChildCount());

		TestLayout second = (TestLayout) Anvil.inflateNode(mContext, b, a, mount);

		assertEquals(second, first);
		assertEquals(1, second.getChildCount());
		TestView child = (TestView) second.getChildAt(0);
		assertEquals("foo", child.getTag());

		TestLayout third = (TestLayout) Anvil.inflateNode(mContext, c, b, mount);
		assertEquals(third, first);
		assertEquals(1, third.getChildCount());
		assertEquals(child, third.getChildAt(0));
		assertEquals("bar", child.getTag());
	}
}
