package cz.braza.leetcode;

/*
Daily 10.2.2025
SHOW: easy, two approaches

3174. Clear Digits
Easy
Topics
Companies
Hint
You are given a string s.

Your task is to remove all digits by doing this operation repeatedly:

Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.



Example 1:

Input: s = "abc"

Output: "abc"

Explanation:

There is no digit in the string.

Example 2:

Input: s = "cb34"

Output: ""

Explanation:

First, we apply the operation on s[2], and s becomes "c4".

Then we apply the operation on s[1], and s becomes "".



Constraints:

1 <= s.length <= 100
s consists only of lowercase English letters and digits.
The input is generated such that it is possible to delete all digits.
 */
public class L3174ClearDigits {
    /*
    Use StringBuilder to add characters, once we encounter a digit, we
    remove last character by calling sb.setLength().

    Runs 1ms, beats 100%, 688 testcases
     */
    public String clearDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray())
            if (c >= '0' && c <= '9')
                sb.setLength(sb.length() - 1);
            else sb.append(c);
        return sb.toString();
    }

    /*
    Run it backwards, when a digit is encountered, update counter how many characters to skip
    If zero to skip, just add characters to the stringbuilder.
    In the end reverse it and return as a string.
    Runs 1ms, beats 100%
     */
    public String clearDigitsBackwards(String s) {
        StringBuilder sb = new StringBuilder();
        int skip = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {
            char c = s.charAt(idx);
            if (c >= '0' && c <= '9')
                skip++;
            else if (skip > 0) skip--;
                else sb.append(c);
        }
        return sb.reverse().toString();
    }
}
