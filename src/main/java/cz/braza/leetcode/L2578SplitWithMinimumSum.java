package cz.braza.leetcode;

import java.util.Arrays;

/*
2578. Split With Minimum Sum
Easy
Topics
Companies
Hint
Given a positive integer num, split it into two non-negative integers num1 and num2 such that:

The concatenation of num1 and num2 is a permutation of num.
In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of occurrences of that digit in num.
num1 and num2 can contain leading zeros.
Return the minimum possible sum of num1 and num2.

Notes:

It is guaranteed that num does not contain any leading zeros.
The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.


Example 1:

Input: num = 4325
Output: 59
Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a sum of 59. We can prove that 59 is indeed the minimal possible sum.
Example 2:

Input: num = 687
Output: 75
Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would give an optimal sum of 75.


Constraints:

10 <= num <= 10^9
 */
public class L2578SplitWithMinimumSum {
    /*
    Minimum split = lowest digits first, in both numbers.
    Lets do it this way:
    1) create a list (array) of digits
    2) sort it
    3) then go from left to right and pick another digit for values A and B
    4) return A+B
    Do it without any dynamic structure and without Strings :-P
    Runs 0ms, beats 100%, 140 testcases (majority of solutions runs 1ms)
     */
    public int splitNum(int num) {
        // create list of digits:
        int digitCount = (int)(Math.log10(num)) + 1;
        int[] digits = new int[digitCount];
        int pos = 0;
        while (num > 0) {
            digits[pos++] = num % 10;
            num /= 10;
        }
        Arrays.sort(digits);
        int a = 0;
        int b = 0;
        boolean doA = true;
        for (int val: digits) {
            if (doA) {
                a *= 10;
                a += val;
            } else {
                b *= 10;
                b += val;
            }
            doA = !doA;
        }
        return a + b;
    }

    /*
    How to do it better? And why, of ot already runs just 0ms?
    Well, to simplify the code, at least. Too many curly brackets, as
    we are doing 2+ commands in each block.
    First part (up to sorting) is ok, but then we do not need to construct
    numbers A and B at all. We only need to group digits in pairs that go
    to the same rank (10^x). If odd number of digits, lowest digits has
    highest rank, after that we always take two and add.
     */
    public int splitNumOpt(int num) {
        // create list of digits:
        int digitCount = (int)(Math.log10(num)) + 1;
        int[] digits = new int[digitCount];
        int pos = 0;
        while (num > 0) {
            digits[pos++] = num % 10;
            num /= 10;
        }
        // sort digits, lowest first
        Arrays.sort(digits);
        int result = 0;
        int index = 0;
        // for odd length, pick first digit separately
        if (digits.length % 2 == 1)
            result += digits[index++];
        // now we always have a couple of digits to go, that will be in the same rank
        while (index < digits.length) {
            result *= 10;
            result += digits[index++] + digits[index++];
        }
        return result;
    }
}
