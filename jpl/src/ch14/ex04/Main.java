package ch14.ex04;

public class Main {
    public static void main(String[] args) {
        Value value = new Value();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                value.incrementValue();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                value.incrementValue();
            }
        });
        thread1.start();
        thread2.start();
    }
}
