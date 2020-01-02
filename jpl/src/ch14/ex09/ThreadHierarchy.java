package ch14.ex09;

public class ThreadHierarchy {

    private void displayParentGroup (ThreadGroup group) {
        ThreadGroup parentGroup = group.getParent();
        System.out.println("-----parent threads of Group : " + group.getName() + "-----");
        while (parentGroup != null) {
            System.out.println(parentGroup.getName());
            parentGroup = parentGroup.getParent();
        }
    }

    private void displayThreadsInGroup (ThreadGroup group) {
        Thread[] threads = new Thread[100];
        group.enumerate(threads, false);
        System.out.println("-----Thread name in Group : " + group.getName() + "-----");
        for (Thread t : threads) {
            if (t != null) {
                System.out.println(t.getName());
            }
        }
    }

    public void startInspectThreadGroup (ThreadGroup group) {
        Thread thread = new Thread(() -> {
            while (true) {try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                displayParentGroup(group);
                displayThreadsInGroup(group);
            }
        });
        thread.start();
    }

}
