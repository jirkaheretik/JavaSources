package cz.braza.leetcode;

/*
5. Longest Palindromic Substring
Medium
Topics
Companies
Hint
Given a string s, return the longest
palindromic

substring
 in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class L5LongestPalindromeSubstr {
    /*
    For every position we test it as a middle of a palindrome, going as far as we can get.
    Need to check twice, if there are successive same chars, for strings like abba vs bbb

    Runs 17ms, beats 68.2%, 142 testcases
     */
    public String longestPalindrome(String s) {
        int minLeft = 0;
        int maxRight = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            int left = getLeft(s, idx, idx);
            if ((idx - left) * 2 + 1 > maxRight - minLeft + 1) {
                minLeft = left;
                maxRight = 2 * idx - left;
            }
            if (idx < s.length() - 1 && s.charAt(idx) == s.charAt(idx + 1)) {
                left = getLeft(s, idx, idx + 1);
                if ((idx - left) * 2 + 2 > maxRight - minLeft + 1) {
                    minLeft = left;
                    maxRight = 2 * idx - left + 1;
                }
            }
        }
        return s.substring(minLeft, maxRight + 1);
    }

    public static int getLeft(String s, int left, int right) {
        while (left > 0 && right < s.length() - 1) {
            if (s.charAt(left - 1) != s.charAt(right + 1)) break;
            left--;
            right++;
        }
        return left;
    }
}
