package ch02.ex16;

public class LinkedList {

    private Object value;
    private LinkedList next;

    private static LinkedList header = null;
    
    public LinkedList(Object value) {
        this.value = value;
        if (header == null) {
            header = this;
        }
    }

    public void add (Object data) {
        LinkedList list = new LinkedList(data);
        LinkedList ptr = header;

        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = list;
    }

    public Object get(int index) {
        LinkedList ptr = header;
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
        LinkedList ptr = header;
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
        LinkedList ptr = header;
        while (ptr.next != null) {
            builder.append(ptr + " -> ");
            ptr = ptr.next;
        }
        builder.append(ptr);
        return builder.toString();
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList(new Vehicle("takahashi"));
        list.add(new Vehicle("satoh"));
        list.add(new Vehicle("suzuki"));
        list.add(new Vehicle("watanabe"));

        System.out.println(list.size());
    }
}
