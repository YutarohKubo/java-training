package ch02.ex14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    LinkedList list;

    @BeforeEach
    void setup () {
        list = new LinkedList(new Vehicle("takahashi"));
    }

    @Test
    void addVehicle () {
        list.add(new Vehicle("satoh"));
        list.add(new Vehicle("suzuki"));
        list.add(new Vehicle("watanabe"));
        assertEquals(list.get(1).toString(), "satoh");
    }

}