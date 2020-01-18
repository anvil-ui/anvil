package dev.inkremental

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

// TODO: add method children() to render all child renderables
// TODO: add method children(r) to override certain properties in child renderables
abstract class RenderableView : FrameLayout, Renderable {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Anvil.mount(this, this)
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Anvil.unmount(this)
    }

    abstract override fun view()
}