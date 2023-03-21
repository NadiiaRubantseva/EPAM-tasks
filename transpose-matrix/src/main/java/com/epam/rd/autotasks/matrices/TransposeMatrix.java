package com.epam.rd.autotasks.matrices;

import java.util.Arrays;

public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int[][] transpose;
        int R = matrix.length;     // the number of rows in matrix
        int C = matrix[0].length;  // the number of columns in matrix
        transpose = new int[C][R];
        for ( int i = 0; i < C; i++) { // goes through ROWS of the transpose
            for ( int j = 0; j < R; j++ ) { // goes through COLUMNS of the transpose
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        int[][] matrix = {
                {1, 2},
                {7, -13}};

        int[][] result = transpose(matrix);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }

}
