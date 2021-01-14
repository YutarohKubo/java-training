package ch06.ex03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    private static final int THREAD_SIZE = 1000;

    private static AtomicLong atomic = new AtomicLong();
    private static LongAdder adder = new LongAdder();

    public static void main(String[] args) {
        long startInclementAtomicTime = System.currentTimeMillis();
        inclementAtomicLong();
        while (true) {
            if (atomic.get() == THREAD_SIZE) {
                System.out.println("atomic inclement time : " + (System.currentTimeMillis() - startInclementAtomicTime) + "[mSec]");
                break;
            }
        }

        long startInclementAdderTime = System.currentTimeMillis();
        inclementLongAdder();
        while (true) {
            if (adder.sum() == THREAD_SIZE) {
                System.out.println("adder inclement time : " + (System.currentTimeMillis() - startInclementAdderTime) + "[mSec]");
                break;
            }
        }
    }

    private static void inclementAtomicLong() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            Thread t = new Thread(() -> {
                atomic.incrementAndGet();
            });
            t.start();
        }
    }

    private static void inclementLongAdder() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            Thread t = new Thread(() -> {
                adder.increment();
            });
            t.start();
        }
    }

}
