package ch14.ex04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestValueIncrement {

    private final Object mainLock = new Object();

    @Test
    public synchronized void incrementValue() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                Value.incrementValue();
            }
            synchronized (mainLock) {
                mainLock.notifyAll();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                Value.incrementValue();
            }
            synchronized (mainLock) {
                mainLock.notifyAll();
            }
        });
        thread1.start();
        thread2.start();

        synchronized (mainLock) {
            for (int i = 0; i < 2; i++) {
                try {
                    mainLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        assertEquals(Value.value, 20000);
    }

}
