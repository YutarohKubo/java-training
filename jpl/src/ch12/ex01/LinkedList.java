package ch12.ex01;

public class LinkedList implements Cloneable{

    private Object value;
    private LinkedList next;

    private LinkedList header;

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

    public LinkedList find(Object elem) throws ObjectNotFoundException {
        LinkedList ptr = header;
        LinkedList containElemList = new LinkedList(value);

        while (ptr != null) {
            if (ptr.value.equals(elem)) {
                return containElemList;
            }
            ptr = ptr.next;
            if (ptr != null) {
                containElemList.add(ptr.value);
            }
        }
        throw new ObjectNotFoundException(elem);
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

        try {
            LinkedList foundList = list.find(new Vehicle("suzuko"));
            System.out.println(foundList.printList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
