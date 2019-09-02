package trikita.anvil.sample

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import trikita.anvil.BaseDSL
import trikita.anvil.DSL
import trikita.anvil.DSL.*
import trikita.anvil.RenderableView


class MainActivity : AppCompatActivity() {

    // Our state that we want to render using Anvil
    var ticktock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // RenderableView wraps Anvil and hooks into View lifecycle
        setContentView(object : RenderableView(this) {
            override fun view() {
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
            }
        })
    }

    private fun color(@ColorRes color: Int) = resources.getColor(color)
}
