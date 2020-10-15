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

    private void displayThreadsAllList(ThreadGroup group) {
        ThreadGroup parentGroup = group;
        System.out.println("-----$$$$$Thread Group List$$$$$-----");
        while (parentGroup.getParent() != null) {
            parentGroup = parentGroup.getParent();
        }
        parentGroup.list();
        System.out.println("-----$$$$$$$$$$$$$$$$$$$$$$$$$$$$-----");
    }

    public void startInspectThreadGroup (ThreadGroup group) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                displayParentGroup(group);
                displayThreadsInGroup(group);
                displayThreadsAllList(group);
            }
        });
        thread.start();
    }

    /**
     * ルートのThreadGroupを得る
     *
     * @param group 検査対象のThreadGroup
     * @return 引数のThreadGroupのルートのThreadGroup
     */
    private static ThreadGroup obtainRootGroup (ThreadGroup group) {
        ThreadGroup rootGroup = group;
        while (rootGroup.getParent() != null) {
            rootGroup = rootGroup.getParent();
        }
        return rootGroup;
    }

    /**
     * サブグループを含むすべてのThreadを出力する
     *
     * @param group 検査対象ThreadGroup
     */
    private static void displayAllThreads(ThreadGroup group) {
        Thread[] threads = new Thread[100];
        group.enumerate(threads);
        System.out.println("-----Thread name in Group : " + group.getName() + "-----");
        for (Thread t : threads) {
            if (t != null) {
                System.out.println(t.getName());
            }
        }
    }

}
