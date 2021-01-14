package ch06.ex05;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static ch06.ex05.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class MainTest {

    @Test
    public void testNullFiles() {
        init();
        operateMap(null);
        assertEquals(0, mapWords.size());
    }

    @Test
    public void test3Files() {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++) {
            System.out.println("j = " + j);
            init();
            File[] files = new File[FILE_NUM];
            for (int i = 0; i < FILE_NUM; i++) {
                files[i] = new File(String.format("%s\\src\\ch06\\ex05\\document%d.txt", CD, i + 1));
            }
            operateMap(files);
            Set<File> expectedValues1 = new HashSet<>();
            expectedValues1.add(files[0]);
            expectedValues1.add(files[1]);
            assertIterableEquals(expectedValues1, mapWords.get("Each"));

            Set<File> expectedValues2 = new HashSet<>();
            expectedValues2.add(files[2]);
            assertIterableEquals(expectedValues2, mapWords.get("Once"));

            Set<File> expectedValues3 = new HashSet<>();
            expectedValues3.add(files[0]);
            expectedValues3.add(files[1]);
            expectedValues3.add(files[2]);
            assertIterableEquals(expectedValues3, mapWords.get("component"));
        }
        System.out.println("finish (" + (System.currentTimeMillis() - startTime) + ") [mSec]");
    }

}
