package ch21.ex04;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> listStr = new ArrayList<>();
        listStr.add("hahahaha");
        listStr.add("hihihihihihi");
        listStr.add("huhuhu");
        listStr.add("hehehehe");
        for (ShortStrings shortStrings = new ShortStrings(listStr.listIterator(listStr.size()), 10); shortStrings.hasPrevious();) {
            String str = shortStrings.previous();
            System.out.println("str = " + str);
        }
    }

}
