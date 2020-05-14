package ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack<E> extends ArrayList<E> {

    /**
     * Creates an empty Stack.
     */
    public MyStack() {
    }

    public E push(E item) {
        add(item);

        return item;
    }

    public synchronized E pop() {
        E obj;
        int len = size();

        obj = peek();
        remove(len - 1);

        return obj;
    }

    public synchronized E peek() {
        int len = size();

        if (len == 0)
            throw new EmptyStackException();
        return get(len - 1);
    }

    public boolean empty() {
        return size() == 0;
    }

    public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }

}
