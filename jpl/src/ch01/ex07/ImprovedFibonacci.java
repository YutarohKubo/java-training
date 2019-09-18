package ch01.ex07;

public class ImprovedFibonacci {
    static final int MAX_INDEX = 9;

    public static void main(String[] args) {
        int lo = 34;
        int hi = 21;
        String mark;

        System.out.println("1: " + lo);
        for (int i = 2; i <= MAX_INDEX; i++) {
            if (hi % 2 == 0) {
                mark = " *";
            } else {
                mark = "";
            }
            System.out.println(i + ": " + hi + mark);
            hi = lo - hi;
            lo = lo - hi;
        }
    }
}
