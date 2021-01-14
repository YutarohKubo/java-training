package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {

    private static final String CD = System.getProperty("user.dir");
    private static final Path pathWrite = Paths.get(String.format("%s\\src\\ch09\\ex07\\out.html", CD));

    public static void main(String[] args) {
        copyWebPageToFile("https://www.google.com");
    }

    private static void copyWebPageToFile(String urlStr) {
        InputStream in = null;
        try {
            URL url = new URL(urlStr);
            in = url.openStream();
            Files.copy(in, pathWrite, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
