package ch14.ex06;

public class DisplayTime {

    public static long secondFromLaunch;
    public static final Object lock = new Object();

    public static void main(String[] args) {
        Thread messageBy15SecThread = new Thread(() -> {
            Message msg1 = new Message(15);
            while (true) {
                msg1.displayMessage("hehehe", secondFromLaunch);
            }
        });
        Thread displayTimeThread = new Thread(() -> {
            DisplayTime displayTime = new DisplayTime();
            while (true) {
                displayTime.displayTime();
            }
        });
        messageBy15SecThread.start();
        displayTimeThread.start();
    }

    public void displayTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            secondFromLaunch++;
            System.out.println("current second = " + secondFromLaunch);
            lock.notifyAll();
        }
    }

    synchronized public static void displayMessage(String msg) {

    }

}
