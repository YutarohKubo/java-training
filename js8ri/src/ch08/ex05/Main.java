package ch08.ex05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) {
        File file = new File(String.format("%s\\src\\ch06\\ex06\\document1.txt", CD));
        List<String> words1 = new ArrayList<>();
        List<String> words2 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("[\\P{L}]+");
                words1.addAll(Arrays.asList(arr));
                words2.addAll(Arrays.asList(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime1 = System.currentTimeMillis();
        filter(words1);
        System.out.println("filter running time : " + (System.currentTimeMillis() - startTime1));

        long startTime2 = System.currentTimeMillis();
        removeIf(words1);
        System.out.println("removeIf running time : " + (System.currentTimeMillis() - startTime2));
    }

    public static void removeIf(List<String> stringList) {
        stringList.removeIf(w -> w.length() <= 12);
        System.out.println("result of removeIf list size : " + stringList.size());
    }

    public static void filter(List<String> stringList) {
        System.out.println("result of filter list size : " + stringList.stream().filter(w -> w.length() > 12).count());
    }

}
