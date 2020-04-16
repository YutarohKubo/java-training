package ch20.ex03;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        byte[] buff = new byte[256];
        int b;
        System.out.println(new String(new byte[]{Byte.MAX_VALUE}));
        DecryptInputStream inputStream = new DecryptInputStream(System.in);
        EncryptOutputStream outputStream = new EncryptOutputStream(System.out);
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
    }


}
