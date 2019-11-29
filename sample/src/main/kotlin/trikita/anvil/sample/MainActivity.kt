package trikita.anvil.sample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import trikita.anvil.*

class MainActivity : AppCompatActivity() {

    // Our state that we want to render using Anvil
    var ticktock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this

        // RenderableView wraps Anvil and hooks into View lifecycle
        renderableContentView {
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
                            onClick {
                                ticktock++
                            }
                        }

                        button {
                            size(WRAP, WRAP)
                            margin(dip(8), dip(0))

                            text("Tock")
                            // You can have more advanced logic here
                            onClick {
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
                    alignParentStart()

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("Open YogaLayout example")
                    onClick {
                        startActivity(Intent(context, YogaActivity::class.java))
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
    }

    private fun color(@ColorRes color: Int) = ResourcesCompat.getColor(resources, color, null)
}
