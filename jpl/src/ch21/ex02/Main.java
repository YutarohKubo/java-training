package ch21.ex02;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        String cd = System.getProperty("user.dir");
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(i + " : " + new String(dataHandler.readFile(cd + "\\src\\ch21\\ex02\\hello" + j)));
            }
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
