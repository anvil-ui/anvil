package trikita.anvil;

import android.view.View;

public class DSL {

	static Anvil.Mount current;

	public static final class ViewClassResult {}

	public static ViewClassResult v(Class<? extends View> c) {
		current.push(c);
		return null;
	}

	static Void end() {
		current.pop();
		return null;
	}

	public static Void x(ViewClassResult c, Object ...args) { return end(); }
	public static Void o(ViewClassResult c, Object ...args) { return end(); }

	public static Void v(Class<? extends View> c, Renderable r) {
		v(c);
		r.view();
		return end();
	}

	public static <T> Void attr(AttrFunc<T> f, T value) {
		current.attr(f, value);
		return null;
	}
}
