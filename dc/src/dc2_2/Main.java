package dc2_2;

public class Main {

    public static void main(String[] args) {
        MyClockFrame myClockFrame = new MyClockFrame("My Clock");
        MyClockPanel clockPanel = new MyClockPanel(myClockFrame);
        myClockFrame.add(clockPanel);
        myClockFrame.setVisible(true);
    }

}
