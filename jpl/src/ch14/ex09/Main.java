package ch14.ex09;

public class Main {

    public static void main(String[] args) {
        ThreadGroup groupParent = new ThreadGroup("parent");
        ThreadGroup groupChild1 = new ThreadGroup(groupParent, "child1");
        ThreadGroup groupChild2 = new ThreadGroup(groupParent, "child2");
        Thread thread1 = new Thread(groupParent, () -> {
            System.out.println("start thread1");
            while (true) {

            }
        }, "thread1");
        Thread thread2 = new Thread(groupParent, () -> {
            System.out.println("start thread2");
            while (true) {

            }
        }, "thread2");
        Thread thread3 = new Thread(groupChild1, () -> {
            System.out.println("start thread3");
            while (true) {

            }
        }, "thread3");
        Thread thread4 = new Thread(groupChild2, () -> {
            System.out.println("start thread4");
            while (true) {

            }
        }, "thread4");
        Thread thread5 = new Thread(groupChild2, () -> {
            System.out.println("start thread5");
            while (true) {

            }
        }, "thread5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        ThreadHierarchy th = new ThreadHierarchy();
        th.startInspectThreadGroup(groupChild1);
        th.startInspectThreadGroup(groupChild2);
    }

}
