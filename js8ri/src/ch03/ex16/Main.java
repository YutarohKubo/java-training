package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Main {

    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
        Thread t = new Thread() {
            public void run() {
                T result = null;
                Throwable throwable = null;
                try {
                    result = first.get();
                } catch (Throwable t) {
                    throwable = t;
                }
                second.accept(result, throwable);
            }
        };
        t.start();
    }

}
