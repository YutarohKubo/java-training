package ch02.ex02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    @Test
    void listAddTest () {
        LinkedList linkedList1 = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        LinkedList linkedList4 = new LinkedList();
        linkedList1.value = "sasaki";
        linkedList1.next = linkedList2;
        linkedList2.value = 3;
        linkedList2.next = linkedList3;
        linkedList3.value = 'c';
        linkedList3.next = linkedList4;
        linkedList4.value = "satoh";

        LinkedList ptr = linkedList1;
        for (int i = 0; i < 2; i++) {
            ptr = ptr.next;
        }
        assertEquals('c', ptr.value);
    }

}