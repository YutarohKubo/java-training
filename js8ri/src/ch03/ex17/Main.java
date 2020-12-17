package ch03.ex17;

import java.util.function.Consumer;

public class Main {

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    first.run();
                } catch (Throwable th) {
                    handler.accept(th);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    second.run();
                } catch (Throwable th) {
                    handler.accept(th);
                }
            }
        });
        t1.start();
        t2.start();
    }

}
