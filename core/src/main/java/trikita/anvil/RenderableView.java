package trikita.anvil;

import android.content.Context;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

public abstract class RenderableView extends FrameLayout implements Renderable {

	Anvil.Mount mount = null;

	public RenderableView(Context c) {
		super(c);
	}

	// TODO: other view constructors

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		assert(mount == null);
		mount = Anvil.mount(this, this);
	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		assert(mount != null);
		Anvil.unmount(mount);
	}

	protected void children() {
		// TODO render children using their default render function
	}

	protected void children(Renderable r) {
		// TODO render each childview overiding its default render function
	}

	public abstract void view();
}
