package ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

    public static void main(String[] args) throws IOException {
        convertInputToOutput(System.in, System.out, args[0].charAt(0), args[1].charAt(0));
    }

    private static void convertInputToOutput (InputStream in, OutputStream out, char from, char to) throws IOException {
        byte fromByte = (byte) from;
        byte toByte = (byte) to;
        int b;
        while ((b = in.read()) != -1) {
            out.write(b == fromByte ? toByte : b);
        }
    }

}
