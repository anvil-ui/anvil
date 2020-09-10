package dev.inkremental.sample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.inkremental.dsl.android.CENTER
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.layoutGravity
import dev.inkremental.dsl.android.size
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.dsl.androidx.core.widget.nestedScrollView
import dev.inkremental.dsl.androidx.viewpager2.viewPager2Inkremental
import dev.inkremental.dsl.google.android.material.tabs.tabLayout
import dev.inkremental.renderableContentView

class ComplexActivity : AppCompatActivity() {

    private var tab: TabLayout? = null

    val list = mutableListOf(R.string.lorem1, R.string.lorem2, R.string.lorem3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        renderableContentView {
            linearLayout {
                size(MATCH, MATCH)
                orientation(LinearLayout.VERTICAL)
                tabLayout {
                    initWith<TabLayout> {
                        tab = this
                    }
                    size(MATCH, WRAP)
                }
                viewPager2Inkremental {
                    id(R.id.view_pager)
                    size(MATCH, MATCH)
                    pagerItemsDiffable(list, IntDiffCallback()) { index, item ->

                        textView {
                            size(WRAP, WRAP)
                            layoutGravity(CENTER)
                            text(item)
                        }
                    }
                    initWith<ViewPager2> {
                        TabLayoutMediator(tab!!, this) { tab, position ->
                            tab.text = "Title${position + 1}"
                        }.attach()
                    }
                }
            }
        }
    }

}