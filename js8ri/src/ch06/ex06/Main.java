package ch06.ex06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Main {

    static final String CD = System.getProperty("user.dir");
    static final int FILE_NUM = 3;
    static ConcurrentHashMap<String, Set<File>> mapWords;
    private static long startTime;

    public static void main(String[] args) {
        init();
        startTime = System.currentTimeMillis();
        File[] files = new File[FILE_NUM];
        for (int i = 0; i < FILE_NUM; i++) {
            files[i] = new File(String.format("%s\\src\\ch06\\ex06\\document%d.txt", CD, i + 1));
        }
        operateMap(files);
        printMap();
    }

    static void init() {
        mapWords = new ConcurrentHashMap<>();
    }

    /**
     * マップに詰め込む処理
     * computeIfAbsentでは、FileのSetがnewされる処理が、
     * キーが初めてMapに登録された段階に限定されている点でメリットがある.
     * (mergeでは、mapにキーを重複して再登録する際にも、Setをnewしていた.)
     *
     * @param files 読み込むファイルの配列
     */
    static void operateMap(File[] files) {
        if (Objects.isNull(files)) {
            return;
        }
        Stream.of(files).forEach(file -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    String[] arr = line.split("[\\P{L}]+");
                    Stream.of(arr).parallel().forEach(word -> mapWords.computeIfAbsent(word, k -> new HashSet<>()).add(file));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void printMap() {
        for (Map.Entry<String, Set<File>> entry : mapWords.entrySet()) {
            String word = entry.getKey();
            System.out.println("word = " + word);
            for (File file : mapWords.get(word)) {
                System.out.println("・" + file.getName());
            }
        }
        System.out.println("finish (" + (System.currentTimeMillis() - startTime) + ") [mSec]");
    }

}
