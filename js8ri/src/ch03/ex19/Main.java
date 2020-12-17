package ch03.ex19;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Answer
 *
 * accumulatorの型をBiFunction<"? super U",? super T,U>と宣言した場合、accumulatorの第一型引数で
 * 与えられるUのsuperTypeがidentityに渡される値に対して恒等変換が成り立たなくなる可能性があるため、
 * ? super Uとしてはいけない.
 *
 * <U> U reduce(U identity,
 *              BiFunction<U,? super T,U> accumulator,
 *              BinaryOperator<U> combiner)
 */
public class Main {

    //じっけん
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaaaa");
        list.add("bbbbb");
        list.add("ccccc");
        list.add("ddddd");
        int result = list.stream().reduce(0, (total, word) -> total + word.length(), (t1, t2) -> t1 + t2);
        int result2 = list.stream().reduce(0, new BiFunction<Integer, CharSequence, Integer>() {
            @Override
            public Integer apply(Integer integer, CharSequence s) {
                return integer + s.length();
            }
        }, (t1, t2) -> t1 + t2);
        List result3 = list.stream().map(String::length).collect(Collectors.toList());
        System.out.println("reduced result2 = " + result2);
        System.out.println("reduced result3 = " + result3.get(0));
    }

}
