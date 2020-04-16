package ch14.ex05;

public class Value {
    public static final Object lock = new Object();
    public static int value = 0;

    public void incrementValue () {
        synchronized (lock) {
            value++;
            System.out.println("after incremented value = " + value);
        }
    }

    public void decrementValue () {
        synchronized (lock) {
            value--;
            System.out.println("after decremented value = " + value);
        }
    }
}
