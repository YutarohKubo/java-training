package ch20.ex03;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EncryptDecryptTest {

    @Test
    public void test1() throws IOException {
        byte[] buff = new byte[256];
        int b;
        DecryptInputStream inputStream = new DecryptInputStream(System.in);
        EncryptOutputStream outputStream = new EncryptOutputStream(System.out);
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
    }

}
