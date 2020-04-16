package ch17.ex02;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DataHandlerTest {

    @Test
    public void testGC () {
        DataHandler dataHandler = new DataHandler();
        String cd = System.getProperty("user.dir");
        System.out.println(new String(dataHandler.readFile(new File(cd + "\\src\\ch17\\ex02\\hello1"))));
        System.gc();
        assertNull(dataHandler.getLastFile());
    }

}
