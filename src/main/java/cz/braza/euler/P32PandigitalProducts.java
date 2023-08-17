package cz.braza.euler;

import java.util.HashSet;
import java.util.Set;

/**
 * See https://www.xarg.org/puzzle/project-euler/problem-32/
 * Observation: only 1digit x 4digit or 2digit x 3 digit can lead to 9digit total
 *
 */
public class P32PandigitalProducts {
    public static boolean isPandigital(String val) {
        if (val.length() != 9) return false; // cannot be 1-9 pandigital
        boolean[] result = new boolean[10];
        result[0] = true; // we want to escape as soon as we can, and zero cannot be there...
        for (int idx = 0; idx < 9; idx++) { // we already know the string is exactly 9 characters long
            int digit = Integer.parseInt("" + val.charAt(idx));
            if (result[digit])
                return false;
            else
                result[digit] = true;
        }
        return true;
    }

    public static void sumRange(int low1, int high1, int low2, int high2, Set results) {
        for (int m = low1; m <= high1; m++)
            for (int n = low2; n <= high2; n++)
                if (isPandigital(String.valueOf(m) + String.valueOf(n) + String.valueOf(m*n))) {
                    System.out.println(m + " x " + n + " = " + (m * n));
                    results.add(m * n);
                }
    }
    public static void main(String[] args) {
        Set<Integer> products = new HashSet<>();
        sumRange(1, 9, 1234, 9876, products);
        sumRange(12, 98, 123, 987, products);
        long totalSum = 0;
        for (int val : products)
            totalSum += val;
        System.out.println("Total sum of pandigital products is " + totalSum);
    }
}
