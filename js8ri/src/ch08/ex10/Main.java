package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        List<Path> paths = new ArrayList<>();
        Path zipFile = Paths.get(String.format("%s\\srcjdk", CD));
        try (Stream<Path> entries = Files.walk(zipFile)) {
            entries.filter(path -> path.toFile().isFile()).forEach(path -> {
                try (Stream<String> stream = Files.lines(path)) {
                    if (stream.filter(str -> str.matches(".*\\s(transient|volatile)\\s.*")).count() > 0) {
                        paths.add(path);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        paths.forEach(path -> System.out.println("・" + path));
    }

}
