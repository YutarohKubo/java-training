package ch08.ex04;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long seed = 0;
        List<Long> seedList = new ArrayList<>();
        for (int i = 0; i < 1000001; i++) {
            seed = prev(seed);
            seedList.add(seed^25214903917L);
        }
        long minSeedValue = seedList.stream().reduce(Long::min).get();
        System.out.println("min value of 1000000 times" + minSeedValue);
    }

    private static long next(long s) {
        return s * 25214903917L + 11L % (long) Math.pow(2, 48);
    }

    private static long prev(long s) {
        return (s - 11L) * 246154705703781L % (long) Math.pow(2, 48);
    }

}
