package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import static junit.framework.Assert.*;

import static trikita.anvil.v10.Attrs.*;

public class RenderBenchmarkTest extends AndroidTestCase {

	private static class User {
		public String name = "";
	}
	
	public void testRender() {
		Context c = getContext();
		final ViewGroup rootView = new FrameLayout(c);
		final User user = new User();
		Renderable renderable = new Renderable() {
			public ViewGroup getRootView() {
				return rootView;
			}
			public ViewNode view() {
				return
					v(FrameLayout.class,
						size(FILL, FILL),
						v(TextView.class,
								textColor(0xffffffff),
								textSize(14),
								backgroundColor(0),
								text("Hello " + user.name)));
			}
		};
		B b = new B("Benchmark/Render");
		int i = 0;
		while (!b.done()) {
			user.name = "User #"+(i++);
			Anvil.render(renderable);
		}
		System.out.println(b.report());

		b = new B("Benchmark/Render when untouched");
		while (!b.done()) {
			Anvil.render(renderable);
		}
		System.out.println(b.report());

		b = new B("Benchmark/View node construction");
		while (!b.done()) {
			user.name = "User #"+(i++);
			renderable.view();
		}
		System.out.println(b.report());

		b = new B("Benchmark/View node construction when untouched");
		while (!b.done()) {
			renderable.view();
		}
		System.out.println(b.report());

		b = new B("Benchmark/String concat");
		while (!b.done()) {
			user.name = "User #"+(i++);
		}
		System.out.println(b.report());
	}
}

