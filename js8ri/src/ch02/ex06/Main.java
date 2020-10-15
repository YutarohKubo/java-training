package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        charactorStream("abcdefgh").forEach((ch) -> {
            System.out.println(ch);
        });
    }

    private static Stream<Character> charactorStream(String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt);
    }

}
