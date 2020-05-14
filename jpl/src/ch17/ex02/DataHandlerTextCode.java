package ch17.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * 本のコードなので、無視
 */
public class DataHandlerTextCode {

    private File lastFile;
    private WeakReference<byte[]> lastData;

    byte[] readFile(File file) {
        byte[] data;

        if (file.equals(lastFile)) {
            data = lastData.get();
            if (data != null) {
                System.out.println("last data is saved.");
                return data;
            }
        }

        data = readBytesFromFile(file);
        System.out.println("last data is not saved");
        lastFile = file;
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
}
