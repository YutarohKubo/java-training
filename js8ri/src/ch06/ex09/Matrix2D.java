package ch06.ex09;

import java.util.Arrays;

public class Matrix2D {

    private double[][] matrix;

    public Matrix2D(double[] vector){
        this.matrix = new double[vector.length][1];
        for(int i=0; i<vector.length; i++){
            this.matrix[i][0] = vector[i];
        }
    }

    public Matrix2D(double[][] vector){
        this.matrix = new double[vector.length][vector[0].length];
        for(int r=0; r<vector.length; r++){
            for(int c=0; c<vector[r].length; c++){
                this.matrix[r][c] = vector[r][c];
            }
        }
    }

    //行数を取得
    public int getRow(){
        return this.matrix.length;
    }

    //列数を取得
    public int getCol() {
        return this.matrix[0].length;
    }

    private double getValue(int row, int col){
        return this.matrix[row][col];
    }

    //転置行列を取得
    public Matrix2D T(){
        double[][] t = new double[this.getCol()][this.getRow()];
        Arrays.parallelSetAll(t, i -> {
            Arrays.parallelSetAll(t[i], j -> this.matrix[j][i]);
            return t[i];
        });
        /*for(int r=0; r<t.length; r++){
            for(int c=0; c<t[r].length; c++){
                t[r][c] = this.matrix[c][r];
            }
        }*/
        return new Matrix2D(t);
    }

    //行列の積
    public static Matrix2D mult(Matrix2D a, Matrix2D b){
        if(a.getCol() == b.getRow()){
            double[][] d = new double[a.getRow()][b.getCol()];
            for(int r=0; r<a.getRow(); r++){
                for(int c=0; c<b.getCol(); c++){
                    double sum = 0;
                    for(int i=0; i<b.getRow(); i++){
                        sum += a.getValue(r, i) * b.getValue(i, c);
                    }
                    d[r][c] = sum;
                }
            }
            return new Matrix2D(d);
        }
        else{
            return null;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int r=0; r<getRow(); r++){
            sb.append("|");
            for(int c=0; c<getCol(); c++){
                if(matrix[r][c] < 0)sb.append(String.format("%.5f ", matrix[r][c]));
                else sb.append(String.format(" %.5f ", matrix[r][c]));
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
}
