package cz.braza.leetcode;

/*
Daily 11.3.2025 - https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

1358. Number of Substrings Containing All Three Characters
Medium
Topics
Companies
Hint
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:

Input: s = "abc"
Output: 1


Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
public class L1358SubstringsContainingABC {
    /*
    Remember last position of each letter (a, b, c) in an array last.
    for each character, we check if that is the farthest one (min position), if not, we do not need to update min
    update last pos to current and update min if needed.
    if min >= 0 (all characters seen already), there are 1+min strings that end at current pos and contain all the letters

    Runs 9ms, beats 97.8%, 54 testcases
     */
    public int numberOfSubstrings(String s) {
        int[] last = {-1, -1, -1};
        int min = -1;
        int result = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            int pos = c - 'a'; // index in last array
            boolean checkMin = min == last[pos];
            last[pos] = idx;
            if (checkMin)
                min = last[0] <= last[1] && last[0] <= last[2] ? last[0] : last[1] < last[2] ? last[1] : last[2];
            if (min >= 0) result += 1 + min;
        }
        return result;
    }

    /*
    Instead of all the s.charAt() and s.length() checks, do s.toCharArray() and manage index manually

    Runs 7ms, beats 99.5%
     */
    public int numberOfSubstringsCharArray(String s) {
        int[] last = {-1, -1, -1};
        int min = -1;
        int result = 0;
        int idx = 0;
        for (char c: s.toCharArray()) {
            int pos = c - 'a'; // index in last array
            boolean checkMin = min == last[pos];
            last[pos] = idx++;
            if (checkMin)
                min = last[0] <= last[1] && last[0] <= last[2] ? last[0] : last[1] < last[2] ? last[1] : last[2];
            if (min >= 0) result += 1 + min;
        }
        return result;
    }
}
