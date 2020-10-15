package ch01.ex07;

public class Main {

    public static void main(String[] args) {
        new Thread(andThen(() -> {
            System.out.println("prepare");
        }, () -> {
            System.out.println("running");
        })).start();
    }

    public static Runnable andThen(Runnable runnable1, Runnable runnable2) {
        runnable1.run();
        return () -> {
            runnable2.run();
        };
    }

}
