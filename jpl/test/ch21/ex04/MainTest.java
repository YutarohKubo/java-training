package ch21.ex04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    public void listIteratorNext() {
        List<String> listStr = new ArrayList<>();
        listStr.add("hahahaha");
        listStr.add("hihihihihihi");
        listStr.add("huhuhu");
        listStr.add("hehehehe");
        List<String> resultList = new ArrayList<>();
        for (ShortStrings shortStrings = new ShortStrings(listStr.listIterator(), 10); shortStrings.hasNext(); ) {
            String str = shortStrings.next();
            resultList.add(str);
        }
        assertEquals(Arrays.asList("hahahaha", "huhuhu", "hehehehe"), resultList);
    }

    @Test
    public void listIteratorPrev() {
        List<String> listStr = new ArrayList<>();
        listStr.add("hahahaha");
        listStr.add("hihihihihihi");
        listStr.add("huhuhu");
        listStr.add("hehehehe");
        List<String> resultList = new ArrayList<>();
        for (ShortStrings shortStrings = new ShortStrings(listStr.listIterator(listStr.size()), 10); shortStrings.hasPrevious(); ) {
            String str = shortStrings.previous();
            resultList.add(str);
        }
        assertEquals(Arrays.asList("hehehehe", "huhuhu", "hahahaha"), resultList);
    }

    @Test
    public void listIteratorNextFailed() {
        List<String> listStr = new ArrayList<>();
        listStr.add("hahahaha");
        listStr.add("hihihihihihi");
        listStr.add("huhuhu");
        listStr.add("hehehehe");
        ShortStrings shortStrings = new ShortStrings(listStr.listIterator(listStr.size()), 10);
        assertThrows(NoSuchElementException.class, shortStrings::next);
    }

    @Test
    public void listIteratorPrevFailed() {
        List<String> listStr = new ArrayList<>();
        listStr.add("hahahaha");
        listStr.add("hihihihihihi");
        listStr.add("huhuhu");
        listStr.add("hehehehe");
        ShortStrings shortStrings = new ShortStrings(listStr.listIterator(), 10);
        assertThrows(NoSuchElementException.class, shortStrings::previous);
    }

}
