package cz.braza.leetcode;

/*
1941. Check if All Characters Have Equal Number of Occurrences
Easy
Topics
Companies
Hint
Given a string s, return true if s is a good string, or false otherwise.

A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).



Example 1:

Input: s = "abacbc"
Output: true
Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
Example 2:

Input: s = "aaabb"
Output: false
Explanation: The characters that appear in s are 'a' and 'b'.
'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class L1941CheckCharFrequenciesInString {
    /*
    Count character frequencies.
    Then process frequencies, and if we find 2 different non zero values, return false,
    otherwise return true in the end.

    Runs 1ms, beats 100%, 134 testcases
     */
    public boolean areOccurrencesEqual(String s) {
        int[] freq = new int[26];
        for (char c: s.toCharArray()) freq[c - 'a']++;
        int fVal = 0;
        for (int f: freq)
            if (f != 0)
                if (fVal == 0) fVal = f;
                else if (f != fVal) return false;
        return true;
    }
}
