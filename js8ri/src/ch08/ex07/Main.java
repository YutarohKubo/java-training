package ch08.ex07;

import java.util.Arrays;
import java.util.Comparator;
import static java.util.Comparator.*;

public class Main {

    public static void main(String[] args) {
        Person[] people = new Person[]{
                new Person("koji", "alexander", "murofushi"),
                new Person("waffle", null, "apple"),
                new Person("chocolate", "baseball", "lemon"),
                new Person("hot", null, "cold"),
                new Person("run", "ran", "run"),
                new Person("my", "me", "mine"),
                new Person("your", "you", "yours"),
                new Person("his", "him", "his")
        };

        Arrays.sort(people, comparing(Person::getMiddleName, nullsFirstReverse()));
        Arrays.stream(people).forEach(p -> System.out.println(String.format("%s %s %s", p.getFirstName(), p.getMiddleName(), p.getLastName())));
    }

    static <T> Comparator<Comparable<T>> nullsFirstReverse() {
        Comparator<Comparable<T>> comparator = (t1, t2) -> t2.compareTo((T) t1);
        return nullsFirst(comparator);
    }

}
