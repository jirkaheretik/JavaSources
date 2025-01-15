package cz.braza.leetcode;

/*
1930. Unique Length-3 Palindromic Subsequences
Medium
Topics
Companies
Hint
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")


Constraints:

3 <= s.length <= 10^5
s consists of only lowercase English letters.
 */
public class L1930Length3Palindromes {
    /*
    At first I considered treating longer strings differently than short ones,
    but later tried this solution and it worked well in general.
    Runs 35ms, beats 90.1%, 70 testcases
     */
    public int countPalindromicSubsequence(String s) {
        // try every possibility (as there are only 676 of them):
        int total = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int left = s.indexOf(c);
            int right = s.lastIndexOf(c);
            if (left == right) continue;
            for (char in = 'a'; in <= 'z'; in++)
                // this is crucial to go right to left in order to have one value and cope with -1 as well
                if (s.lastIndexOf(in, right - 1) > left)
                    total++;
        }
        return total;
    }
}
