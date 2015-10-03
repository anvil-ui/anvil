package trikita.anvil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// TODO: add method children() to render all child renderables
// TODO: add method children(r) to override certain properties in child renderables
public abstract class RenderableView extends FrameLayout
	implements Anvil.Renderable {

	public RenderableView(Context context) {
		super(context);
	}

	public RenderableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RenderableView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Anvil.mount(this, this);
	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		Anvil.unmount(this);
	}

	public abstract void view();
}
