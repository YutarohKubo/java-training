package ch11.ex02;

public class Attr<E> {
    private final String name;
    private E value = null;

    public Attr(String name) {
        this(name, null);
    }

    public Attr(String name, E value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public E getValue() {
        return value;
    }

    public E setValue(E newValue) {
        E oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
}
