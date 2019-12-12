package ch13.ex03;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String[] str = delimitedAllString("hello");
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }

    public static String[] delimitedAllString (String str) {
        ArrayList<String> listOutput = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length() - i; j++) {
                listOutput.add(str.substring(j, j + i + 1));
            }
        }
        return listOutput.toArray(new String[listOutput.size()]);
    }
}
