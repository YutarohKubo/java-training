package ch14.ex02;

public class PrintServer implements Runnable {

    private final String PRINT_PROCESS_THREAD_NAME = "thread_print_process";
    private final PrintQueue requests = new PrintQueue();

    public PrintServer () {
        new Thread(this, PRINT_PROCESS_THREAD_NAME).start();
    }

    public void print(PrintJob job) {
        requests.add(job);
    }

    @Override
    public void run() {
        for(;;) {
            if (Thread.currentThread().getName().equals(PRINT_PROCESS_THREAD_NAME)) {
                realPrint(requests.remove());
                System.out.println("current thread is " + Thread.currentThread().getName());
            } else {
                System.out.println("Invalid thread....");
                break;
            }
        }
    }

    private void realPrint(PrintJob job) {
        //実際のプリント処理を行う
        System.out.println("印刷してます");
    }

    public static void main(String[] args) {
        new PrintServer();
    }
}
