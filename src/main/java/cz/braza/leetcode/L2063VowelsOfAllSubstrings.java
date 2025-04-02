package cz.braza.leetcode;

/*
2063. Vowels of All Substrings - https://leetcode.com/problems/vowels-of-all-substrings/
Medium
Topics
Companies
Hint
Given a string word, return the sum of the number of vowels ('a', 'e', 'i', 'o', and 'u') in every substring of word.

A substring is a contiguous (non-empty) sequence of characters within a string.

Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer. Please be careful during the calculations.



Example 1:

Input: word = "aba"
Output: 6
Explanation:
All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
- "b" has 0 vowels in it
- "a", "ab", "ba", and "a" have 1 vowel each
- "aba" has 2 vowels in it
Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.
Example 2:

Input: word = "abc"
Output: 3
Explanation:
All possible substrings are: "a", "ab", "abc", "b", "bc", and "c".
- "a", "ab", and "abc" have 1 vowel each
- "b", "bc", and "c" have 0 vowels each
Hence, the total sum of vowels = 1 + 1 + 1 + 0 + 0 + 0 = 3.
Example 3:

Input: word = "ltcd"
Output: 0
Explanation: There are no vowels in any substring of "ltcd".


Constraints:

1 <= word.length <= 10^5
word consists of lowercase English letters.
 */
public class L2063VowelsOfAllSubstrings {
    /*
    If a vowel is found, we count how many substrings it can appear in
    (distance-from-start * distance-to-end)
    Runs 6ms, beats 97%, 51 testcases
     */
    public long countVowels(String word) {
        int idx = 0;
        long count = 0;
        int length = word.length();
        for (char c: word.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                count += (long)(idx + 1) * (length - idx);
            idx++;
        }
        return count;
    }
}
