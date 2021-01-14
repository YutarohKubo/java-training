package ch06.ex07;

import java.util.concurrent.ConcurrentHashMap;

public class Main {

    static ConcurrentHashMap<String, Long> map;

    public static void main(String[] args) {
        init();
        map.put("waffle", 200L);
        map.put("meron", 700L);
        map.put("apple", 400L);
        map.put("chocolate", 100L);
        map.put("sushi", 600L);
        System.out.println("max key = " + getMaxKey());
    }

    static void init() {
        map = new ConcurrentHashMap<>();
    }

    static String getMaxKey() {
        return map.reduceEntries(1, (stringLongEntry, stringLongEntry2) ->
                Long.max(stringLongEntry.getValue(), stringLongEntry2.getValue()) == stringLongEntry.getValue() ? stringLongEntry : stringLongEntry2).getKey();
    }

}
