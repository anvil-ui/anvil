package trikita.anvil;

import android.content.Context;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

public class RenderableView extends FrameLayout implements Renderable {

	// View must have a non-zero ID to make onSaveInstanceState() and
	// onRestoreInstanceState() called
	public final static int DEFAULT_RENDERABLE_ID = 0xaccede;

	private Renderable renderable;
	private Anvil.Mount mount;

	public RenderableView(Context c) {
		super(c);
		setId(DEFAULT_RENDERABLE_ID);
		// TODO attach/detach?
		mount = Anvil.mount(this, this);
	}

	// Custom Property implementation, holds one value of the given type, allows
	// to get/set it from within the current RenderableView instance
	protected class Prop<V> extends Property<RenderableView, V> {
		private V value;

		public Prop(String name, V value) {
			super((Class<V>) value.getClass(), name);
			this.value = value;
		}

		public V get() {
			return get(RenderableView.this);
		}

		public void set(V value) {
			set(RenderableView.this, value);
		}

		public V get(RenderableView object) {
			if (object == RenderableView.this) {
				return this.value;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		public void set(RenderableView object, V value) {
			if (object == RenderableView.this) {
				this.value = value;
			}
		}

		public boolean isReadOnly() {
			return false;
		}
	}

	// List of object properties with their names
	private Map<String, Prop<?>> mProps = new HashMap<>();

	// Helper to get named property
	public <T> Prop<T> prop(String name) {
		return (Prop<T>) mProps.get(name);
	}

	// Binding to set property value. If it's a Prop class - update it, otherwise - 
	// create a Property instance and use that. This implies having a setNNN
	// method or NNN public field within the renderable component
	protected static  Void set(final String name, final Object propValue) {
		return DSL.attr(PropSetter.instance, new Pair<String, Object>(name, propValue));
	}

	private static class PropSetter implements AttrFunc<Pair<String, Object>> {
		private static PropSetter instance = new PropSetter();
		public void apply(View v, Pair<String, Object> kv) {
			String name = kv.first;
			Object propValue = kv.second;
			if (v instanceof RenderableView) {
				Prop prop = ((RenderableView) v).prop(name);
				if (prop != null) {
					prop.set(propValue);
				} else {
					Property p = Property.of(v.getClass(), propValue.getClass(), name);
					p.set(v, propValue);
				}
			}
		}
	}

	protected void children() {
		// TODO render children using their default renderables
	}

	protected void children(Renderable r) {
		// TODO render r for each of the children overiding their default render
	}

	public void view() {}
}
