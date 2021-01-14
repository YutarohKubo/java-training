package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        Path pathRead = Paths.get(String.format("%s\\src\\ch09\\ex06\\document1.txt", CD));
        Path pathWrite = Paths.get(String.format("%s\\src\\ch09\\ex06\\out.txt", CD));
        List<String> lines = Files.readAllLines(pathRead);
        Collections.reverse(lines);
        Files.write(pathWrite, lines);
    }

}
