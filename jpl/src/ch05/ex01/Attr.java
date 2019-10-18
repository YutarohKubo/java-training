package ch05.ex01;

import java.util.Iterator;

public class Attr {

    public interface Attributed {
        void add(Attr newAttr);
        Attr find(String attrName);
        Attr remove(String attrName);
        Iterator<Attr> attrs();
    }

    private final String name;
    private Object value = null;

    public Attr(String name) {
        this(name, null);
    }

    public Attr(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
}
