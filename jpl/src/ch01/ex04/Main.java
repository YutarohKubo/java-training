package ch01.ex04;

public class Main {
    public static void main(String[] args) {
        System.out.println("square table");
        int number = 1;
        while (number < 10000) {
            System.out.println("square of " + number + " is " + (int) Math.pow(number, 2));
            number++;
        }
    }
}
