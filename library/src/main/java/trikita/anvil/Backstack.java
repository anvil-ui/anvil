package trikita.anvil;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayDeque;
import java.util.ArrayList;

//
// Custom backstack implementation to use views instead of
// activities/fragments.
//
// Saves stack of views in a bundle, each view can also save its internal state
// to a nested bundle.
//
public class Backstack {

	// Bundle keys
	final static String KEY_CLASSNAME = "className";
	final static String KEY_STATE = "state";
	final static String KEY_BACKSTACK = "backstack";

	Listener listener;
	Context context;
	ArrayDeque<Bundle> backstack = new ArrayDeque<>();
	View currentView;

	public interface Listener {
		void setContentView(View toView);
	}

	public Backstack(Context c, Listener l) {
		this.context = c;
		this.listener = l;
	}

	public void navigate(View v) {
		if (currentView != null) {
			SparseArray<Parcelable> state = new SparseArray<>();
			currentView.saveHierarchyState(state);
			backstack.peek().putSparseParcelableArray(KEY_STATE, state);
		}
		Bundle b = new Bundle();
		b.putString(KEY_CLASSNAME, v.getClass().getName());
		SparseArray<Parcelable> state = new SparseArray<>();
		v.saveHierarchyState(state);
		b.putSparseParcelableArray(KEY_STATE, state);
		backstack.push(b);
		listener.setContentView(v);
		currentView = v;
	}

	public int size() {
		return backstack.size();
	}

	public boolean back() {
		backstack.pop();
		if (backstack.size() == 0) {
			return false;
		} else {
			View v = createView(backstack.peek());
			listener.setContentView(v);
		}
		Anvil.render();
		return true;
	}

	public void load(Bundle b) {
		for (Parcelable p : b.getParcelableArrayList(KEY_BACKSTACK)) {
			if (p instanceof Bundle) {
				navigate(createView((Bundle) p));
			}
		}
	}

	public void save(Bundle b) {
		if (currentView != null) {
			SparseArray<Parcelable> state = new SparseArray<>();
			currentView.saveHierarchyState(state);
			backstack.peek().putSparseParcelableArray(KEY_STATE, state);
		}
		ArrayList<Parcelable> backstackState = new ArrayList<>(backstack.size());
		for (Bundle entry : backstack) {
			backstackState.add(0, entry);
		}
		b.putParcelableArrayList(KEY_BACKSTACK, backstackState);
	}

	private View createView(Bundle b) {
		try {
			String className = b.getString(KEY_CLASSNAME);
			View v = (View)
				Class.forName(className).getConstructor(Context.class).newInstance(context);
			SparseArray<Parcelable> state = b.getSparseParcelableArray(KEY_STATE);
			if (state != null) {
				v.restoreHierarchyState(state);
			}
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}



