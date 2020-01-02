package ch14.ex05;

public class Main {
    public static void main(String[] args) {
        Value value = new Value();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                value.incrementValue();
            }
            for (int i = 0; i < 1000; i++) {
                value.decrementValue();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                value.incrementValue();
            }
            for (int i = 0; i < 1000; i++) {
                value.decrementValue();
            }
        });
        thread1.start();
        thread2.start();
    }
}
