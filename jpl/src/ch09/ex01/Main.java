package ch09.ex01;

public class Main {

    public static void main(String[] args) {
        System.out.println("Posi Inf + Posi Inf" + (Double.POSITIVE_INFINITY + Double.POSITIVE_INFINITY));
        System.out.println("Posi Inf + Nega Inf" + (Double.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY));
        System.out.println("Posi Inf - Posi Inf" + (Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY));
        System.out.println("Posi Inf - Nega Inf" + (Double.POSITIVE_INFINITY - Double.NEGATIVE_INFINITY));
        System.out.println("Posi Inf * Posi Inf" + (Double.POSITIVE_INFINITY * Double.POSITIVE_INFINITY));
        System.out.println("Posi Inf * Nega Inf" + (Double.POSITIVE_INFINITY * Double.NEGATIVE_INFINITY));
        System.out.println("Posi Inf / Posi Inf" + (Double.POSITIVE_INFINITY / Double.POSITIVE_INFINITY));
        System.out.println("Posi Inf / Nega Inf" + (Double.POSITIVE_INFINITY / Double.NEGATIVE_INFINITY));
    }

}
