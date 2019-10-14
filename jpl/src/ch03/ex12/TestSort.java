package ch03.ex12;

public class TestSort {

    static double[] testData1 = {
            0.3, 1.3e-2, 7.9, 3.17
    };

    static String[] testData2 = {
            "sakana", "tamago", "meron", "waffle", "chocolate",
    };


    public static void main(String[] args) {
        SortDouble bsort = new SimpleSortDouble();
        SortHarness hsort = new SimpleSortHarness();
        SortMetrics metrics1 = bsort.sort(testData1);
        System.out.println("Metrics1: " + metrics1);
        SortMetrics metrics2 = hsort.sort(testData2);
        System.out.println("Metrics2: " + metrics2);
        for (int i = 0; i < testData1.length; i++) {
            System.out.println("\t" + testData1[i]);
        }
        for (int i = 0; i < testData2.length; i++) {
            System.out.println("\t" + testData2[i]);
        }
    }
}
