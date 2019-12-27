package trikita.anvil.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.soloader.SoLoader
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaFlexDirection
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.size
import dev.inkremental.dsl.android.text
import dev.inkremental.dsl.android.widget.*
import dev.inkremental.dsl.yoga.yogaLayout
import trikita.anvil.renderableContentView


class YogaActivity : AppCompatActivity() {

    var reversed = false
    var flexWeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        renderableContentView {
            yogaLayout {
                size(MATCH, MATCH)
                flexDirection(if (reversed) YogaFlexDirection.COLUMN_REVERSE else YogaFlexDirection.COLUMN)

                yogaLayout {
                    size(MATCH, 50.sizeDp)
                    backgroundResource(R.drawable.children_background)
                    flexDirection(YogaFlexDirection.ROW)
                    alignItems(YogaAlign.CENTER)

                    imageView {
                        size(50.sizeDp, 50.sizeDp)
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
                    size(MATCH, 50.sizeDp)
                    backgroundResource(R.drawable.children_background)
                    flexDirection(YogaFlexDirection.ROW_REVERSE)
                    alignItems(YogaAlign.FLEX_START)

                    imageView {
                        size(50.sizeDp, 50.sizeDp)
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
                    size(MATCH, 50.sizeDp)
                    backgroundResource(R.drawable.children_background)
                    flexDirection(YogaFlexDirection.ROW_REVERSE)
                    alignItems(YogaAlign.FLEX_START)

                    imageView {
                        size(50.sizeDp, 50.sizeDp)
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
    }
}
