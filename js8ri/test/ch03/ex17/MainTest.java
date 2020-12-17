package ch03.ex17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    private int numOfThrowException = 0;

    @Test
    void testCaseSuccess() {
        Main.doInParallelAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("first i : " + i);
            }
        }, () -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("second i : " + i);
            }
        }, ((throwable) -> {
            fail();
        }));

    }

    @Test
    void testThrowException() {
        Main.doInParallelAsync(() -> {
            int[] array1 = new int[50000];
            for (int i = 0; i < 100000; i++) {
                array1[i] = i;
            }
        }, () -> {
            int[] array2 = new int[80000];
            for (int i = 0; i < 100000; i++) {
                array2[i] = i;
            }
        }, ((throwable) -> {
            numOfThrowException++;
            assertEquals(numOfThrowException, 2);
        }));
    }

}
