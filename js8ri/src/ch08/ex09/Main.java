package ch08.ex09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    static final String CD = System.getProperty("user.dir");

    public static void main(String[] args) throws FileNotFoundException {
        File fileInt = new File(String.format("%s\\src\\ch08\\ex09\\intdoc.txt", CD));
        File fileWord = new File(String.format("%s\\src\\ch08\\ex09\\worddoc.txt", CD));
        File fileDouble = new File(String.format("%s\\src\\ch08\\ex09\\doubledoc.txt", CD));
        Scanner scannerInt = new Scanner(fileInt);
        Scanner scannerWord = new Scanner(fileWord);
        Scanner scannerDouble = new Scanner(fileDouble);
        scannerIntStream(scannerInt).forEach(integer -> {
            System.out.println(integer);
        });
        scannerWordStream(scannerWord).forEach(word -> System.out.println(word));
        scannerLineStream(scannerWord).forEach(word -> System.out.println(word));
        scannerDoubleStream(scannerDouble).forEach(word -> System.out.println(word));
    }

    public static Stream<Integer> scannerIntStream(Scanner scanner) {
        Iterator<Integer> iter = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextInt();
            }

            @Override
            public Integer next() {
                return scanner.nextInt();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<String> scannerWordStream(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }

            @Override
            public String next() {
                return scanner.next();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<String> scannerLineStream(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextLine();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<Double> scannerDoubleStream(Scanner scanner) {
        Iterator<Double> iter = new Iterator<Double>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNextDouble();
            }

            @Override
            public Double next() {
                return scanner.nextDouble();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

}
