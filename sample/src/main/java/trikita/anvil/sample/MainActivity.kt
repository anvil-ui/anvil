package trikita.anvil.sample

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import trikita.anvil.BaseDSL.alignParentBottom
import trikita.anvil.BaseDSL.alignParentEnd
import trikita.anvil.DSL.MATCH
import trikita.anvil.DSL.WRAP
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.button
import trikita.anvil.DSL.dip
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.margin
import trikita.anvil.DSL.onClick
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.padding
import trikita.anvil.DSL.paintFlags
import trikita.anvil.DSL.relativeLayout
import trikita.anvil.DSL.size
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView
import trikita.anvil.RenderableView


class MainActivity : AppCompatActivity() {

    // Our state that we want to render using Anvil
    var ticktock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // RenderableView wraps Anvil and hooks into View lifecycle
        setContentView(object : RenderableView(this) {
            override fun view() {
                relativeLayout {
                    size(MATCH, MATCH)

                    linearLayout {
                        size(MATCH, MATCH)
                        padding(dip(8))
                        orientation(LinearLayout.VERTICAL)

                        textView {
                            size(MATCH, WRAP)
                            text("Tick-tock: $ticktock")
                        }

                        linearLayout {
                            size(MATCH, WRAP)
                            orientation(LinearLayout.HORIZONTAL)

                            button {
                                size(WRAP, WRAP)
                                text("Tick")
                                // onClick conveniently calls Anvil.render()
                                // after executing listener lambda to render updated state
                                onClick { v ->
                                    ticktock++
                                }
                            }

                            button {
                                size(WRAP, WRAP)
                                margin(dip(8), dip(0))

                                text("Tock")
                                // You can have more advanced logic here
                                onClick { v ->
                                    ticktock = (ticktock - 1).coerceAtLeast(0)
                                }
                            }
                        }

                        // You can use cycles!
                        for (i in 1..ticktock) {
                            textView {
                                size(MATCH, WRAP)
                                padding(dip(4))
                                text("$i")
                                // And conditionals too!
                                if (i % 2 == 1) {
                                    backgroundColor(color(R.color.colorAccent))
                                } else {
                                    backgroundColor(Color.TRANSPARENT)
                                }
                            }
                        }
                    }

                    textView {
                        text("Open ConstraintsLayout example")
                        onClick {
                            startActivity(Intent(context, ConstraintActivity::class.java))
                        }

                        size(WRAP, WRAP)
                        padding(16)

                        alignParentBottom()
                        alignParentEnd()

                        paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                        textColor(Color.BLUE)
                    }
                }
            }
        })
    }

    private fun color(@ColorRes color: Int) = resources.getColor(color)
}
