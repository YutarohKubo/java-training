package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> resultList = new ArrayList<>();
        for (T elem : list) {
            U value = f.apply(elem);
            resultList.add(value);
        }
        return resultList;
    }

}
