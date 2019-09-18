package ch02.ex11;

public class LinkedList {
    Object value;
    LinkedList next;

    public LinkedList (Object value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return value.toString();
    }

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList(new Vehicle("takahashi"));
        LinkedList list2 = new LinkedList(new Vehicle("satoh"));
        LinkedList list3 = new LinkedList(new Vehicle("suzuki"));
        LinkedList list4 = new LinkedList(new Vehicle("watanabe"));

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;

        StringBuilder builder = new StringBuilder();
        LinkedList ptr = list1;
        while (ptr.next != null) {
            builder.append(ptr + " -> ");
            ptr = ptr.next;
        }
        builder.append(ptr);
        System.out.println(builder.toString());
    }
}
