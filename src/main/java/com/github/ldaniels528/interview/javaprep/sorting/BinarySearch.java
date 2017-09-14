package com.github.ldaniels528.interview.javaprep.sorting;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {

    public static void main(String[] args) {
        final Random random = new Random();
        final int[] numbers = new int[100];
        for (int n = 0; n < numbers.length; n++) numbers[n] = random.nextInt(1000);
        Arrays.sort(numbers);

        final int number = numbers[random.nextInt(numbers.length)];

        System.out.printf("Looking for value %d...\n", number);
        final int index = binarySearch(numbers, number);
        if (index == -1) {
            System.out.printf("Value %d was not found\n", number);
            System.out.println();
            Arrays.stream(numbers).forEach(n -> System.out.printf("number: %d\n", n));

        }
        else System.out.printf("Value %d was found at %d\n", number, index);
    }

    private static int binarySearch(final int[] array, final int number) {
        int p0 = 0;
        int p1 = array.length - 1;

        while (p0 < p1 && array[p0] != number && array[p1] != number) {
            final int mp = (p0 + p1) / 2;
            if (number >= array[mp]) p0 = mp;
            if (number <= array[mp]) p1 = mp;
        }

        if (array[p0] == number) return p0;
        else if (array[p1] == number) return p1;
        else return -1;
    }

}
