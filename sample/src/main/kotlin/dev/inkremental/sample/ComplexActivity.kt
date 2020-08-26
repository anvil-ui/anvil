package dev.inkremental.sample

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import dev.inkremental.dsl.android.Size.MATCH
import dev.inkremental.dsl.android.Size.WRAP
import dev.inkremental.dsl.android.size
import dev.inkremental.dsl.android.view.ViewScope
import dev.inkremental.dsl.android.widget.FrameLayoutScope
import dev.inkremental.dsl.android.widget.linearLayout
import dev.inkremental.dsl.android.widget.textView
import dev.inkremental.dsl.androidx.core.widget.nestedScrollView
import dev.inkremental.dsl.androidx.recyclerview.RenderableRecyclerViewAdapter
import dev.inkremental.dsl.androidx.viewpager2.adapter
import dev.inkremental.dsl.androidx.viewpager2.widget.viewPager2
import dev.inkremental.dsl.google.android.material.tabs.tabLayout
import dev.inkremental.renderableContentView

class ComplexActivity : AppCompatActivity() {

    val textIds : List<Int> = listOf(R.string.large_text, R.string.large_text, R.string.large_text)

    private var tab: TabLayout? = null

    val pagerAdapter : RenderableRecyclerViewAdapter<Int> = RenderableRecyclerViewAdapter.withItems(listOf(R.string.large_text, R.string.large_text, R.string.large_text)) { pos, item ->
        FrameLayoutScope.size(MATCH, MATCH)

        nestedScrollView {
            id(R.id.scroll)

            size(MATCH, MATCH)
            linearLayout {
                size(MATCH, MATCH)
                orientation(LinearLayout.VERTICAL)

                textView {
                    size(MATCH, MATCH)
                    text(item)
                }
            }
        }
    }

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
                viewPager2 {
                    id(R.id.view_pager)
                    size(MATCH, MATCH)
                    adapter(pagerAdapter)
                }
            }
        }
    }


}