package ch14.ex08;

public class Friendly {

    private Friendly partner;
    private String name;

    public Friendly(String name) {
        this.name = name;
    }

    synchronized public void hug () {
        System.out.println(Thread.currentThread().getName() + " in " + name + ".hug trying to invoke " + partner.name + ".hugBack()");
        partner.hugBack();
    }

    synchronized private void hugBack () {
        System.out.println(Thread.currentThread().getName() + " in " + name + "hugBack()");
    }

    public void becomeFriend(Friendly partner) {
        this.partner = partner;
    }

    public static void main(String[] args) {
        final Friendly jareth = new Friendly("jareth");
        final Friendly cory = new Friendly("cory");

        jareth.becomeFriend(cory);
        cory.becomeFriend(jareth);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("hug from jareth count " + (i + 1) + " time.");
                    jareth.hug();
                }
            }
        }, "Thread1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("hug from cory count " + (i + 1) + " time.");
                    cory.hug();
                }
            }
        }, "Thread2").start();
    }

}
