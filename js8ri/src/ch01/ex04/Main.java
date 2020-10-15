package ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class Main {

    private static String CD = System.getProperty("user.dir");
    private static String PARENT = CD + "\\src\\ch01\\ex04\\parent";

    public static void main(String[] args) {
        File[] files = new File(PARENT).listFiles();
        Arrays.sort(files, (file1, file2) -> {
            if ((file1.isDirectory() && file2.isDirectory()) ||
                    (file1.isFile() && file2.isFile())) {
                return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            }
            if (file1.isFile() && file2.isDirectory()) {
                return 1;
            } else {
                return -1;
            }
        });

        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

}
