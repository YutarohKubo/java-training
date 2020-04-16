package ch20.ex04;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class BufferFilterReader extends FilterReader {

    private static int MAX_BUFFER_SIZE = 256;

    public BufferFilterReader(Reader in) {
        super(in);
    }

    public String readLine () throws IOException {
        char[] buff = new char[MAX_BUFFER_SIZE];
        int c;
        int pointer = 0;
        do {
            c = read();
            buff[pointer] = (char) c;
            pointer++;
        } while (c != '\n' && c != -1 && pointer != MAX_BUFFER_SIZE);
        return new String(buff);
    }
}
