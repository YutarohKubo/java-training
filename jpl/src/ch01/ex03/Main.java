package ch01.ex03;

public class Main {
    public static void main(String[] args) {
        int lo = 1;
        int hi = 1;
        System.out.println("fibonacci program");
        System.out.println(lo);
        while (hi < 50) {
            System.out.println(hi);
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}