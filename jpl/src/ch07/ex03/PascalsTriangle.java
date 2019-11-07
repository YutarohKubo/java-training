package ch07.ex03;

public class PascalsTriangle {

    int[][] pascalsTriangle;

    public void makeTriangle (int rowLength) {
        pascalsTriangle = new int[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            pascalsTriangle[i] = new int[i+1];
            for (int j = 0; j <= i; j++) {
                if (i == 0) {
                    pascalsTriangle[i][j] = 1;
                    continue;
                }
                if (j == 0) {
                    pascalsTriangle[i][j] = pascalsTriangle[i-1][j];
                    continue;
                }
                if (j == i) {
                    pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1];
                    continue;
                }
                pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j];
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
        pt.makeTriangle(120);
        pt.printTriangle();
    }

}
