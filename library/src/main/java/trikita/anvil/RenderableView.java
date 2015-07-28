package trikita.anvil;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import android.util.Property;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * A most common renderable implementation - a reactive view group that updates
 * its layout every time the app state changes.
 */
public abstract class RenderableView extends FrameLayout implements Renderable {

	// View must have a non-zero ID to make onSaveInstanceState() and
	// onRestoreInstanceState() called
	public final static int DEFAULT_RENDERABLE_ID = 0xaccede;

	private boolean isRendered = false;

	List<Nodes.ViewNode> mChildNodes = new ArrayList<>();

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


	// We can't do render(this) here because it would call overridden methods
	public RenderableView(Context c) {
		super(c);
		setId(DEFAULT_RENDERABLE_ID);
	}

	// Seems to be a good place to start rendering our view
	public void onMeasure(int wspec, int hspec) {
		if (isRendered == false) {
			Anvil.render(this);
			isRendered = true;
		}
		super.onMeasure(wspec, hspec);
	}

	// Return itself
	public ViewGroup getRootView() {
		return this;
	}

	// Here you can store view state
	public void onLoad(Bundle b) {}

	// Here you can load view state
	public void onSave(Bundle b) {}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		Bundle b = new Bundle();
		onSave(b);
		SavedState ss = new SavedState(superState, b);
		ss.childrenStates = new SparseArray();
		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).saveHierarchyState(ss.childrenStates);
		}
		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState ss = (SavedState) state;
		onLoad(ss.getBundle());
		Anvil.render(this);
		super.onRestoreInstanceState(ss.getSuperState());
		for (int i = 0; i < getChildCount(); i++) {
			getChildAt(i).restoreHierarchyState(ss.childrenStates);
		}
	}

	@Override
	protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
		dispatchFreezeSelfOnly(container);
	}

	@Override
	protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		dispatchThawSelfOnly(container);
	}

	static class SavedState extends BaseSavedState {
		SparseArray childrenStates;
		Bundle bundle;

		SavedState(Parcelable superState, Bundle b) {
			super(superState);
			this.bundle = b;
		}

		public Bundle getBundle() {
			return this.bundle;
		}

		public String toString() {
			return "(" + this.bundle.toString() + ";" +
				this.childrenStates.toString() + ")";
		}

		private SavedState(Parcel in, ClassLoader classLoader) {
			super(in);
			this.bundle = in.readBundle();
			childrenStates = in.readSparseArray(classLoader);
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeBundle(this.bundle);
			out.writeSparseArray(childrenStates);
		}

		public interface ParcelableCompatCreatorCallbacks<T> {
			public T createFromParcel(Parcel p, ClassLoader loader);
			public T createFromParcel(Parcel p);
			public T[] newArray(int size);
		}

		static class CompatCreator<T> implements Parcelable.Creator<T> {
			final ParcelableCompatCreatorCallbacks<T> mCallbacks;

			public CompatCreator(ParcelableCompatCreatorCallbacks<T> callbacks) {
				mCallbacks = callbacks;
			}

			@Override
			public T createFromParcel(Parcel source) {
				return mCallbacks.createFromParcel(source, null);
			}

			@Override
			public T[] newArray(int size) {
				return mCallbacks.newArray(size);
			}
		}

		static class ParcelableCompatCreatorFactory {
			static <T> Parcelable.Creator<T> instantiate(ParcelableCompatCreatorCallbacks<T> cb) {
				return new ParcelableClassLoaderCreator(cb);
			}
		}

		public static <T> Parcelable.Creator<T> newCreator(ParcelableCompatCreatorCallbacks<T> cb) {
			if (Build.VERSION.SDK_INT >= 13) {
				ParcelableCompatCreatorFactory.instantiate(cb);
			}
			return new CompatCreator<T>(cb);
		}

		public static class ParcelableClassLoaderCreator<T> implements Parcelable.ClassLoaderCreator<T> {
			private final ParcelableCompatCreatorCallbacks<T> mCallbacks;

			public ParcelableClassLoaderCreator(ParcelableCompatCreatorCallbacks<T> cb) {
				mCallbacks = cb;
			}

			public T createFromParcel(Parcel p) {
				return mCallbacks.createFromParcel(p, null);
			}

			public T createFromParcel(Parcel p, ClassLoader loader) {
				return mCallbacks.createFromParcel(p, loader);
			}

			public T[] newArray(int size) {
				return mCallbacks.newArray(size);
			}
		}

		public static final Parcelable.Creator<SavedState> CREATOR =
				newCreator(new ParcelableCompatCreatorCallbacks() {
			public SavedState createFromParcel(Parcel p, ClassLoader loader) {
				return new SavedState(p, loader);
			}

			public SavedState createFromParcel(Parcel p) {
				return createFromParcel(null);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		});
	}

	public List<Nodes.ViewNode> children() {
		return mChildNodes;
	}

	// Helper to create new named property, if already exists - updates the value
	public <T> Prop<T> prop(String name, T value) {
		Prop<T> prop = (Prop<T>) mProps.get(name);
		if (prop != null) {
			prop.set(value);
		} else {
			prop = new Prop<T>(name, value);
			mProps.put(name, prop);
		}
		return prop;
	}

	// Helper to get named property
	public <T> Prop<T> prop(String name) {
		return (Prop<T>) mProps.get(name);
	}

	// Binding to set property value. If it's a Prop class - update it, otherwise - 
	// create a Property instance and use that. This implies having a setNNN
	// method or NNN public field within the renderable component
	public static <T> Nodes.AttrNode set(final String name, final T propValue) {
		return new SimpleAttrNode(new Pair<>(name, propValue)) {
			public void apply(View v) {
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
		};
	}

	// Helper for bindings that are evaluated only on the first rendering cycle
	public Nodes.AttrNode once(final Nodes.AttrNode node) {
		return isRendered ? null : node;
	}
}

