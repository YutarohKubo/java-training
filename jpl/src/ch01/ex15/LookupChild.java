package ch01.ex15;

public interface LookupChild extends Lookup {
    void add(String name, Object value);
    void remove(String name);
}
