package dev.inkremental.sample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.padding
import dev.inkremental.dsl.android.size
import dev.inkremental.dsl.android.text
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.renderableContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this

        // RenderableView wraps Anvil and hooks into View lifecycle
        renderableContentView {
            linearLayout {
                size(MATCH, MATCH)
                orientation(LinearLayout.VERTICAL)

                textView {
                    text("Basics")
                    onClick {
                        startActivity(Intent(context, BasicsActivity::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("Fragment Sample")
                    onClick {
                        startActivity(Intent(context, FragmentActivity::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("Lists example")
                    onClick {
                        startActivity(Intent(context, ListActivityWithFragment::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("ConstraintsLayout example")
                    onClick {
                        startActivity(Intent(context, ConstraintActivity::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }


                textView {
                    text("Xml example")
                    onClick {
                        startActivity(Intent(context, XmlActivity::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("Complex example")
                    onClick {
                        startActivity(Intent(context, ComplexActivityWithFragment::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }

                textView {
                    text("YogaLayout example")
                    onClick {
                        startActivity(Intent(context, YogaActivity::class.java))
                    }

                    size(WRAP, WRAP)
                    padding(16.dp)

                    paintFlags(Paint.UNDERLINE_TEXT_FLAG)
                    textColor(Color.BLUE)
                }
            }

        }
    }
}