package dev.inkremental.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.widget.button
import dev.inkremental.dsl.android.widget.frameLayout
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.dsl.androidx.recyclerview.RecyclerLayoutType
import dev.inkremental.dsl.androidx.recyclerview.list
import dev.inkremental.renderable

class ListFragment : Fragment() {

    var items = mutableListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, "Section 10",
            11, 12, 13, 14, 15, 16, 17, 18, 19, "Section 20",
            20, 21, 22, 23, 24, 25, 26, 27, 28, "Section 30"
    ).toMutableList()

    var listStyle = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return renderable(inflater.context) {
            frameLayout {
                size(MATCH, MATCH)

                linearLayout {
                    size(MATCH, 50.sizeDp)

                    button {
                        size(WRAP, MATCH)
                        text("Forward")
                        onClick {
                            fragmentManager!!.beginTransaction()
                                    .replace(R.id.fragment, SimpleFragment())
                                    .addToBackStack(null)
                                    .commit()
                        }
                    }

                }
                list {
                    id(R.id.content1)
                    size(MATCH, MATCH)
                    margin(top = 50.dp)
                    layout(RecyclerLayoutType.Vertical())


                    //uses Recycler underneath
                    items(items) { index: Int, itemValue: Any ->
//                    itemsDiffable(items, intDiffCallback) { index: Int, itemValue: Int ->
                        size(if (listStyle != 2) MATCH else WRAP, WRAP)
                        //adapter item
                        when(itemValue) {
                            is Int -> itemWidget(itemValue)
                            is String -> sectionWidget(itemValue)
                        }
                    }
                }
            }
        }
    }

    private fun itemWidget(itemValue: Int) {
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

    private fun sectionWidget(itemValue: String) {
        frameLayout {
            size(MATCH, if (listStyle == 3) 150.sizeDp else 50.sizeDp)
            margin(value = 1.dp)
            backgroundResource(R.color.children_stroke)

            frameLayout {
                size(MATCH, MATCH)
                margin(value = 2.dp)

                backgroundResource(R.color.white)
                textView {
                    size(WRAP, WRAP)
                    layoutGravity(CENTER)
                    text(itemValue)
                }
            }
        }
    }
}