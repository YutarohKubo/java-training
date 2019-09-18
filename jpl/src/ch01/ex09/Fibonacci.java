package ch01.ex09;

public class Fibonacci {
    static final int MAX_INDEX = 9;

    public static void main(String[] args) {
        int[] numArray = new int[MAX_INDEX];

        numArray[0] = numArray[1] = 1;
        for (int i = 2; i < MAX_INDEX; i++) {
            numArray[i] = numArray[i-2] + numArray[i-1];
        }

        printArray(numArray);
    }

    private static void printArray (int[] numArray) {
        if (numArray == null || numArray.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numArray.length; i++) {
            System.out.println(numArray[i]);
        }
    }
}
