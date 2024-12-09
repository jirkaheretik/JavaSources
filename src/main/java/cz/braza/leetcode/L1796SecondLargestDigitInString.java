package cz.braza.leetcode;

/*
1796. Second Largest Digit in a String
Easy
Topics
Companies
Hint
Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.

An alphanumeric string is a string consisting of lowercase English letters and digits.



Example 1:

Input: s = "dfa12321afd"
Output: 2
Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
Example 2:

Input: s = "abc1111"
Output: -1
Explanation: The digits that appear in s are [1]. There is no second largest digit.


Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters and digits.
 */
public class L1796SecondLargestDigitInString {
    // split to chars, update highest and secondhighest
    // runs 1ms, beats 99.15%
    public int secondHighest(String s) {
        int highest = -1;
        int secondHighest = -1;
        for (char c: s.toCharArray())
            if (c >= '0' && c <= '9') {
                int digit = c - '0';
                if (digit != highest && digit != secondHighest)
                    if (digit > highest) {
                        secondHighest = highest;
                        highest = digit;
                    } else if (digit > secondHighest)
                        secondHighest = digit;
            }
        return secondHighest;
    }

}
