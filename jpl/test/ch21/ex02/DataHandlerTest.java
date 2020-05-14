package ch21.ex02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHandlerTest {

    @Test
    public void testGC () {
        DataHandler dataHandler = new DataHandler();
        String cd = System.getProperty("user.dir");
        for (int i = 0; i < 5; i++) {
            System.out.println(new String(dataHandler.readFile(cd + "\\src\\ch21\\ex02\\hello" + i)));
        }
        System.gc();
        assertEquals(0, dataHandler.getFileMapSize());
    }

}
