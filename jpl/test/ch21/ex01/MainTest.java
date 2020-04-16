package ch21.ex01;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MainTest {

    @Test
    public void testSortedMapCheck () {
        List<String> expected = Arrays.asList("apple", "chocolate", "cocoa", "lemon", "meron", "waffle");
        Main main = new Main();
        main.sortListFromFile();
        assertArrayEquals(main.getSortedLineList().toArray(), expected.toArray());
    }

}
