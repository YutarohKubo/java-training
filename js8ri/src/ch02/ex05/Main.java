package ch02.ex05;

import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream<Long> stream = lcGenerate(25214903917L, 11L, (long) Math.pow(2, 48));
        stream.limit(100).forEach(System.out::println);
    }

    private static Stream<Long> lcGenerate(long a, long c, long m) {
        return Stream.iterate(0L, x -> (a * x + c) % m);
    }

}
