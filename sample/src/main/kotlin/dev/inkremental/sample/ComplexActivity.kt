package dev.inkremental.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.inkremental.dsl.android.*
import dev.inkremental.dsl.android.Size.*
import dev.inkremental.dsl.android.widget.*
import dev.inkremental.dsl.androidx.core.viewPagerInkremental
import dev.inkremental.dsl.androidx.core.widget.nestedScrollView
import dev.inkremental.dsl.google.android.material.tabs.tabLayout
import dev.inkremental.renderableContentView

class ComplexActivity : AppCompatActivity() {

    val textIds : List<Int> = listOf(R.string.large_text, R.string.large_text, R.string.large_text)

    private var tab: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        renderableContentView {
            linearLayout {
                size(MATCH, MATCH)
                orientation(LinearLayout.VERTICAL)
                tabLayout {
//                    id(R.id.tabs)
                    initWith<TabLayout> {
                        tab = this
                    }
                    size(MATCH, WRAP)
                }
                viewPagerInkremental {
//                    id(R.id.view_pager)
                    size(MATCH, MATCH)
                    offscreenPageLimit(2)

                    titles {
                        when (it) {
                            0 -> "Будущие"
                            1 -> "Онлайн"
                            2 -> "Прошедшие"
                            else -> throw IllegalStateException("unknown tab index")
                        }
                    }
                    initWith<ViewPager> {
                        tab?.setupWithViewPager(this)
                    }
                    pagerItems(textIds) { pos, item ->
                        nestedScrollView {
                            size(MATCH, MATCH)
                            linearLayout {
                                size(MATCH, MATCH)
                                orientation(LinearLayout.VERTICAL)

                                textView {
                                    size(MATCH, MATCH)
                                    text(item)
                                }
                                button {
                                    text("Forward!")
                                    onClick {
                                        startActivity(Intent(this@ComplexActivity, XmlActivity::class.java))
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