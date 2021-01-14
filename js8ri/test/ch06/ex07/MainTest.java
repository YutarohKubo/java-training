package ch06.ex07;

import static ch06.ex07.Main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testMax() {
        init();
        map.put("waffle", 200L);
        map.put("meron", 700L);
        map.put("apple", 400L);
        map.put("chocolate", 100L);
        map.put("sushi", 600L);

        assertEquals("meron", getMaxKey());
    }

}
