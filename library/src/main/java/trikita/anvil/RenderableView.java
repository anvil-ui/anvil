package trikita.anvil;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
		Bundle b = new Bundle();
		b.putParcelable("instanceState", super.onSaveInstanceState());
		onSave(b);
		return b;
	}

	@Override
	public void onRestoreInstanceState(Parcelable p) {
		if (p instanceof Bundle) {
			Bundle b = (Bundle) p;
			onLoad(b);
			super.onRestoreInstanceState(b.getParcelable("instanceState"));
		} else {
			super.onRestoreInstanceState(p);
		}
	}
}

