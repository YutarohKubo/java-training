package ch17.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class DataHandler {

    private WeakReference<File> lastFile;
    private WeakReference<byte[]> lastData;

    byte[] readFile(File file) {
        byte[] data;

        File tmpFile = null;
        if (lastFile != null) {
            tmpFile = lastFile.get();
        }
        if (tmpFile != null && tmpFile.equals(file)) {
            /*System.out.println("last file is saved.");
            if (file.equals(tmpFile)) {
                data = lastData.get();
                if (data != null) {
                    System.out.println("last data is saved.");
                    return data;
                }
            }*/
            System.out.println("last file is saved.");
            return readBytesFromFile(tmpFile);
        } else {
            System.out.println("last file is not saved.");
        }

        data = readBytesFromFile(file);
        //このブロックが処理されている間は、fileは強参照なのでlastFileがnullになることはない
        lastFile = new WeakReference<File>(file);
        lastData = new WeakReference<byte[]>(data);
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

    public File getLastFile() {
        return lastFile.get();
    }
}
