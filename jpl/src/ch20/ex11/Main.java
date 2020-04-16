package ch20.ex11;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String cd = System.getProperty("user.dir");
        File dir = new File(cd + "\\src\\ch20\\ex11");
        List<File> childFiles = getAllChildFile(dir);
        System.out.println("-----------childFiles--------------");
        for (File childFile : childFiles) {
            System.out.println(childFile.getName());
        }
        System.out.println("-----------------------------------");
    }

    private static List<File> getAllChildFile(File dir) {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException();
        }
        List<File> findFiles = new ArrayList<>();
        final File[] files = dir.listFiles(new TextFileFilter("a"));
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    List<File> childFiles = getAllChildFile(files[i]);
                    findFiles.addAll(childFiles);
                }
                if (files[i].isFile()) {
                    findFiles.add(files[i]);
                }
            }
        }
        return findFiles;
    }

}
