package com.github.ldaniels528.interview.javaprep;

public class MoveZeroesToTheEnd {
    private static int[] array = {10, 5, 0, 6, 0, 1, 2, 0, 0, 0, 9, 7, 11, 17, 0};

    public static void main(String[] args) {
        System.out.println("BEFORE: " + toString(array));
        moveZeroesToEndA(array);
        System.out.println("AFTER: " + toString(array));
    }

    private static void moveZeroesToEndA(final int[] array) {
        int p0 = 0;
        int p1 = array.length - 1;
        while (p1 >= 0 && array[p1] == 0) p1--;
        while (p0 < p1) {
            if (array[p0] == 0) {
                final int temp = array[p0];
                System.arraycopy(array, p0 + 1, array, p0, p1 - p0 + 1);
                array[p1--] = temp;
            } else p0++;
        }
    }

    private static void moveZeroesToEndB(final int[] array) {
        int p1 = array.length - 1;
        while (p1 >= 0 && array[p1] == 0) p1--;
        for (int p0 = 0; p0 < p1; p0++) {
            if (array[p0] == 0) swap(array, p0, p1--);
        }
    }

    private static void swap(final int[] array, final int i, final int j) {
        final int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static String toString(final int[] array) {
        final StringBuilder sb = new StringBuilder(2 + array.length * 4);
        sb.append('[');
        for (int v : array) sb.append(String.format("%d, ", v));
        sb.append(']');
        return sb.toString();
    }

}
