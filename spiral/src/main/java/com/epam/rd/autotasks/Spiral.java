package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] spiral = new int[rows][columns];

        if (rows == 0 || columns == 0) {
            return spiral;
        }

        int rowStart = 0;
        int colStart = 0;
        int rowEnd = rows - 1;
        int colEnd = columns - 1;
        int number = 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                spiral[rowStart][i] = number++;
            }

            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                spiral[i][colEnd] = number++;
            }

            colEnd--;

            for (int i = colEnd; i >= colStart; i--) {
                if (rowStart <= rowEnd) {
                    spiral[rowEnd][i] = number++;
                }
            }

            rowEnd--;

            for (int i = rowEnd; i >= rowStart; i--) {
                if (colStart <= colEnd) {
                    spiral[i][colStart] = number++;
                }
            }

            colStart++;
        }
        return spiral;
    }
}
