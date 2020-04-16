package ch14.ex05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final Object mainLock = new Object();

    @Test
    public void testIncrementDecrement() {
        Value value = new Value();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                value.decrementValue();
            }
            for (int i = 0; i < 1000; i++) {
                value.incrementValue();
            }
            synchronized (mainLock) {
                mainLock.notifyAll();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                value.incrementValue();
            }
            for (int i = 0; i < 1000; i++) {
                value.decrementValue();
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
        assertEquals(Value.value, 0);
    }

}
