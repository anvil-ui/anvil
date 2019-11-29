package trikita.anvil

import trikita.anvil.Anvil.Renderable
import kotlin.test.Test

class BenchmarkTest : Utils() {
    private var mode = 0
    @Test
    fun testRenderBenchmark() {
        var start: Long
        val r = Renderable {
            for (i in 0..9) {
                group(transform(i)) {
                    for (j in 0..9) {
                        item(transform(i * 10 + j))
                    }
                }
            }
        }
        mode = 0
        Anvil.mount(container, r)
        start = System.currentTimeMillis()
        for (i in 0 until N) {
            Anvil.render()
        }
        println("render/no-changes: " + (System.currentTimeMillis() - start) * 1000 / N + "us")
        Anvil.unmount(container, true)
        mode = 1
        Anvil.mount(container, r)
        start = System.currentTimeMillis()
        for (i in 0 until N) {
            Anvil.render()
        }
        println("render/small-changes: " + (System.currentTimeMillis() - start) * 1000 / N + "us")
        Anvil.unmount(container, true)
        mode = 2
        Anvil.mount(container, r)
        start = System.currentTimeMillis()
        for (i in 0 until N) {
            Anvil.render()
        }
        println("render/big-changes: " + (System.currentTimeMillis() - start) * 1000 / N + "us")
    }

    private fun transform(i: Int): Int {
        when (mode) {
            0 -> return i
            1 -> return if (i == 1) (Math.random() * 100).toInt() else i
            2 -> return (Math.random() * 100).toInt()
        }
        return 0
    }

    private fun group(i: Int, r: () -> Unit) {
        v<MockLayout, ViewScope>(ViewScope) {
            id(i * 100)
            tag("layout")
            r()
        }
    }

    private fun item(i: Int) {
        v<MockView, ViewScope>(ViewScope) {
            id(i)
            tag("item$i")
        }
    }

    companion object {
        private const val N = 100000
    }
}
