package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.DSL.*;
import static trikita.anvil.TestUtil.*;

public class RenderBenchmarkTest extends AndroidTestCase {

	ViewGroup rootView;
	
	// Static layout, 3 nesting levels, 8 views, 16 attributes
	Anvil.Renderable staticView = new Anvil.Renderable() {
		public void view() {
			o (v(TestLayout.class),
					dummy(0),
					o (v(TestView.class),
						dummy(0xffffffff),
						dummy(0),
						dummy(1)),
					o (v(TestView.class),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(1)),
					o (v(TestLayout.class),
						o (v(TestLayout.class),
							o (v(TestView.class),
								dummy(0xffffffff),
								dummy(0xffffffff),
								dummy(1)),
							o (v(TestView.class),
								dummy(0xffffffff),
								dummy(1)),
							o (v(TestView.class),
								dummy(0xffffffff),
								dummy(1)))));
		}
	};

	// Dynamic layout, 3 nesting levels), 8 views), 16 attributes
	// 2 attributes can be changed manually, one is changed on every render cycle
	// This means 12.5% of nodes/attributes are re-rendered on each cycle
	Object dynamicValue;
	Anvil.Renderable dynamicView = new Anvil.Renderable() {
		public void view() {
			x (v(TestLayout.class),
					dummy(dynamicValue),
					x (v(TestView.class),
						dummy(0xffffffff),
						dummy(0),
						dummy(1)),
					x (v(TestView.class),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(0xffffffff),
						dummy(dynamicValue)),
					x (v(TestLayout.class),
						x (v(TestLayout.class),
							x (v(TestView.class),
								dummy(0xffffffff),
								dummy(0xffffffff),
								dummy(1)),
							x (v(TestView.class),
								dummy(0xffffffff),
								dummy(Math.random())),
							x (v(TestView.class),
								dummy(0xffffffff),
								dummy(1)))));
		}
	};

	Anvil.Renderable dynamicLambdaView = () -> {
		testLayout(() -> {
			dummy(dynamicValue);
			testView(() -> {
				dummy(0xffffffff);
				dummy(0);
				dummy(1);
			});
			testView(() -> {});
			testView(() -> {
				dummy(0xffffffff);
				dummy(0xffffffff);
				dummy(0xffffffff);
				dummy(0xffffffff);
				dummy(dynamicValue);
			});
			testLayout(() -> {
				testLayout(() -> {
					testView(() -> {
						dummy(0xffffffff);
						dummy(0xffffffff);
						dummy(1);
					});
					testView(() -> {
						dummy(0xffffffff);
						dummy(Math.random());
					});
					testView(() -> {
						dummy(0xffffffff);
						dummy(1);
					});
				});
			});
		});
	};

	@Test
	public void testStaticRender() {
		rootView = new TestLayout(getContext());
		B b = new B("Benchmark/RenderStatic");
		Anvil.Mount m = Anvil.mount(rootView, staticView);
		while (!b.done()) {
			Anvil.render();
		}
		Anvil.unmount(m);
		System.out.println(b.report());
	}

	@Test
	public void testDynamicRender() {
		rootView = new TestLayout(getContext());

		Anvil.Mount m = Anvil.mount(rootView, dynamicView);
		B b = new B("Benchmark/RenderDynamic");
		int i = 0;
		while (!b.done()) {
			dynamicValue = ++i;
			Anvil.render();
		}
		Anvil.unmount(m);
		System.out.println(b.report());
	}

	@Test
	public void testLambdaDynamicRender() {
		rootView = new TestLayout(getContext());
		Anvil.Mount m = Anvil.mount(rootView, dynamicLambdaView);
		B b = new B("Benchmark/RenderDynamicLambda");
		int i = 0;
		while (!b.done()) {
			dynamicValue = ++i;
			Anvil.render();
		}
		Anvil.unmount(m);
		System.out.println(b.report());
	}
}

