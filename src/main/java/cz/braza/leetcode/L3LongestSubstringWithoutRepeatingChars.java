package cz.braza.leetcode;

/*
3. Longest Substring Without Repeating Characters
Medium
Topics
Companies
Hint
Given a string s, find the length of the longest
substring
 without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.
 */
public class L3LongestSubstringWithoutRepeatingChars {
    /*
    Map of frequencies, so that we can solve it in one pass (or maybe two, with the "left" pointer).
    We go char by char and count max length, and once we find a duplicity (frequency increases above 1)
    we update left pointer and frequency array
    Runs 2ms, beats 98.6%, 987 testcases
     */
    public int lengthOfLongestSubstring(String s) {
        // map of frequencies:
        int[] freqs = new int[255];
        boolean hasDuplicates = false;
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int code = (int) s.charAt(right);
            if (freqs[code] == 1) hasDuplicates = true;
            freqs[code]++;
            // if duplicates, move left window so that no duplicates there:
            while (hasDuplicates) {
                int lCode = (int) s.charAt(left++);
                if (freqs[lCode] == 2) hasDuplicates = false;
                freqs[lCode]--;
            }
            if (left + maxLength > s.length()) break; // no more better solutions
            if (right - left + 1 > maxLength) maxLength = right - left + 1;
        }
        return maxLength;
    }
}
