package ch06.ex02;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static ch06.ex02.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testAssignIdAtomicLong() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
            init();
            assignIdByAtomicLong();
            Set<Long> idSet = new HashSet<>();
            for (long l : arrId) {
                idSet.add(l);
            }

            assertEquals(10000, idSet.size());
        }
    }

}
