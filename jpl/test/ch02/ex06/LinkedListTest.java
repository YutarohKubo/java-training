package ch02.ex06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    @Test
    void addVehicle () {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list3 = new LinkedList();
        LinkedList list4 = new LinkedList();

        list1.value = new Vehicle("takahashi");
        list1.next = list2;
        list2.value = new Vehicle("satoh");
        list2.next = list3;
        list3.value = new Vehicle("suzuki");
        list3.next = list4;
        list4.value = new Vehicle("watanabe");

        LinkedList ptr = list1;
        for (int i = 0; i < 2; i++) {
            ptr = ptr.next;
        }
        assertEquals("suzuki", ptr.value.toString());
    }

}