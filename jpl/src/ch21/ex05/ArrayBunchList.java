package ch21.ex05;

import java.util.*;

public class ArrayBunchList<E> extends AbstractList<E> {
    private final E[][] arrays;
    private final int size;

    public ArrayBunchList(E[][] arrays) {
        this.arrays = arrays.clone();
        int s = 0;
        for (E[] array : arrays) {
            s += array.length;
        }
        size = s;
    }

    @Override
    public E get(int index) {
        int off = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < off + arrays[i].length) {
                return arrays[i][index - off];
            }
            off += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public E set(int index, E element) {
        int off = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < off + arrays[i].length) {
                E ret = arrays[i][index - off];
                arrays[i][index - off] = element;
                return ret;
            }
            off += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ABLIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ABLListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ABLListIterator(index);
    }

    private class ABLIterator implements Iterator<E> {
        int off;
        int array;
        int pos;
        int lastArray = -1;
        int lastPos = -1; // index of last element returned; -1 if no such

        ABLIterator () {
            off = 0;
            array = 0;
            pos = 0;

            for (array = 0; array < arrays.length; array++) {
                if (arrays[array].length > 0) {
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return off + pos < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastArray = array;
            lastPos = pos;
            E ret = arrays[array][pos++];

            while (pos >= arrays[array].length) {
                off += arrays[array++].length;
                pos = 0;
                if (array >= arrays.length) {
                    break;
                }
            }
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ABLListIterator extends ABLIterator implements ListIterator<E> {

        ABLListIterator(int index) {
            if (index < 0 || index > size()) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                off = 0;
            } else {
                int cursor = 1;
                out:
                for (array = 0; array < arrays.length; array++) {
                    for (pos = 0; pos < arrays[array].length; pos++) {
                        if (index == cursor) {
                            break out;
                        }
                        cursor++;
                    }
                    off += arrays[array].length;
                }
                if (index == size()) {
                    pos++;
                }
            }
        }

        @Override
        public boolean hasPrevious() {
            return off + pos != 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            pos--;
            while (pos < 0) {
                off -= arrays[array].length;
                if (array-- == 0) {
                    break;
                }
                pos = arrays[array].length - 1;
            }
            lastArray = array;
            lastPos = pos;
            return arrays[array][pos];
        }

        @Override
        public int nextIndex() {
            return off + pos;
        }

        @Override
        public int previousIndex() {
            return off + pos - 1;
        }

        @Override
        public void set(E o) {
            if (lastPos < 0 || lastArray < 0)
                throw new IllegalStateException();

            try {
                arrays[lastArray][lastPos] = o;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E o) {
            throw new UnsupportedOperationException();
        }
    }

}
