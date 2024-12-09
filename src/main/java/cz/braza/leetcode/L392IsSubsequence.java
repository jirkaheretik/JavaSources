package cz.braza.leetcode;

/*
Got linked here from L2825

392. Is Subsequence
Easy
Topics
Companies
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 10^4
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?

 */
public class L392IsSubsequence {
    /*
    Using two pointer solution from L2825, only simplified (exact ==)
    Runs 1ms, beats 93.5%
     */
    public boolean isSubsequence(String s, String t) {
        int idx2 = 0, length1 = t.length(), length2 = s.length();
        for (int idx1 = 0; idx1 < length1 && idx2 < length2; idx1++)
            if (t.charAt(idx1) == s.charAt(idx2))
                idx2++;
        return idx2 == length2;
    }
}
