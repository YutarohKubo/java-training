package ch04.ex02;

public class TestSort {

    static String[] testData1 = {
            "sakana", "tamago", "meron", "waffle", "chocolate",
    };


    public static void main(String[] args) {
        SortHarness hsort = new SimpleSortHarness();
        SortMetrics metrics1 = hsort.sort(testData1);
        System.out.println("Metrics1: " + metrics1);
        for (int i = 0; i < testData1.length; i++) {
            System.out.println("\t" + testData1[i]);
        }
    }
}
