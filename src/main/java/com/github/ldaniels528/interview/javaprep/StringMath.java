package com.github.ldaniels528.interview.javaprep;

import java.util.ArrayList;
import java.util.List;

public class StringMath {

    public static void main(String[] args) {
        final String number0 = "1482";
        final String number1 = "12";

        System.out.println(String.format("%s * %s = %s", number0, number1, multiply(number0, number1)));
        System.out.println(String.format("%s + %s = %s", number0, number1, add(number0, number1)));
    }

    private static String multiply(final String numberL, final String numberR) {
        final char[] caL = numberL.toCharArray();
        final char[] caR = numberR.toCharArray();
        final int limit = Math.max(caL.length, caR.length);
        final List<String> numbers = new ArrayList<>(caR.length);

        for (int i = caR.length - 1; i >= 0; i--) {
            final int digitR = caR[i] - '0';
            int carry = 0;

            final StringBuilder sb = new StringBuilder(limit + 1);
            for (int n = i + 1; n < caR.length; n++) sb.append('0');

            for (int j = caL.length - 1; j >= 0; j--) {
                final int digitL = caL[j] - '0';
                final int product = digitL * digitR + carry;
                final int digit = product % 10;
                carry = product / 10;
                sb.append(digit);
            }
            numbers.add(sb.reverse().toString());
        }

        // generate the sum of the products
        String outcome = numbers.get(0);
        for (int n = 1; n < numbers.size(); n++) {
            outcome = add(outcome, numbers.get(n));
        }
        return outcome;
    }

    private static String add(final String numberL, final String numberR) {
        final char[] caL = numberL.toCharArray();
        final char[] caR = numberR.toCharArray();
        final int limit = Math.max(caL.length, caR.length);

        final StringBuilder sb = new StringBuilder(limit + 1);
        int carry = 0;
        for (int n = 0; n < limit; n++) {
            final int digitL = caL.length - n > 0 ? caL[(caL.length - 1) - n] - '0' : 0;
            final int digitR = caR.length - n > 0 ? caR[(caR.length - 1) - n] - '0' : 0;
            final int sum = digitL + digitR + carry;
            final int digit = sum % 10;
            carry = sum / 10;
            sb.append(digit);
        }
        return sb.reverse().toString();
    }

}
