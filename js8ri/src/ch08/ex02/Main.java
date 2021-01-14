package ch08.ex02;

public class Main {

    public static void main(String[] args) {
        System.out.println(Math.negateExact(100));
        System.out.println(Math.negateExact(Integer.MIN_VALUE)); // java.lang.ArithmeticExceptionが発生する
    }

}
