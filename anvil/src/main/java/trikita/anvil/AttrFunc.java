package trikita.anvil;

import android.view.View;

public interface AttrFunc<T> {
	void apply(View v, T value, T oldValue);
}


