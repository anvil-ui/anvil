package trikita.anvil;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;

import static org.junit.Assert.*;

import static trikita.anvil.DSL.*;

public class RenderBenchmarkTest extends AnvilTestBase {

	// Static layout, 3 nesting levels, 8 views, 16 attributes
	Anvil.Renderable staticView = new Anvil.Renderable() {
		public void view() {
			o (v(TestLayout.class),
					fooProp(0),
					o (v(TestView.class),
						fooProp(0xffffffff),
						fooProp(0),
						fooProp(1)),
					o (v(TestView.class),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(1)),
					o (v(TestLayout.class),
						o (v(TestLayout.class),
							o (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(0xffffffff),
								fooProp(1)),
							o (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(1)),
							o (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(1)))));
		}
	};

	// Dynamic layout, 3 nesting levels), 8 views), 16 attributes
	// 2 attributes can be changed manually, one is changed on every render cycle
	// This means 12.5% of nodes/attributes are re-rendered on each cycle
	Object dynamicValue;
	Anvil.Renderable dynamicView = new Anvil.Renderable() {
		public void view() {
			x (v(TestLayout.class),
					fooProp(dynamicValue),
					x (v(TestView.class),
						fooProp(0xffffffff),
						fooProp(0),
						fooProp(1)),
					x (v(TestView.class),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(0xffffffff),
						fooProp(dynamicValue)),
					x (v(TestLayout.class),
						x (v(TestLayout.class),
							x (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(0xffffffff),
								fooProp(1)),
							x (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(Math.random())),
							x (v(TestView.class),
								fooProp(0xffffffff),
								fooProp(1)))));
		}
	};

	Anvil.Renderable dynamicLambdaView = () -> {
		fooLayout(() -> {
			fooProp(dynamicValue);
			fooView(() -> {
				fooProp(0xffffffff);
				fooProp(0);
				fooProp(1);
			});
			fooView(() -> {});
			fooView(() -> {
				fooProp(0xffffffff);
				fooProp(0xffffffff);
				fooProp(0xffffffff);
				fooProp(0xffffffff);
				fooProp(dynamicValue);
			});
			fooLayout(() -> {
				fooLayout(() -> {
					fooView(() -> {
						fooProp(0xffffffff);
						fooProp(0xffffffff);
						fooProp(1);
					});
					fooView(() -> {
						fooProp(0xffffffff);
						fooProp(Math.random());
					});
					fooView(() -> {
						fooProp(0xffffffff);
						fooProp(1);
					});
				});
			});
		});
	};

	@Test
	@UiThreadTest
	public void testStaticRender() {
		B b = new B("Benchmark/RenderStatic");
		Anvil.mount(container, staticView);
		while (!b.done()) {
			Anvil.render();
		}
		System.out.println(b.report());
	}

	@Test
	@UiThreadTest
	public void testDynamicRender() {
		Anvil.mount(container, dynamicView);
		B b = new B("Benchmark/RenderDynamic");
		int i = 0;
		while (!b.done()) {
			dynamicValue = ++i;
			Anvil.render();
		}
		System.out.println(b.report());
	}

	@Test
	@UiThreadTest
	public void testLambdaDynamicRender() {
		Anvil.mount(container, dynamicLambdaView);
		B b = new B("Benchmark/RenderDynamicLambda");
		int i = 0;
		while (!b.done()) {
			dynamicValue = ++i;
			Anvil.render();
		}
		System.out.println(b.report());
	}
}

