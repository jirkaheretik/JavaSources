package cz.braza.leetcode;

import java.util.Arrays;

/*
2982. Find Longest Special Substring That Occurs Thrice II
Medium
Topics
Companies
Hint
You are given a string s that consists of lowercase English letters.

A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.

Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.

A substring is a contiguous non-empty sequence of characters within a string.



Example 1:

Input: s = "aaaa"
Output: 2
Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
It can be shown that the maximum length achievable is 2.
Example 2:

Input: s = "abcdef"
Output: -1
Explanation: There exists no special substring which occurs at least thrice. Hence return -1.
Example 3:

Input: s = "abcaba"
Output: 1
Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
It can be shown that the maximum length achievable is 1.


Constraints:

3 <= s.length <= 5 * 10^5
s consists of only lowercase English letters.

NOTE: Same as L2981 with lower constraints
 */
public class L2982LongestSpecialSubstrThrice {
    /*
    For each char (26 possibilities) maintain list of 3 longest strings.
    Then go through this list and find max in 3rd column, or -1 if all zeroes
    Runs 18ms, beats 100% (L2982)
    Runs 2ms, beats 99.1% (L2981)
     */
    public static int maximumLength(String s) {
        int[][] map = new int[26][3];
        char last = '#'; // something not in actual string
        int lastCount = 0;
        for (char c: s.toCharArray()) {
            if (c == last)
                lastCount++;
            else {
                lastCount = 1;
                last = c;
            }
            // file `lastCount` for `c` into map, if needed:
            int row = c - 'a';
            if (map[row][0] < lastCount) {
                map[row][2] = map[row][1];
                map[row][1] = map[row][0];
                map[row][0] = lastCount;
            } else if (map[row][1] < lastCount) {
                map[row][2] = map[row][1];
                map[row][1] = lastCount;
            } else if (map[row][2] < lastCount)
                map[row][2] = lastCount;
        }
        // get result:
        int max = 0;
        for (int letter = 0; letter < map.length; letter++)
            if (map[letter][2] > max)
                max = map[letter][2];
        return max > 0 ? max : -1;
    }

    public static void main(String[] args) {
        System.out.println(maximumLength("aaaa"));
    }
}
