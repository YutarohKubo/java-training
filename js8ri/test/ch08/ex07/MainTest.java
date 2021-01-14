package ch08.ex07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static ch08.ex07.Main.nullsFirstReverse;
import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MainTest {

    @Test
    public void testNullsFirstReverse() {
        Person person1 = new Person("koji", "alexander", "murofushi");
        Person person2 = new Person("waffle", null, "apple");
        Person person3 = new Person("chocolate", "baseball", "lemon");
        Person person4 = new Person("run", "ran", "run");
        Person person5 = new Person("my", "me", "mine");
        Person person6 = new Person("your", "you", "yours");
        Person person7 = new Person("his", "him", "his");
        Person[] people = new Person[]{
                person1, person2, person3, person4, person5, person6, person7
        };

        Person[] expected = new Person[] {
                person2, person6, person4, person5, person7, person3, person1
        };

        Arrays.sort(people, comparing(Person::getMiddleName, nullsFirstReverse()));
        Arrays.stream(people).forEach(p -> System.out.println(String.format("%s %s %s", p.getFirstName(), p.getMiddleName(), p.getLastName())));

        assertArrayEquals(expected, people);
    }

}
