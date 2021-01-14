package ch08.ex03;

/**
 * gcd1(), gcd2(), gcd3()でそんなにコード量は変わらない.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(36 % 15);
        System.out.println(-36 % 15);
        System.out.println(36 % -15);
        System.out.println(-36 % -15);
        System.out.println(Math.floorMod(36, 15));
        System.out.println(Math.floorMod(-36, 15));
        System.out.println(Math.floorMod(36, -15));
        System.out.println(Math.floorMod(-36, -15));
        System.out.println(rem(36, 15));
        System.out.println(rem(-36, 15));
        System.out.println(rem(36, -15));
        System.out.println(rem(-36, -15));
        System.out.println(gcd1(38, 15));
        System.out.println(gcd1(-38, 15));
        System.out.println(gcd1(38, -15));
        System.out.println(gcd1(-38, -15));
        System.out.println(gcd2(38, 15));
        System.out.println(gcd2(-38, 15));
        System.out.println(gcd2(38, -15));
        System.out.println(gcd2(-38, -15));
        System.out.println(gcd3(38, 15));
        System.out.println(gcd3(-38, 15));
        System.out.println(gcd3(38, -15));
        System.out.println(gcd3(-38, -15));
    }

    static int gcd1(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd1(b, a % b);
    }

    static int gcd2(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd2(b, Math.floorMod(a, b));
    }

    static int gcd3(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd3(b, rem(a, b));
    }

    private static int rem(int a, int b) {
        return a > 0 && b > 0 ? a % b : (a % b + Math.abs(b)) % Math.abs(b);
    }

}
