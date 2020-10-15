package dc2_4;

public class Main {

    public static void main(String[] args) {
        System.out.println("thread running main = " + Thread.currentThread());
        displayThreadsAllList(Thread.currentThread().getThreadGroup());
        MyClockFrame myClockFrame = new MyClockFrame();
        MyClockPanel clockPanel = new MyClockPanel(myClockFrame);
        displayThreadsAllList(Thread.currentThread().getThreadGroup());
        myClockFrame.add(clockPanel);
        myClockFrame.setVisible(true);
        displayThreadsAllList(Thread.currentThread().getThreadGroup());
    }

    public static void displayThreadsAllList(ThreadGroup group) {
        ThreadGroup parentGroup = group;
        System.out.println("-----$$$$$Thread Group List$$$$$-----");
        while (parentGroup.getParent() != null) {
            parentGroup = parentGroup.getParent();
        }
        parentGroup.list();
        System.out.println("-----$$$$$$$$$$$$$$$$$$$$$$$$$$$$-----");
    }

}
