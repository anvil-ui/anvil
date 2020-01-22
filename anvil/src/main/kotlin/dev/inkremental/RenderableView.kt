package dev.inkremental

import android.content.Context
import android.widget.FrameLayout

// TODO: add method children() to render all child renderables
// TODO: add method children(r) to override certain properties in child renderables
abstract class RenderableView(context: Context) : FrameLayout(context) {

    abstract val renderable : () -> Unit
    open val removeChilds = true

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Inkremental.mount(this, renderable )
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Inkremental.unmount(this, removeChilds)
    }
}