package ch14.ex04;

public class Value {
    public static int value = 0;

    synchronized public static void incrementValue () {
        value++;
        System.out.println("value = " + value);
    }
}
