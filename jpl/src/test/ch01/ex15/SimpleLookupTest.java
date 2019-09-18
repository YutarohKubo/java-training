package test.ch01.ex15;

import ch01.ex15.SimpleLookup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLookupTest {

    @Test
    void addNameValue () {
        SimpleLookup lookup = new SimpleLookup();
        lookup.add("aaa", "apple");
        lookup.add("bbb", 2);
        lookup.add("ccc", 'c');
        assertArrayEquals(lookup.getNames().toArray(), new String[]{"aaa", "bbb", "ccc"});
        assertArrayEquals(lookup.getValues().toArray(), new Object[]{"apple", 2, 'c'});
    }

    @Test
    void removeNameValue () {
        SimpleLookup lookup = new SimpleLookup();
        lookup.add("aaa", "apple");
        lookup.add("bbb", 2);
        lookup.remove("aaa");
        lookup.add("ccc", 'c');
        lookup.add("ddd", "damage");
        lookup.remove("eee");
        assertArrayEquals(lookup.getNames().toArray(), new String[]{"bbb", "ccc", "ddd"});
        assertArrayEquals(lookup.getValues().toArray(), new Object[]{2, 'c', "damage"});
    }

    @Test
    void findName () {
        SimpleLookup lookup = new SimpleLookup();
        lookup.add("aaa", "apple");
        lookup.add("bbb", 2);
        lookup.remove("aaa");
        lookup.add("ccc", 'c');
        lookup.add("ddd", "damage");
        lookup.remove("eee");
        assertEquals(lookup.find("ddd"), "damage");
    }

}