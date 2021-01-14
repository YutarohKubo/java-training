package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        // (例)java docdir intent
        if (args.length != 2) {
            System.out.println("argument (path) (regex)");
            return;
        }

        for (Map.Entry<Path, List<String>> entry : grepFilesLine(args[0], args[1]).entrySet()) {
            entry.getValue().forEach(str -> {
                System.out.println(String.format("%s %s", entry.getKey().getFileName(), str));
            });
        }
    }

    static Map<Path, List<String>> grepFilesLine(String pathName, String regex) {
        Map<Path, List<String>> resultMap = new HashMap<>();
        Pattern pattern = Pattern.compile(regex);
        Path targetPath = Paths.get(String.format("%s\\src\\ch08\\ex15\\" + pathName, CD));
        try (Stream<Path> entries = Files.walk(targetPath)) {
            entries.filter(path -> path.toFile().isFile()).forEach(path -> {
                List<String> lineList = new ArrayList<>();
                try (Stream<String> stream = Files.lines(path)) {
                    stream.filter(pattern.asPredicate()).forEach(lineList::add);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!lineList.isEmpty()) {
                    resultMap.put(path, lineList);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}
