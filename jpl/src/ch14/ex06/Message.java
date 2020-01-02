package ch14.ex06;

public class Message {

    private long everySecond;

    public Message(long everySecond) {
        this.everySecond = everySecond;
    }

    public void displayMessage(String msg, long secondFromLaunch) {
        synchronized (DisplayTime.lock) {
            if (secondFromLaunch % everySecond == 0) {
                System.out.println(msg);
            }
            try {
                DisplayTime.lock.wait(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
