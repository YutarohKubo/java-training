package ch01.ex02;

import java.io.File;

public class Main {

    private static String CD = System.getProperty("user.dir");
    private static String PARENT = CD + "\\src\\ch01\\ex02\\parent";

    public static void main(String[] args) {
        System.out.println("-----by lambda-----");
        for (File file : getSubDirByLambda()) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("-----by method reference-----");
        for (File file : getSubDirByMethodRef()) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static File[] getSubDirByLambda() {
        File file = new File(PARENT);
        return file.listFiles((f) -> {
            return f.isDirectory();
        });
    }

    private static File[] getSubDirByMethodRef() {
        File file = new File(PARENT);
        return file.listFiles(File::isDirectory);
    }

}
