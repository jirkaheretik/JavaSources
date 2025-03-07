package cz.braza.leetcode;

import java.util.Arrays;

/*
Daily 18.2.2025

2375. Construct Smallest Number From DI String
Medium
Topics
Companies
Hint
You are given a 0-indexed string pattern of length n consisting of the characters 'I' meaning increasing and 'D' meaning decreasing.

A 0-indexed string num of length n + 1 is created using the following conditions:

num consists of the digits '1' to '9', where each digit is used at most once.
If pattern[i] == 'I', then num[i] < num[i + 1].
If pattern[i] == 'D', then num[i] > num[i + 1].
Return the lexicographically smallest possible string num that meets the conditions.



Example 1:

Input: pattern = "IIIDIDDD"
Output: "123549876"
Explanation:
At indices 0, 1, 2, and 4 we must have that num[i] < num[i+1].
At indices 3, 5, 6, and 7 we must have that num[i] > num[i+1].
Some possible values of num are "245639871", "135749862", and "123849765".
It can be proven that "123549876" is the smallest possible num that meets the conditions.
Note that "123414321" is not possible because the digit '1' is used more than once.
Example 2:

Input: pattern = "DDD"
Output: "4321"
Explanation:
Some possible values of num are "9876", "7321", and "8742".
It can be proven that "4321" is the smallest possible num that meets the conditions.


Constraints:

1 <= pattern.length <= 8
pattern consists of only the letters 'I' and 'D'.
 */
public class L2357ConstructSmallestNumFromDIString {
    private static int lastDigit = 1;
    private static int highestDigit = 0;

    private static void reset() {
        lastDigit = 1;
        highestDigit = 0;
    }

    /*
    Somewhat messy solution - first we count D block sizes through the string (from right to left),
    we add "I" at the beginning of pattern so that we have same length of pattern and result.
    If we encounter I, we increment highest digits, and if Ds follow, we leave space for them too.
    If D is encountered, just put lastDigit-1 and update it.

    Runs in O(n), 1ms, beats 75.3%, 104 testcases
     */
    public static String smallestNumber(String pattern) {
        reset();
        // first count the D block sizes, there can be at most 4 of them
        int dIdx = 0;
        int[] dSizes = new int[5];
        // go from right to left
        int dSize = 0;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            if (pattern.charAt(i) == 'D') dSize++;
            else if (dSize > 0) {
                dSizes[dIdx++] = dSize;
                dSize = 0;
            }
        }
        if (dSize > 0)
            dSizes[dIdx++] = dSize;
        pattern = "I" + pattern; // prepend 'I' to make it easier to handle
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'I') {
                int plus = 1;
                if (i < pattern.length() - 1 && dIdx > 0 && pattern.charAt(i + 1) == 'D') plus += dSizes[--dIdx];
                // pick first + next Ds:
                int num = highestDigit + plus;
                highestDigit = num;
                lastDigit = num;
                sb.append(num);
            } else
                sb.append(--lastDigit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] testPatterns = {"I", "II", "III", "IIIII", "DD", "IDIDID", "IIID", "IIIDIDDD", "DDD"};
        for (String pattern : testPatterns)
            System.out.printf("Pattern: %s, result: %s%n", pattern, smallestNumber(pattern));
    }
}
