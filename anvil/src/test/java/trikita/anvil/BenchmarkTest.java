package trikita.anvil;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static trikita.anvil.BaseDSL.v;
import static trikita.anvil.DSL.id;
import static trikita.anvil.DSL.tag;

public class BenchmarkTest extends Utils {

    private static final int N = 100000;
    private int mode;

    @Test
    public void testRenderBenchmark() throws ExecutionException {
        long start;

        Anvil.Renderable r = new Anvil.Renderable() {
            public void view() {
                for (int i = 0; i < 10; i++) {
                    final int fi = i;
                    group(transform(i), new Anvil.Renderable() {
                        public void view() {
                            for (int j = 0; j < 10; j++) {
                                item(transform(fi * 10 + j));
                            }
                        }
                    });
                }
            }
        };

        mode = 0;
        Anvil.mount(container, r);
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            renderView(i);
        }
        System.out.println("render/no-changes: " + (System.currentTimeMillis() - start)*1000/N + "us");
        Anvil.unmount(container, true);

        mode = 1;
        Anvil.mount(container, r);
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            renderView(i);
        }
        System.out.println("render/small-changes: " + (System.currentTimeMillis() - start)*1000/N +"us");
        Anvil.unmount(container, true);

        mode = 2;
        Anvil.mount(container, r);
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            renderView(i);
        }
        System.out.println("render/big-changes: " + (System.currentTimeMillis() - start)*1000/N+"us");
    }

    private int transform(int i) {
        switch (mode) {
            case 0: return i;
            case 1: return (i == 1 ? (int)(Math.random() * 100): i);
            case 2: return (int) (Math.random() * 100);
        }
        return 0;
    }

    private void group(final int i, final Anvil.Renderable r) {
        v(MockLayout.class, new Anvil.Renderable() {
            public void view() {
                id(i * 100);
                tag("layout");
                r.view();
            }
        });
    }

    private void item(final int i) {
        v(MockView.class, new Anvil.Renderable() {
            public void view() {
                id(i);
                tag("item"+i);
            }
        });
    }

    private void renderView(int i) throws ExecutionException {
        Future<?> future = Anvil.render();
        if (i == N-1)
            waitTaskToFinish(future);
    }

    private void waitTaskToFinish(Future<?> future) throws ExecutionException {
        try {
            future.get();
        } catch (ExecutionException executionException) {
            throw executionException;
        } catch (Exception e) {
        }
    }
}
