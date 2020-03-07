package dev.inkremental.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.android.widget.button
import dev.inkremental.dsl.android.widget.frameLayout
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.dsl.androidx.recyclerview.*
import dev.inkremental.dsl.androidx.recyclerview.RecyclerLayoutType.*
import dev.inkremental.dsl.androidx.recyclerview.widget.recyclerView
import dev.inkremental.renderableContentView

class ListActivity : AppCompatActivity() {

    var items = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    var listStyle = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        renderableContentView {
            frameLayout {
                size(MATCH, MATCH)

                linearLayout {
                    size(MATCH, 50.sizeDp)

                    button {
                        size(WRAP, MATCH)
                        text("Vertical")
                        onClick {
                            listStyle = 1
                        }
                    }
                    button {
                        size(WRAP, MATCH)
                        text("Horizontal")
                        onClick {
                            listStyle = 2
                        }
                    }
                    button {
                        size(WRAP, MATCH)
                        text("Grid")
                        onClick {
                            listStyle = 3
                        }
                    }
                    button {
                        size(WRAP, MATCH)
                        text("Add")
                        onClick {
                            val size = items.size + 1
                            items = (items + (size..(size + 20))).toMutableList()
                        }
                    }
                }
                list {
                    size(MATCH, MATCH)
                    marginTop(50.dp)

                    when (listStyle) {
                        1 -> layout(Vertical())
                        2 -> layout(Horizontal())
                        3 -> layout(Grid(3))
                    }

                    //uses Recycler underneath
                    items(items) { index : Int, itemValue : Any ->
                        size(WRAP, WRAP)
                        //adapter item
                        frameLayout {
                            size(100.sizeDp, if (listStyle == 3) 150.sizeDp else 50.sizeDp)
                            backgroundResource(R.color.children_stroke)

                            textView {
                                size(WRAP, WRAP)
                                layoutGravity(CENTER)
                                text("item: $itemValue")
                            }
                        }
                    }
                }
            }

        }
    }
}