package ch03.ex07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("super apple");
        list.add("Apple is Delicious");
        list.add("I am so happy");
        list.add("Iamsohappy");
        list.add(" apple is delicious");
        list.sort(comparator1());
        System.out.println("----comparator1-----");
        list.forEach(System.out::println);
        list.sort(comparator2());
        System.out.println("----comparator2-----");
        list.forEach(System.out::println);
        list.sort(comparator3());
        System.out.println("----comparator3-----");
        list.forEach(System.out::println);
        list.sort(comparator4());
        System.out.println("----comparator4-----");
        list.forEach(System.out::println);

    }

    /**
     * 普通の順序のComparatorを生成する
     *
     * @return 普通の順序のComparator
     */
    private static Comparator<String> comparator1() {
        return (s1, s2) -> s1.compareTo(s2);
    }

    /**
     * 逆順のComparatorを生成する
     *
     * @return 逆順のComparator
     */
    private static Comparator<String> comparator2() {
        return (s1, s2) -> s2.compareTo(s1);
    }

    /**
     * 大文字小文字を区別しない
     */
    private static Comparator<String> comparator3 () {
        return (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase());
    }

    /**
     * 空白を除外する
     */
    private static Comparator<String> comparator4() {
        return (s1, s2) -> s1.replaceAll("\\s", "").compareTo(s2.replaceAll("\\s", ""));
    }

}
