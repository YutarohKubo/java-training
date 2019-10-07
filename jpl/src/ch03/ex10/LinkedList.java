package ch03.ex10;

public class LinkedList implements Cloneable{

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

    @Override
    protected LinkedList clone() {
        try {
            return (LinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
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

        LinkedList cloneList = list.clone();
        cloneList.size();
        ((Vehicle) cloneList.get(1)).setOwnerName("murata");

        System.out.println(list.printList());
        System.out.println(cloneList.printList());
    }
}
