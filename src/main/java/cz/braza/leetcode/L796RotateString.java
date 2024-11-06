package cz.braza.leetcode;

/*
796. Rotate String
Easy
Topics
Companies
Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.

A shift on s consists of moving the leftmost character of s to the rightmost position.

For example, if s = "abcde", then it will be "bcdea" after one shift.


Example 1:

Input: s = "abcde", goal = "cdeab"
Output: true
Example 2:

Input: s = "abcde", goal = "abced"
Output: false


Constraints:

1 <= s.length, goal.length <= 100
s and goal consist of lowercase English letters.
 */
public class L796RotateString {
    /*
    First attempt. Check same lengths, return false if differ.
    Then take first character from one string, and try to find it in the other,
    where it divides the string into two parts - swap them and compare with the
    first string. Try to iterate over all such positions, returning true if match
    is found, false if we run out of options.
    Runs 0ms, beats 100% (Memory beats 9.1% only? with nothing we store?!)
     */
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        String seed = "" + s.charAt(0);
        int start = 0;
        while ((start = goal.indexOf(seed, start)) >= 0) {
            if (s.equals(goal.substring(start) + goal.substring(0, start)))
                return true;
            start++;
        }
        return false;
    }

}
