package com.github.ldaniels528.interview.javaprep;

/**
 * Rotate Matrix
 */
public class RotateMatrix {
    private static int[][] matrix = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {8, 9, 0, 1, 2, 3, 4, 5, 6, 1},
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 8},
            {2, 3, 4, 5, 6, 7, 8, 9, 0, 7},
            {3, 4, 5, 6, 7, 8, 9, 0, 1, 6},
            {4, 5, 6, 7, 8, 9, 0, 1, 2, 5},
            {5, 6, 7, 8, 9, 0, 1, 2, 3, 4},
            {6, 7, 8, 9, 0, 1, 2, 3, 4, 3},
            {7, 8, 9, 0, 1, 2, 3, 4, 5, 2},
            {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
    };

    public static void main(String[] args) {
        rotate90();
        show();
    }

    private static void rotate90() {
        for (int x = matrix.length - 1; x >= 0; x--) {
            for(int y = 0; y < matrix.length; y++) {
                final int z = matrix.length - x - 1;
                final int temp = matrix[x][y];
                matrix[x][y] = matrix[y][z];
                matrix[y][z] = temp;
            }
        }
    }

    private static void show() {
        for (int y = 0; y < matrix.length; y++) {
            final StringBuilder sb = new StringBuilder(matrix.length * 3);
            for (int x = 0; x < matrix.length; x++) {
                sb.append(String.format(" %d ", matrix[x][y]));
            }
            System.out.println(sb.toString());
        }
    }

}
