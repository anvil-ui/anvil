package dev.inkremental.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.inkremental.Inkremental
import dev.inkremental.dsl.android.Size
import dev.inkremental.dsl.android.Size.*
import dev.inkremental.dsl.android.size
import dev.inkremental.dsl.android.text
import dev.inkremental.dsl.android.widget.button
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.dsl.androidx.core.InkrementalPagerAdapter
import dev.inkremental.dsl.androidx.core.viewPagerInkremental
import dev.inkremental.dsl.androidx.core.widget.nestedScrollView
import dev.inkremental.dsl.google.android.material.tabs.tabLayout
import dev.inkremental.renderable

class ComplextFragment : Fragment() {

    val textIds : List<Int> = listOf(R.string.large_text, R.string.large_text, R.string.large_text)

    private var tab: TabLayout? = null
    var adapter : InkrementalPagerAdapter<Any>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return renderable(inflater.context) {
            linearLayout {
                size(MATCH, MATCH)
                orientation(LinearLayout.VERTICAL)
                tabLayout {
//                    id(R.id.tabs)
//                    initWith<TabLayout> {
//                        tab = this
//                    }
                    size(MATCH, WRAP)
                }
                viewPagerInkremental {
                    id(R.id.view_pager)
                    size(MATCH, MATCH)
                    offscreenPageLimit(2)
                    if (Inkremental.currentView<ViewPager>()?.adapter != null){
                        adapter = (Inkremental.currentView<ViewPager>()?.adapter as InkrementalPagerAdapter<Any>)
                    }
                    if (Inkremental.currentView<ViewPager>()?.adapter == null && adapter != null) {
                        Inkremental.currentView<ViewPager>()?.adapter = adapter
                    }

//                    titles {
//                        when (it) {
//                            0 -> "Будущие"
//                            1 -> "Онлайн"
//                            2 -> "Прошедшие"
//                            else -> throw IllegalStateException("unknown tab index")
//                        }
//                    }
//                    initWith<ViewPager> {
//                        tab?.setupWithViewPager(this)
//                    }
                    pagerItems(textIds) { pos, item ->
                        nestedScrollView {
                            when (pos){
                                0 -> id(R.id.content1)
                                1 -> id(R.id.content2)
                                2 -> id(R.id.content3)
                            }

                            size(MATCH, MATCH)
                            linearLayout {
                                when (pos){
                                    0 -> id(R.id.roo1)
                                    1 -> id(R.id.roo2)
                                    2 -> id(R.id.roo3)
                                }
                                size(MATCH, MATCH)
                                orientation(LinearLayout.VERTICAL)

                                textView {
                                    when (pos){
                                        0 -> id(R.id.text1)
                                        1 -> id(R.id.text2)
                                        2 -> id(R.id.text3)
                                    }
                                    size(MATCH, MATCH)
                                    text(item)
                                }
                                button {
                                    text("Forward!")
                                    onClick {
                                        fragmentManager!!.beginTransaction()
                                                .replace(R.id.fragment, SimpleFragment())
                                                .addToBackStack(null)
                                                .commit()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}