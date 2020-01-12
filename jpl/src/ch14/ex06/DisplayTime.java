package ch14.ex06;

public class DisplayTime {

    public static long secondFromLaunch;
    private static final Message msg1 = new Message(15);
    private static final Message msg2 = new Message(7);

    public static void main(String[] args) {
        Thread messageBy15SecThread = new Thread(() -> {
            while (true) {
                msg1.displayMessage("hehehe");
            }
        });
        Thread messageBy7SecThread = new Thread(() -> {
            while (true) {
                msg2.displayMessage("huhuhu");
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
        messageBy7SecThread.start();
    }

    public void displayTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        secondFromLaunch++;
        System.out.println("current second = " + secondFromLaunch);
        synchronized (msg1) {
            if (secondFromLaunch % 15 == 0) {
                msg1.notify();
            }
        }
        synchronized (msg2) {
            if (secondFromLaunch % 5 == 0) {
                msg2.notify();
            }
        }
    }

    synchronized public static void displayMessage(String msg) {

    }

}
