package ch02.ex16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    @Test
    void listSize () {
        LinkedList list = new LinkedList(new Vehicle("takahashi"));
        list.add(new Vehicle("satoh"));
        list.add(new Vehicle("suzuki"));
        list.add(new Vehicle("watanabe"));

        assertEquals(4, list.size());
    }

}