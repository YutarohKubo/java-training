package ch01.ex12;

public class ImprovedFibonacci {
    static final int MAX_INDEX = 9;
    public static void main(String[] args) {
        String[] numArray = new String[MAX_INDEX];

        numArray[0] = numArray[1] = "1";
        for (int i = 2; i < MAX_INDEX; i++) {
            numArray[i] = Integer.toString(Integer.parseInt(numArray[i-2]) + Integer.parseInt(numArray[i-1]));
        }

        for (int i = 0; i < MAX_INDEX; i++) {
            System.out.println(numArray[i]);
        }
    }
}

