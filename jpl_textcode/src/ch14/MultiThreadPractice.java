package ch14;

public class MultiThreadPractice {

    public static Runnable task;

    public static void main(String[] args) {
        MultiThreadPractice multiThreadPractice = new MultiThreadPractice();
        multiThreadPractice.createTask();
    }

    private void createTask () {
        if (task != null) {
            task = new Task();
        }
        Thread th1 = new Thread(task);
        th1.start();
    }

    private void notify (Object lock) {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    class Task implements Runnable {
        @Override
        synchronized public void run() {
            System.out.println("Task is launch");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
