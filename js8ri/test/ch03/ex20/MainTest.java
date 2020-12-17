package ch03.ex20;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void mappingToStringLength() {
        List<String> stringList = new ArrayList<String>() {
            {
                add("waffle");
                add("meron pan");
                add("chocolate");
                add("special lemon tea");
            }
        };
        List<Integer> expectedList = new ArrayList<Integer>() {
            {
                add(6);
                add(9);
                add(9);
                add(17);
            }
        };

        assertEquals(Main.map(stringList, String::length), expectedList);
    }

}
