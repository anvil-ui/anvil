package trikita.anvil.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.soloader.SoLoader
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaFlexDirection
import trikita.anvil.DSL.MATCH
import trikita.anvil.DSL.WRAP
import trikita.anvil.DSL.backgroundResource
import trikita.anvil.DSL.button
import trikita.anvil.DSL.dip
import trikita.anvil.DSL.imageResource
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.onClick
import trikita.anvil.DSL.size
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView
import trikita.anvil.RenderableView
import trikita.anvil.yoga.layout.YogaDSL.alignItems
import trikita.anvil.yoga.layout.YogaDSL.flex
import trikita.anvil.yoga.layout.YogaDSL.flexDirection
import trikita.anvil.yoga.layout.YogaDSL.marginRightPercent
import trikita.anvil.yoga.layout.YogaDSL.yogaLayout


class YogaActivity : AppCompatActivity() {

    var reversed = false
    var flexWeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        // RenderableView wraps Anvil and hooks into View lifecycle
        setContentView(object : RenderableView(this) {
            override fun view() {
                yogaLayout {
                    size(MATCH, MATCH)
                    flexDirection(if (reversed) YogaFlexDirection.COLUMN_REVERSE else YogaFlexDirection.COLUMN)

                    yogaLayout {
                        size(MATCH, dip(50))
                        backgroundResource(R.drawable.children_background)
                        flexDirection(YogaFlexDirection.ROW)
                        alignItems(YogaAlign.CENTER)

                        imageView {
                            size(dip(50), dip(50))
                            imageResource(R.mipmap.ic_launcher)
                            flex(1f)
                        }
                        textView {
                            size(WRAP, WRAP)
                            text(R.string.child_1_text)
                            flex(flexWeight.toFloat())
                        }
                    }
                    yogaLayout {
                        size(MATCH, dip(50))
                        backgroundResource(R.drawable.children_background)
                        flexDirection(YogaFlexDirection.ROW_REVERSE)
                        alignItems(YogaAlign.FLEX_START)

                        imageView {
                            size(dip(50), dip(50))
                            imageResource(R.mipmap.ic_launcher)
                            flex(0f)
                        }
                        textView {
                            size(WRAP, WRAP)
                            text(R.string.child_2_text)
                            flex(1f)
                        }
                    }
                    yogaLayout {
                        size(MATCH, dip(50))
                        backgroundResource(R.drawable.children_background)
                        flexDirection(YogaFlexDirection.ROW_REVERSE)
                        alignItems(YogaAlign.FLEX_START)

                        imageView {
                            size(dip(50), dip(50))
                            imageResource(R.mipmap.ic_launcher)
                            flex(0f)
                            marginRightPercent(30f)
                        }
                        textView {
                            size(WRAP, WRAP)
                            text(R.string.child_3_text)
                            flex(1f)
                        }
                    }
                    button {
                        text("Reverse")
                        onClick {
                            reversed = !reversed
                            flexWeight++
                        }
                    }
                }
            }
        })
    }
}
