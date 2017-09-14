package com.github.ldaniels528.interview.javaprep;

/**
 * Given two integers, add them without using any arithmetic operators
 */
public class NonArithmeticAddition {

    public static void main(String[] args) {
        final int num0 = 5; // 0101
        final int num1 = 7; // 0110
        System.out.printf("%d + %d = %d\n", num0, num1, add(num0, num1));
    }

    // x = 0101
    // y = 0110

    // carry = (0101 & 0110) << 1 => 0100 << 1 => 1000
    // x = 0101 ^ 0110 => 0011
    // y = 1000

    // carry = (0011 & 1000) << 1 => 0000
    // x = 0011 ^ 1000 => 1011
    // y = 0000

    private static int add(final int n0, final int n1) {
        int x = n0;
        int y = n1;
        while (y > 0) {
            final int carry = (x & y) << 1;
            x ^= y;
            y = carry;
        }
        return x;
    }

}
