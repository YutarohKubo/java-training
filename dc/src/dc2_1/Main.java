package dc2_1;

public class Main {

    public static void main(String[] args) {
        MyClockFrame myClockFrame = new MyClockFrame("My Clock");
        MyClockPanel clockPanel = new MyClockPanel();
        myClockFrame.add(clockPanel);
        myClockFrame.setVisible(true);
    }

}
