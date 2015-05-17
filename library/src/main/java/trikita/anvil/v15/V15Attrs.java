package trikita.anvil.v15;

import trikita.anvil.BaseAttrs;
import android.view.View;

/**
 * This is a utility class with platform-dependent attribute generators. This
 * class works with API level 15 or newer. It's not meant to be used directly,
 * instead BaseAttrs from the corresponding API level package should be used.
 */
public class V15Attrs extends BaseAttrs {

	public interface OnMountListener {
		public void onMount(View v);
	}

	public interface OnUnmountListener {
		public void onUnmount(View v);
	}

	/**
	 * Binds a listener that is called when the view is mounted into the layout
	 */
	public AttrNode onMount(final OnMountListener listener) {
		View.OnAttachStateChangeListener stateListener = new View.OnAttachStateChangeListener() {
			public void onViewAttachedToWindow(View v) {
				listener.onMount(v);
			}
			public void onViewDetachedFromWindow(View v) {}
		};
		return new CleansableAttrNode<View.OnAttachStateChangeListener>(stateListener) {
			public void apply(View v) {
				super.apply(v);
				v.addOnAttachStateChangeListener(this.value);
			}
			public void cleanup(View v) {
				v.removeOnAttachStateChangeListener(this.value);
			}
		};
	}

	/**
	 * Binds a listener that is called when the view is unmounted from the layout
	 */
	public AttrNode onUnmount(final OnUnmountListener listener) {
		View.OnAttachStateChangeListener stateListener = new View.OnAttachStateChangeListener() {
			public void onViewAttachedToWindow(View v) {}
			public void onViewDetachedFromWindow(View v) {
				listener.onUnmount(v);
			}
		};
		return new CleansableAttrNode<View.OnAttachStateChangeListener>(stateListener) {
			public void apply(View v) {
				super.apply(v);
				v.addOnAttachStateChangeListener(this.value);
			}
			public void cleanup(View v) {
				v.removeOnAttachStateChangeListener(this.value);
			}
		};
	}
}

