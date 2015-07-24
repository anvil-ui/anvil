package trikita.anvil;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.test.AndroidTestCase;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.v10.Attrs.*;

public class BackstackTest extends AndroidTestCase {

	//
	// A simple view class that should keep its state
	//
	public static class ViewWithState extends View {
		public int state = 0;
		public ViewWithState(Context c) {
			super(c);
			setId(1);
		}

		public ViewWithState withState(int state) {
			this.state = state;
			return this;
		}

		@Override
		public Parcelable onSaveInstanceState() {
			Bundle b = new Bundle();
			b.putParcelable("instanceState", super.onSaveInstanceState());
			b.putInt("state", state);
			return b;
		}

		@Override
		public void onRestoreInstanceState(Parcelable p) {
			Bundle b = (Bundle) p;
			this.state = b.getInt("state");
			super.onRestoreInstanceState(b.getParcelable("instanceState"));
		}
	}

	//
	// A renderable wrapper for a simple class
	//
	public static class WrapperView extends RenderableView {
		public int state = 0;
		public WrapperView(Context c) {
			super(c);
		}

		public WrapperView withState(int state) {
			this.state = state;
			return this;
		}

		@Override
		public Parcelable onSaveInstanceState() {
			Bundle b = new Bundle();
			b.putParcelable("instanceState", super.onSaveInstanceState());
			b.putInt("state", state);
			return b;
		}

		@Override
		public void onRestoreInstanceState(Parcelable p) {
			Bundle b = (Bundle) p;
			this.state = b.getInt("state");
			super.onRestoreInstanceState(b.getParcelable("instanceState"));
		}

		public ViewNode view() {
			return v(ViewWithState.class, id(12345));
		}
	}

	@Test
	public void testSingleViewBackstack() {
		final List<View> views = new ArrayList<>();

		Backstack backstack = new Backstack(getContext(), new Backstack.Listener() {
			public void setContentView(View v) {
				views.add(v);
			}
		});
		backstack.navigate(new ViewWithState(getContext()));
		assertEquals(views.size(), 1);
		assertTrue(views.get(0) instanceof ViewWithState);
		assertEquals(backstack.back(), false);
	}

	@Test
	public void testViewSaveRestore() {
		Bundle b = new Bundle();
		final View[] viewHolder = new View[1];
		Backstack.Listener listener = new Backstack.Listener() {
			public void setContentView(View v) {
				viewHolder[0] = v;
			}
		};

		Backstack backstack = new Backstack(getContext(), listener);
		backstack.navigate(new ViewWithState(getContext()).withState(1));
		backstack.navigate(new ViewWithState(getContext()).withState(2));
		backstack.save(b);

		backstack = new Backstack(getContext(), listener);
		backstack.load(b);
		assertEquals(((ViewWithState) viewHolder[0]).state, 2);
		backstack.back();
		assertEquals(((ViewWithState) viewHolder[0]).state, 1);
	}

	@Test
	public void testViewModifySave() {
		Bundle b = new Bundle();
		final View[] viewHolder = new View[1];
		Backstack.Listener listener = new Backstack.Listener() {
			public void setContentView(View v) {
				viewHolder[0] = v;
			}
		};

		Backstack backstack = new Backstack(getContext(), listener);
		ViewWithState v = new ViewWithState(getContext()).withState(1);
		backstack.navigate(v);
		v.state = 5;
		backstack.save(b);

		backstack = new Backstack(getContext(), listener);
		backstack.load(b);
		assertEquals(((ViewWithState) viewHolder[0]).state, 5);
	}

	@Test
	public void testNestedViews() {
		Bundle b = new Bundle();
		final View[] viewHolder = new View[1];
		Backstack.Listener listener = new Backstack.Listener() {
			public void setContentView(View v) {
				viewHolder[0] = v;
			}
		};
		Backstack backstack = new Backstack(getContext(), listener);
		WrapperView w = new WrapperView(getContext()).withState(1);
		Anvil.render(w);
		backstack.navigate(w);
		w.state = 123;
		ViewWithState v = (ViewWithState) w.getChildAt(0);
		v.state = 42;
		backstack.save(b);

		backstack = new Backstack(getContext(), listener);
		backstack.load(b);

		w = (WrapperView) viewHolder[0];
		assertEquals(w.state, 123);

		v = (ViewWithState) w.getChildAt(0);
		assertEquals(v.state, 42);
	}
}
