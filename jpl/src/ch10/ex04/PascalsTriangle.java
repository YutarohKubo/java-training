package ch10.ex04;

public class PascalsTriangle {

    private static final int ROW_LENGTH = 12;
    int[][] pascalsTriangle;

    public void makeTriangle (int rowLength) {
        pascalsTriangle = new int[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            pascalsTriangle[i] = new int[i+1];
            int j = 0;
            while (j <= i) {
                if (i == 0) {
                    pascalsTriangle[i][j] = 1;
                    j++;
                    continue;
                }
                if (j == 0) {
                    pascalsTriangle[i][j] = pascalsTriangle[i-1][j];
                    j++;
                    continue;
                }
                if (j == i) {
                    pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1];
                    j++;
                    continue;
                }
                pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j];
                j++;
            }
        }
    }

    public void printTriangle () {
        for (int i = 0; i < pascalsTriangle.length; i++) {
            for (int j = 0; j <=i; j++) {
                System.out.print(String.format("%5d ", pascalsTriangle[i][j]));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle();
        pt.makeTriangle(ROW_LENGTH);
        pt.printTriangle();
    }

}
