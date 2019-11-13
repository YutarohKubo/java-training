package ch10.ex05;

public class Main {

    public static void main(String[] args) {
        System.out.println(showBetweenCharAndChar((char) 48, (char) 57));
    }

    private static String showBetweenCharAndChar (char ch1, char ch2) {
        String str = "";
        for (char ch = ch1; ch <= ch2; ch++) {
            str += ch;
        }
        return str;
    }

}
