package cz.braza.leetcode;

/*
1422. Maximum Score After Splitting a String
Easy
Topics
Companies
Hint
Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.



Example 1:

Input: s = "011101"
Output: 5
Explanation:
All possible ways of splitting s into two non-empty substrings are:
left = "0" and right = "11101", score = 1 + 4 = 5
left = "01" and right = "1101", score = 1 + 3 = 4
left = "011" and right = "101", score = 1 + 2 = 3
left = "0111" and right = "01", score = 1 + 1 = 2
left = "01110" and right = "1", score = 2 + 1 = 3
Example 2:

Input: s = "00111"
Output: 5
Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
Example 3:

Input: s = "1111"
Output: 3


Constraints:

2 <= s.length <= 500
The string s consists of characters '0' and '1' only.
 */
public class L1422MaxScoreAfterStringSplit {
    /*
    First attempt - count ones in the string.
    Then iterate from left to right and compute the score, remembering max
    Runs 1ms, beats 97.4%
     */
    public int maxScore(String s) {
        // compute zeroes and ones
        int ones = 0;
        for (char c: s.toCharArray())
            if (c == '1') ones++;
        // iterate and update max score:
        int maxScore = 0;
        int zeroesFound = 0;
        int onesFound = 0;
        int length = s.length();
        for (int idx = 0; idx < length - 1; idx++) {
            if (s.charAt(idx) == '0') zeroesFound++;
            else onesFound++;
            // count score and compare:
            int score = zeroesFound + ones - onesFound;
            if (score > maxScore) maxScore = score;
        }
        return maxScore;
    }

    /*
    Split should ALWAYS be after a zero, or specifically between 01, except:
    - all ones, split after first char
    - all zeroes, split before last char
    - number of ones, follower by number of zeroes - pick greater of the above
    Runs 1ms, beats 97.4%
     */
    public int maxScoreUpdated(String s) {
        // compute zeroes and ones
        int ones = 0;
        for (char c: s.toCharArray())
            if (c == '1') ones++;
        // iterate and update max score:
        // set initial max score to the split after first char:
        int length = s.length();
        int maxScore = s.charAt(0) == '0' ? ones + 1 : ones - 1;
        // what about split at the end? (before last char)
        // only if zero is at the end we actually count it, otherwise it is coped ok in the algorithm
        if (s.charAt(length - 1) == '0' && length - ones - 1 > maxScore)
            maxScore = length - ones - 1;
        int zeroesFound = 0;
        for (int idx = 0; idx < length - 1; idx++)
            if (s.charAt(idx) == '0') {
                zeroesFound++;
                if (s.charAt(idx + 1) == '1') {
                    // count score and compare:
                    int score = 2 * zeroesFound + ones - idx - 1;
                    if (score > maxScore) maxScore = score;
                }
            }
        return maxScore;
    }
}
