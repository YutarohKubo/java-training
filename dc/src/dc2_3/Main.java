package dc2_3;

public class Main {

    public static void main(String[] args) {
        MyClockFrame myClockFrame = new MyClockFrame();
        MyClockPanel clockPanel = new MyClockPanel(myClockFrame);
        myClockFrame.add(clockPanel);
        myClockFrame.setVisible(true);
    }

}
