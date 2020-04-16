package ch20.ex06;

import java.util.Iterator;

public interface Attributed {
    void add(Attr newAttr);
    Attr find(String attrName);
    Attr remove(String attrName);
    boolean containsKey(String attrName);
    Iterator<Attr> attrs();
}
