package ch02.ex02;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("apple");
        wordList.add("game");
        wordList.add("pen");
        wordList.add("meron");
        wordList.add("book");
        wordList.add("pi");
        wordList.add("special waffle");
        wordList.add("osushi");
        wordList.add("bad chocolate");
        wordList.add("book");
        Object[] result = wordList.stream().filter(s -> {
            System.out.println("target string = " + s);
            return s.length() >= 4;
        }).limit(5).toArray();
    }

}
