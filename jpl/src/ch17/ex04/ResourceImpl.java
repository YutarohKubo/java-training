package ch17.ex04;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourceImpl implements Resource {

    int keyHash;
    boolean needsRelease = false;
    File file;
    FileOutputStream outputStream;

    public ResourceImpl (Object key) {
        keyHash = System.identityHashCode(key);

        //外部リソースの設定
        String cd = System.getProperty("user.dir");
        file = new File(cd + "\\src\\ch17\\ex03\\hello1");
        try {
            outputStream = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        needsRelease = true;
    }

    @Override
    public void use(Object key, Object... args) {
        if (System.identityHashCode(key) != keyHash) {
            throw new IllegalArgumentException("wrong key");
        }

        //リソースの使用
        try {
            outputStream.write("hello!!!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void release() {
        if (needsRelease) {
            needsRelease = false;
        }

        //リソースの解放
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
