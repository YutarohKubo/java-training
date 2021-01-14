package ch06.ex09;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        double[][] matrix = new double[2][2];
        Arrays.parallelSetAll(matrix, i -> {
            Arrays.parallelSetAll(matrix[i], j -> i == 1 && j == 1 ? 0 : 1);
            return matrix[i];
        });
        Matrix2D resultFiboMatrix = multiN(new Matrix2D(matrix), 5);
        System.out.println(resultFiboMatrix);
    }

    static Matrix2D multiN(Matrix2D matrix, int n) {
        Matrix2D resultMatrix = matrix;
        for (int i = 0; i < n; i++) {
            resultMatrix = Matrix2D.mult(resultMatrix, matrix);
        }
        return resultMatrix;
    }

}
