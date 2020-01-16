package ch14.ex06;

public class Message {

    private long everySecond;

    public Message(long everySecond) {
        this.everySecond = everySecond;
    }

    synchronized public void displayMessage(String msg) {
        //if (secondFromLaunch % everySecond == 0) {
        System.out.println(msg);
        //}
        System.out.println("run message every " + everySecond + " sec");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
