package cz.braza.leetcode;

/*
Daily 11.4.2025 - https://leetcode.com/problems/count-symmetric-integers/
2843. Count Symmetric Integers
Solved
Easy
Topics
Companies
Hint
You are given two positive integers low and high.

An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the sum of the last n digits of x. Numbers with an odd number of digits are never symmetric.

Return the number of symmetric integers in the range [low, high].



Example 1:

Input: low = 1, high = 100
Output: 9
Explanation: There are 9 symmetric integers between 1 and 100: 11, 22, 33, 44, 55, 66, 77, 88, and 99.
Example 2:

Input: low = 1200, high = 1230
Output: 4
Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.


Constraints:

1 <= low <= high <= 10^4
 */
public class L2843SymmetricIntegers {
    /*
    Numeric method - just try 11-99, then 1001-9999, use division and modulo
    Runs 11ms, beats 96.7%, 1967 testcases
     */
    public int countSymmetricIntegers(int low, int high) {
        if (low < 11) low = 11;
        int count = 0;
        for (int i = low; i <= high; i++)
            if (i == 100) i = 1000;
            else if (isSymmetric(i)) count++;
        return count;
    }

    private static boolean isSymmetric(int num) {
        if (num < 100) return num % 10 == num / 10;
        int left = num / 100;
        int right = num % 100;
        int leftSum = left % 10 + left / 10;
        int rightSum = right % 10 + right / 10;
        return leftSum == rightSum;
    }

    /*
    Inlined the isSymmetric() function:
    Runs 10ms, beats 98.6%
     */
    public int countSymmetricIntegersInlined(int low, int high) {
        if (low < 11) low = 11;
        int count = 0;
        for (int i = low; i <= high; i++)
            if (i == 100) i = 1000;
            else if (i < 100) { if (i % 10 == i / 10) count++; }
            else {
                int left = i / 100;
                int right = i % 100;
                if (left % 10 + left / 10 == right % 10 + right / 10) count++;
            }
        return count;
    }
}
