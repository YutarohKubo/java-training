package ch09.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        Path pathRead = Paths.get(String.format("%s\\src\\ch09\\ex05\\document1.txt", CD));
        Path pathWrite = Paths.get(String.format("%s\\src\\ch09\\ex05\\out.txt", CD));
        String sentence = new String(Files.readAllBytes(pathRead), StandardCharsets.UTF_8);
        String reverseSentence = new StringBuilder(sentence).reverse().toString();
        Files.write(pathWrite, reverseSentence.getBytes(StandardCharsets.UTF_8));
    }

}
