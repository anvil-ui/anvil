package trikita.anvil;

import android.view.ViewGroup;

/**
 * A renderable component. Contains a viewgroup into which the virtual layout
 * will be rendered and a method that returns the virtual layout represeting
 * the current app state.
 */
public interface Renderable {
	Nodes.ViewNode view();
	ViewGroup getRootView();
}
