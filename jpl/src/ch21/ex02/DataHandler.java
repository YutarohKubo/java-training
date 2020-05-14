package ch21.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.WeakHashMap;

public class DataHandler {

    private WeakHashMap<String, File> fileMap = new WeakHashMap<>();

    byte[] readFile(String fileName) {
        byte[] data;

        File tmpFile = fileMap.get(fileName);
        if (tmpFile != null) {
            System.out.println("last file is saved.");
            return readBytesFromFile(tmpFile);
        }

        System.out.println("last file is not saved.");
        tmpFile = new File(fileName);
        data = readBytesFromFile(tmpFile);
        //このブロックが処理されている間は、fileNameは強参照なのでキーバリューが消滅することはない
        fileMap.put(fileName, tmpFile);
        return data;
    }

    byte[] readBytesFromFile(File file) {
        byte[] buff = new byte[1024];
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff;
    }

    public int getFileMapSize() {
        System.out.println(fileMap);
        return fileMap.size();
    }
}
