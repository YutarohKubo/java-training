package ch13.ex02;

public class Main {

    public static void main(String[] args) {
        System.out.println(countAppearanceString("hello1 hello1 hello1!", "hello1"));
    }

    public static int countAppearanceString(String str, String testStr) {
        int counter = 0;
        for (int i = 0; i < str.length() - testStr.length() + 1; i++) {
            if (str.substring(i, i + testStr.length()).equals(testStr)) {
                counter++;
            }
        }
        return counter;
    }
}
