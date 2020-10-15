package ch01.ex03;

import java.io.File;

public class Main {

    private static String CD = System.getProperty("user.dir");
    private static String PARENT = CD + "\\src\\ch01\\ex03\\parent";

    public static void main(String[] args) {
        System.out.println("-----end with .txt-----");
        for (File file : searchFileByLambda("txt")) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static File[] searchFileByLambda(String extension) {
        File file = new File(PARENT);
        return file.listFiles((f, name) -> {
            return f.isFile() && name.endsWith("." + extension);
        });
    }

}
