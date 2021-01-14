package ch06.ex02;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    static LongAdder idAdder;
    static AtomicLong idAtomic;
    static long[] arrId;

    public static void main(String[] args) {
        init();
        assignIdByLongAdder();

        Set<Long> idSet = new HashSet<>();
        for (long l : arrId) {
            idSet.add(l);
        }
        System.out.println("not duplicated id size : " + idSet.size());

        init();
        assignIdByAtomicLong();

        idSet.clear();
        for (long l : arrId) {
            idSet.add(l);
        }
        System.out.println("not duplicated id size : " + idSet.size());
    }

    static void assignIdByLongAdder() {
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                idAdder.increment();
                arrId[finalI] = idAdder.sum(); //IDを割り振る際、スレッドセーフでないため、LongAdderは役に立たない.
            });
            t.start();
        }
    }

    static void assignIdByAtomicLong() {
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                arrId[finalI] = idAtomic.incrementAndGet(); //これでスレッドセーフにIDを割り振ることができるため、AtomicLongの方が役に立つ
            });
            t.start();
        }
    }

    static void init() {
        idAdder = new LongAdder();
        idAtomic = new AtomicLong();
        arrId= new long[10000];
    }

}
