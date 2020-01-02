package ch14.ex03;

public class Main {
    public static void main(String[] args) {
        Value value = new Value();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    value.incrementValue();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    value.incrementValue();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
