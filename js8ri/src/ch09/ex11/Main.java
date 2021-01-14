package ch09.ex11;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        /*Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs) {
            System.out.println(name);
        }*/

        ProcessBuilder processBuilder = new ProcessBuilder(
                "grep", "-r", "-e", "[0-9]\\{7\\}", String.format("%s\\src\\ch09\\ex11\\docdir\\*", CD)
        );
        processBuilder.redirectOutput(Paths.get(String.format("%s\\src\\ch09\\ex11\\out.txt", CD)).toFile());
        Process process = null;
        try {
            process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
