package ch20.ex04;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferFilterReader reader = new BufferFilterReader(new InputStreamReader(System.in));
        System.out.println(reader.readLine());
    }

}
