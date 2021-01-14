package ch06.ex04;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAccumulator;

public class Main {

    static LongAccumulator maxer = new LongAccumulator(Long::max, Long.MIN_VALUE);

    static void accumulateMaxValue(long[] arr) {
        Arrays.stream(arr).parallel().forEach(x -> maxer.accumulate(x));
    }

}
