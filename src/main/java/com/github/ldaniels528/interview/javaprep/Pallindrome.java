package com.github.ldaniels528.interview.javaprep;

public class Pallindrome {

    public static void main(String[] args) {
        final String word = "rotor";
        System.out.println(String.format("is '%s' a pallindrome? %s", word, isPallindrome2(word)));
    }

    private static boolean isPallindrome1(final String s) {
        final int length = s.length();
        final int half = length / 2 + length % 2;
        return s.substring(0, half).equals(new StringBuilder(s.substring(length - half, length)).reverse().toString());
    }

    private static boolean isPallindrome2(final String s) {
        final int length = s.length();
        final int half = length / 2 + length % 2;
        final char[] ca = s.toCharArray();
        for (int i = 0; i <= half; i++) {
            if (ca[i] != ca[(ca.length - 1) - i]) return false;
        }
        return true;
    }

}
