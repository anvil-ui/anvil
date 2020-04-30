package dev.inkremental.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.widget.button
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.renderable

class SimpleFragment : Fragment() {

    var ticktock = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return renderable(inflater.context) {
            linearLayout {
                size(MATCH, MATCH)
                padding(8.dp)
                orientation(LinearLayout.VERTICAL)

                textView {
                    size(MATCH, WRAP)
                    textSize(20f.sp)
                    text("Tick-tock: $ticktock")
                    rippleEffectBorderless(true)
                    clickable(true)
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
                        size(WRAP, 50.sizeDp)
                        margin(8.dp, 0.dp)

                        text("Tock")
                        // You can have more advanced logic here
                        onClick {
                            ticktock = (ticktock - 1).coerceAtLeast(0)
                        }
                    }
                }
            }
        }
    }
}