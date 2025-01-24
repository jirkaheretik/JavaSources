package cz.braza.leetcode;

/*
7. Reverse Integer
Medium
Topics
Companies
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21


Constraints:

-2^31 <= x <= 2^31 - 1*/
public class L7ReverseInteger {
    /*
    Make array of digits, revert, check for exceptions (too high values).

    Runs 1ms, beats 84.5%, 1045 testcases
    */
    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x *= -1;
        }
        if (x == 0) return 0;
        int[] digits = new int[(int)Math.log10(x) + 1];
        int idx = digits.length - 1;
        while (x > 0) {
            digits[idx--] = x % 10;
            x /= 10;
        }
        long result = 0;
        for (int digit: digits) {
            result *= 10;
            result += digit;
        }
        if (isNegative) result *= -1;
        return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? 0 : (int) result;
    }

    /*
    No intermediate array, thus O(1) space complexity, but still no significant time boost
    Runs 1ms, beats 84.5%
    */
    public int reverseNoArray(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x *= -1;
        }
        if (x == 0) return 0;
        long result = 0;
        while (x > 0) {
            result *= 10;
            result += x % 10;
            x /= 10;
        }
        if (isNegative) result *= -1;
        return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? 0 : (int) result;
    }
}
