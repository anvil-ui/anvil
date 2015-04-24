package trikita.anvil;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.util.SparseArray;
import android.os.Parcel;

/**
 * A most common renderable implementation - a reactive view group that updates
 * its layout every time the app state changes.
 */
public abstract class RenderableView extends FrameLayout implements Renderable {

	// View must have a non-zero ID to make onSaveInstanceState() and
	// onRestoreInstanceState() called
	public final static int DEFAULT_RENDERABLE_ID = 0xaccede;

	private boolean isRendered = false;

	// We can't do render(this) here because it would call overridden methods
	public RenderableView(Context c) {
		super(c);
		setId(DEFAULT_RENDERABLE_ID);
		Anvil.render(this);
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

		public static final ClassLoaderCreator<SavedState> CREATOR
			= new ClassLoaderCreator<SavedState>() {
				@Override
				public SavedState createFromParcel(Parcel source, ClassLoader loader) {
					return new SavedState(source, loader);
				}

				@Override
				public SavedState createFromParcel(Parcel source) {
					return createFromParcel(null);
				}

				public SavedState[] newArray(int size) {
					return new SavedState[size];
				}
			};
	}
}

