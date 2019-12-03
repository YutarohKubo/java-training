package ch11.ex01;

public class LinkedList<E> {

    private E value;
    private LinkedList<E> next;

    private static LinkedList header;

    public LinkedList(E value) {
        this.value = value;
        if (header == null) {
            header = this;
        }
    }

    public void add (E data) {
        LinkedList<E> list = new LinkedList<>(data);
        LinkedList<E> ptr = header;

        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = list;
    }

    public E get(int index) {
        LinkedList<E> ptr = header;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.value;
    }

    public int size () {
        int size = 0;
        if (header != null) {
            size++;
        }
        LinkedList<E> ptr = header;
        while (ptr.next != null) {
            ptr = ptr.next;
            size++;
        }
        return size;
    }

    @Override
    public String toString () {
        return value.toString();
    }

    public String printList () {
        StringBuilder builder = new StringBuilder();
        LinkedList<E> ptr = header;
        while (ptr.next != null) {
            builder.append(ptr + " -> ");
            ptr = ptr.next;
        }
        builder.append(ptr);
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Vehicle> list = new LinkedList<>(new Vehicle("takahashi"));
        list.add(new Vehicle("satoh"));
        list.add(new Vehicle("suzuki"));
        list.add(new Vehicle("watanabe"));

        System.out.println(list.size());
        System.out.println(list.get(2));
    }
}
