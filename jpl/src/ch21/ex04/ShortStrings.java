package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String> {

    private ListIterator<String> strings; //元文字列
    private String nextShort; //次が不明ならばnull
    private String prevShort;
    private final int maxLen; //この長さ以下の文字列だけを返す

    public ShortStrings(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
        prevShort = null;
    }

    @Override
    public boolean hasPrevious() {
        if (prevShort != null) {
            return true;
        }
        while(strings.hasPrevious()) {
            prevShort = strings.previous();
            if (prevShort.length() <= maxLen) {
                return true;
            }
        }
        prevShort = null;
        return false;
    }

    @Override
    public boolean hasNext() {
        if (nextShort != null) {
            return true;
        }
        while(strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen) {
                return true;
            }
        }
        nextShort = null;
        return false;
    }

    @Override
    public String previous() {
        if (prevShort == null && !hasPrevious()) {
            throw new NoSuchElementException();
        }
        String p = prevShort;
        prevShort = null;
        return p;
    }

    @Override
    public String next() {
        if (nextShort == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        String n = nextShort;
        nextShort = null;
        return n;
    }

    @Override
    public int nextIndex() {
        return strings.nextIndex();
    }

    @Override
    public int previousIndex() {
        return strings.previousIndex();
    }

    @Override
    public void set(String s) {
        strings.set(s);
    }

    @Override
    public void add(String s) {
        strings.add(s);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
