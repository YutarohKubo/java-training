package ch13.ex01;

public class Main {

    public static void main(String[] args) {
        System.out.println(countAppearanceChar("Hello World", 'o'));
    }

    public static int countAppearanceChar(String str, char testChar) {
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (ch == testChar) {
                counter++;
            }
        }
        return counter;
    }
}
