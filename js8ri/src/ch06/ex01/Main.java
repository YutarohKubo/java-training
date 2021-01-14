package ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    static AtomicReference<String> largest;

    public static void updateMaxLengthAndGet(String str) {
        largest.updateAndGet(x -> {
            if (str.length() > x.length()) {
                return str;
            }
            return x;
        });
    }

    public static void initAtomic() {
        largest = new AtomicReference<>("");
    }

}
