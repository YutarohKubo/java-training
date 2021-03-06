package ch03.ex11;

public class TestSort {

    static double[] testData1 = {
            0.3, 1.3e-2, 7.9, 3.17
    };

    static double[] testData2 = {
            0.5, 0.3, 0.7, 1.9, 0.02
    };


    public static void main(String[] args) {
        SortDouble bsort = new SimpleSortDouble();
        SortDouble.SortMetrics metrics1 = bsort.sort(testData1);
        System.out.println("Metrics: " + metrics1);
        SortDouble.SortMetrics metrics2 = bsort.sort(testData2);
        System.out.println("Metrics: " + metrics2);
        for (int i = 0; i < testData1.length; i++) {
            System.out.println("\t" + testData1[i]);
        }
        for (int i = 0; i < testData2.length; i++) {
            System.out.println("\t" + testData2[i]);
        }
    }
}
