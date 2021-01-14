package ch06.ex04;

import org.junit.jupiter.api.Test;

import static ch06.ex04.Main.accumulateMaxValue;
import static ch06.ex04.Main.maxer;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void test1() {
        long[] longs = new long[]{-62, 90, -14, 19, 76, -87, 94, 42, -48, -52, -28, 38, 20, 59};
        for (int i = 0; i < 10000; i++) {
            accumulateMaxValue(longs);
            assertEquals(94, maxer.get());
        }
    }

}
