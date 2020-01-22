package dev.inkremental

import android.content.Context
import kotlin.test.Test

class ViewByIdTest : Utils() {
    class CustomLayout(c: Context?) : MockLayout(c) {
        var firstView: MockView
        var secondView: MockView

        init {
            firstView = MockView(c)
            firstView.id = ID_FIRST
            secondView = MockView(c)
            secondView.id = ID_SECOND
            addView(firstView, 0)
            addView(secondView, 0)
        }
    }

    @Test
    fun testWithId() {
//        Anvil.mount(container, new Anvil.Renderable() {
//            public void view() {
//                v(CustomLayout.class, new Anvil.Renderable() {
//                    public void view() {
//                        // The order doesn't matter
//                        withId(ID_SECOND, new Anvil.Renderable() {
//                            public void view() {
//                                attr("text", "qux");
//                            }
//                        });
//                        withId(ID_FIRST, new Anvil.Renderable() {
//                            public void view() {
//                                attr("text", "bar");
//                            }
//                        });
//                        // Also, one view can be looked up by id many times
//                        withId(ID_SECOND, new Anvil.Renderable() {
//                            public void view() {
//                                attr("tag", "world");
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        CustomLayout layout = (CustomLayout) container.getChildAt(0);
//        assertEquals("bar", layout.firstView.getText());
//        assertEquals("qux", layout.secondView.getText());
//        assertEquals("world", layout.secondView.getTag());
    }

    companion object {
        private const val ID_FIRST = 100
        private const val ID_SECOND = 101
    }
}
