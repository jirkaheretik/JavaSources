package cz.braza.leetcode;

/*
Daily 4.3.2025 - https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/

1780. Check if Number is a Sum of Powers of Three
Medium
Topics
Companies
Hint
Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.



Example 1:

Input: n = 12
Output: true
Explanation: 12 = 31 + 32
Example 2:

Input: n = 91
Output: true
Explanation: 91 = 30 + 32 + 34
Example 3:

Input: n = 21
Output: false


Constraints:

1 <= n <= 10^7
 */
public class L1780SumOfPowersOf3 {
    /*
    The only time we return false is if we need TWO of a given power of 3, that is,
    if in ternary representation of a number is 2 somewhere.
    So we keep checking modulo 3, early bailing out if two is found, otherwise
    return true in the end.

    Runs 0ms, beats 100%, 129 testcases
     */
    public boolean checkPowersOfThree(int n) {
        while (n > 0)
            if (n % 3 == 2) return false;
            else n /= 3;
        return true;
    }

    /*
    Using java function - must be "slower", since we:
    a) are calling other functions
    b) work with strings
    c) always need to convert whole number, cannot end early
    Still runs 0ms, beats 100%
     */
    public boolean checkPowersOfThreeJava(int n) {
        return !Integer.toString(n, 3).contains("2");
    }

    public static void main(String[] args) {
        int[] nums = {12, 27, 21, 91};
        for (int num: nums)
            System.out.println(Integer.toString(num, 3));
    }
}
