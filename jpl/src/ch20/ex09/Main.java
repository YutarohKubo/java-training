package ch20.ex09;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)  throws FileNotFoundException {
        String cd = System.getProperty("user.dir");
        showFileInfo(cd + "\\src\\ch20\\ex09");
    }

    private static void showFileInfo (String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (file.isFile()) {
            System.out.println("this is File.");
            System.out.println("file length is " + file.length());
        }
        if (file.isDirectory()) {
            System.out.println("this is directory");
            List<File> childFiles = getAllChildFile(file);
            System.out.println("-----------childFiles--------------");
            for (File childFile : childFiles) {
                System.out.println(childFile.getName());
            }
            System.out.println("-----------------------------------");
        }
        System.out.println("last modified is " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(file.lastModified()));
    }

    private static List<File> getAllChildFile(File dir) {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException();
        }
        List<File> findFiles = new ArrayList<>();
        final File[] files = dir.listFiles();
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
