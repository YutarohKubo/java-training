package ch17.ex01;

public class Main {

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long freeMemory1 = rt.freeMemory();
        System.out.println("free memory when app launch = " + freeMemory1);
        for (int i = 0; i < 100000000; i++) {
            new String("hogehogehogehoge");
        }
        long freeMemory2 = rt.freeMemory();
        System.out.println("free memory after created many String = " + freeMemory2);
        rt.gc();
        long freeMemory3 = rt.freeMemory();
        System.out.println("free memory after gc = " + freeMemory3);
    }

}
