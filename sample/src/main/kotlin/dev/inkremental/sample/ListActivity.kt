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

    var items = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20).toMutableList()
    var listStyle = 1
    val intDiffCallback = IntDiffCallback()

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
                            //reverse positions of last two elements and add next twenty elements. This is done to show animation from diffutils
                            items = (items.dropLast(2) + listOf(items[items.lastIndex], items[items.lastIndex - 1]) + (size..(size + 20))).toMutableList()
                        }
                    }
                }
                list {
                    size(MATCH, MATCH)
                    margin(top = 50.dp)

                    when (listStyle) {
                        1 -> layout(Vertical())
                        2 -> layout(Horizontal())
                        3 -> layout(Grid(3))
                    }

                    //uses Recycler underneath
//                    items(items) { index: Int, itemValue: Int ->
                    itemsDiffable(items, intDiffCallback) { index: Int, itemValue: Int ->
                        size(if (listStyle != 2) MATCH else WRAP, WRAP)
                        //adapter item
                        frameLayout {
                            size(if (listStyle != 2) MATCH else 100.sizeDp, if (listStyle == 3) 150.sizeDp else 50.sizeDp)
                            margin(value = 1.dp)
                            backgroundResource(R.color.children_stroke)

                            frameLayout {
                                size(MATCH, MATCH)
                                margin(value = 2.dp)

                                backgroundResource(R.color.white)
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
}


class IntDiffCallback : InkrementalDiffCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        val old: Int = oldItems[oldItem]
        val new: Int = newItems[newItem]
        return old == new
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        val old: Int = oldItems[oldItem]
        val new: Int = newItems[newItem]
        return old == new
    }

}
