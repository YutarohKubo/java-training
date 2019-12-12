package ch11.ex03;

import java.util.Iterator;

public interface Attributed<E> {
    void add(Attr<E> newAttr);
    Attr<E> find(String attrName);
    Attr<E> remove(String attrName);
    Iterator<Attr<E>> attrs();
}
