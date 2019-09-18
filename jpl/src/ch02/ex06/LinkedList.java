package ch02.ex06;

public class LinkedList {
    public Object value;
    public LinkedList next;

    public static void main(String[] args) {
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
    }
}
