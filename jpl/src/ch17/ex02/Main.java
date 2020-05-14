package ch17.ex02;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        String cd = System.getProperty("user.dir");
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + " : " + new String(dataHandler.readFile(new File(cd + "\\src\\ch17\\ex02\\hello"))));
            useManyMemory();
        }
    }

    private static void useManyMemory() {
        String cd = System.getProperty("user.dir");
        for (int i = 0; i < 100000; i++) {
            new File(cd + "\\src\\ch17\\ex02\\aaaaaaaa");
        }
    }

}
