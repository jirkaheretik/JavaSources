package cz.braza.leetcode;

/*
Q1. Substring Matching Pattern
Easy
3 pt.
You are given a string s and a pattern string p, where p contains exactly one '*' character.

The '*' in p can be replaced with any sequence of zero or more characters.

Return true if p can be made a substring of s, and false otherwise.

A substring is a contiguous non-empty sequence of characters within a string.



Example 1:

Input: s = "leetcode", p = "ee*e"

Output: true

Explanation:

By replacing the '*' with "tcod", the substring "eetcode" matches the pattern.

Example 2:

Input: s = "car", p = "c*v"

Output: false

Explanation:

There is no substring matching the pattern.

Example 3:

Input: s = "luck", p = "u*"

Output: true

Explanation:

The substrings "u", "uc", and "uck" match the pattern.



Constraints:

1 <= s.length <= 50
1 <= p.length <= 50
s contains only lowercase English letters.
p contains only lowercase English letters and exactly one '*'
 */
public class LBCQ1SubstringMatchingPattern {
    public static boolean hasMatch(String s, String p) {
        if ("*".equals(p)) return true;
        String[] patterns = p.split("\\*");
        if (patterns.length == 1) return s.indexOf(patterns[0]) >= 0;
        int left = s.indexOf(patterns[0]);
        int right = s.lastIndexOf(patterns[1]);
        return left >= 0 && left + patterns[0].length() <= right;
    }

    public static void main(String[] args) {
        System.out.println(hasMatch("leetcode", "ee*e"));
        System.out.println(hasMatch("crv", "crr*v"));
        System.out.println(hasMatch("luck", "u*"));
    }
}
