package ch01.ex10;

public class ImprovedFibonacci {
    static final int MAX_INDEX = 9;

    public static void main(String[] args) {
        NumData[] numArray = new NumData[MAX_INDEX];

        numArray[0] = numArray[1] = new NumData(1, false);
        for (int i = 2; i < MAX_INDEX; i++) {
            int value = numArray[i-2].value + numArray[i-1].value;
            numArray[i] = new NumData(value, value % 2 == 0);
        }

        printArray(numArray);
    }

    private static void printArray (NumData[] numArray) {
        if (numArray == null || numArray.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numArray.length; i++) {
            System.out.println(numArray[i].value + " : " + numArray[i].isEven);
        }
    }
}

class NumData {

    public int value;
    public boolean isEven;

    NumData (int value, boolean isEven) {
        this.value = value;
        this.isEven = isEven;
    }
}
