package dev.inkremental.sample

import android.content.Context
import dev.inkremental.RenderableView
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.Size.*
import dev.inkremental.dsl.android.widget.frameLayout
import dev.inkremental.dsl.android.widget.textView

class RenderableForPreview(context: Context) : RenderableView(context) {

    override val renderable = {
        frameLayout {
            size(MATCH, MATCH)
            textView {
                size(WRAP, WRAP)
                layoutGravity(CENTER)
                text("Hello Inkremental from preview")
            }
        }
    }
}