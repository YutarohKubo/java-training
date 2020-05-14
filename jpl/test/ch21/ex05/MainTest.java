package ch21.ex05;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    public void listIteratorNext() {
        String[][] arrays = {
                {"a", "i", "u", "e", "o"},
                {"ka", "ki", "ku", "ke", "ko"},
                {"sa", "si", "su", "se", "so"}
        };
        String[] resultArray = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so"};
        ArrayBunchList<String> arrayBunchList = new ArrayBunchList<>(arrays);
        List<String> resultList = new ArrayList<>();
        for (ListIterator<String> listIterator = arrayBunchList.listIterator(); listIterator.hasNext();) {
            String nextStr = listIterator.next();
            System.out.println(nextStr);
            resultList.add(nextStr);
        }
        assertArrayEquals(resultArray, resultList.toArray());
    }

    @Test
    public void listIteratorPrev() {
        String[][] arrays = {
                {"so", "se", "su", "si", "sa"},
                {"ko", "ke", "ku", "ki", "ka"},
                {"o", "e", "u", "i", "a"}
        };
        String[] resultArray = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so"};
        ArrayBunchList<String> arrayBunchList = new ArrayBunchList<>(arrays);
        List<String> resultList = new ArrayList<>();
        for (ListIterator<String> listIterator = arrayBunchList.listIterator(resultArray.length); listIterator.hasPrevious();) {
            String nextStr = listIterator.previous();
            System.out.println(nextStr);
            resultList.add(nextStr);
        }
        assertArrayEquals(resultArray, resultList.toArray());
    }

    @Test
    public void callSetBeforeNext() {
        String[][] arrays = {
                {"a", "i", "u", "e", "o"},
                {"ka", "ki", "ku", "ke", "ko"},
                {"sa", "si", "su", "se", "so"}
        };
        ArrayBunchList<String> arrayBunchList = new ArrayBunchList<>(arrays);
        ListIterator<String> listIterator = arrayBunchList.listIterator();
        assertThrows(IllegalStateException.class, () -> listIterator.set("ta"));
    }

    @Test
    public void callSet() {
        String[][] arrays = {
                {"a", "i", "u", "e", "o"},
                {"ka", "ki", "ku", "ke", "ko"},
                {"sa", "si", "su", "se", "so"}
        };
        String[] resultArray = {"a", "i", "u", "e", "o", "ta", "ki", "ku", "ke", "ko", "sa", "si", "su", "se", "so"};
        ArrayBunchList<String> arrayBunchList = new ArrayBunchList<>(arrays);
        ListIterator<String> listIterator = arrayBunchList.listIterator();
        for (int i = 0; i < 6; i++) {
            System.out.println(listIterator.next());
        }
        listIterator.set("ta");
        assertArrayEquals(resultArray, arrayBunchList.toArray());
    }

}
