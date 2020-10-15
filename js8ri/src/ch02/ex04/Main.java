package ch02.ex04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};
        // int[]の要素一つのストリーム
        Stream<int[]> stream = Stream.of(values);

        // intのストリーム
        IntStream intStream = IntStream.of(1, 4, 9, 16);
    }

}
