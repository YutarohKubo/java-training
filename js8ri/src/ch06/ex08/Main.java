package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

public class Main {

    /**
     * parallelSortがsortの処理時間を10mSec上回るのは、long配列でおおよそ132000の大きさ
     */
    public static void main(String[] args) {
        int N = 10;
        long sumArraySize = 0;
        for (int k = 0; k < N; k++) {
            for (int i = 10000; ; i += 1000) {
                System.out.println("array size = " + i);
                long[] arr1 = new long[i];
                setValueToArray(arr1, i);
                long startTime = System.currentTimeMillis();
                Arrays.sort(arr1);
                long normalSortTime = System.currentTimeMillis() - startTime;

                long[] arr2 = new long[i];
                setValueToArray(arr2, i);
                startTime = System.currentTimeMillis();
                Arrays.parallelSort(arr2);
                long parallelSortTime = System.currentTimeMillis() - startTime;

                if (normalSortTime - parallelSortTime > 10) {
                    System.out.println("Parallel sort is better than normal sort , array size = " + i);
                    System.out.println("normal sort time : " + normalSortTime);
                    System.out.println("parallel sort time : " + parallelSortTime);
                    sumArraySize += i;
                    break;
                }
            }
        }

        System.out.println("mean of array size = " + ((double) sumArraySize / N));
    }

    static void setValueToArray(long[] arr, int size) {
        Random random = new Random();
        Arrays.parallelSetAll(arr, num -> (long) random.nextInt(size));
    }

    private static void printArray(long[] arr) {
        System.out.print("[");
        for (long l : arr) {
            System.out.print(l + ", ");
        }
        System.out.println("]");
    }

}
