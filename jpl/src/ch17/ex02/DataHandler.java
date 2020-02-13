package ch17.ex02;

import java.io.File;
import java.lang.ref.WeakReference;

public class DataHandler {

    private WeakReference<File> lastFile;
    private WeakReference<byte[]> lastData;

    byte[] readFile(File file) {
        byte[] data;

        if (file.equals(lastFile)) {
            data = lastData.get();
            if (data != null) {
                return data;
            }
        }

        data = readBytesFromFile(file);
        lastFile = new WeakReference<>(file);
        lastData = new WeakReference<byte[]>(data);
        return data;
    }

    byte[] readBytesFromFile(File file) {
        return null;
    }

}
