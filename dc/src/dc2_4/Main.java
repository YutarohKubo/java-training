package dc2_4;

public class Main {

    public static void main(String[] args) {
        MyClockFrame myClockFrame = new MyClockFrame();
        MyClockPanel clockPanel = new MyClockPanel(myClockFrame);
        myClockFrame.add(clockPanel);
        myClockFrame.setVisible(true);
    }

}
