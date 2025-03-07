package cz.braza.leetcode;

/*
Daily 5.2.2025
SHOW: easy, managing states, do not do anything unnecessary

1790. Check if One String Swap Can Make Strings Equal
Easy
Topics
Companies
Hint
You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.



Example 1:

Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
Example 2:

Input: s1 = "attack", s2 = "defend"
Output: false
Explanation: It is impossible to make them equal with one string swap.
Example 3:

Input: s1 = "kelb", s2 = "kelb"
Output: true
Explanation: The two strings are already equal, so no string swap operation is required.


Constraints:

1 <= s1.length, s2.length <= 100
s1.length == s2.length
s1 and s2 consist of only lowercase English letters.
 */
public class L1790IsOneStringSwapEnough {
    /*
    One pass - if we find a position where strings differ, we mark it. If we find
    another one, check if swap solves it (return false otherwise) and mark that swap was done
    In the end, if we get there, there must not be an "open swap" waiting to be resolved.
    Runs 0ms, beats 100%, 132 testcases.
     */
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int swapPos = -1; // substitutes also the need for `boolean swapNeeded`
        boolean swapDone = false;
        for (int idx = 0; idx < s1.length(); idx++)
            if (s1.charAt(idx) != s2.charAt(idx))
                if (swapDone) return false;
                else if (swapPos >= 0)
                    if (s1.charAt(idx) != s2.charAt(swapPos) || s2.charAt(idx) != s1.charAt(swapPos)) return false;
                    else swapDone = true;
                else swapPos = idx;
        return swapPos == -1 || swapDone;
    }
}
