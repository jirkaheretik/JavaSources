package cz.braza.leetcode;

/*
2423. Remove Letter To Equalize Frequency
Easy
Topics
Companies
Hint
You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.

Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.

Note:

The frequency of a letter x is the number of times it occurs in the string.
You must remove exactly one letter and cannot choose to do nothing.


Example 1:

Input: word = "abcc"
Output: true
Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.
Example 2:

Input: word = "aazz"
Output: false
Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.


Constraints:

2 <= word.length <= 100
word consists of lowercase English letters only.
*/
class L2423RemoveLetterToEqualizeFrequency {
    /*
    Curious since this problem had lowest acceptance rate among easy questions, 17.8%, there are too many edge cases even in just 50 testcases.
    We count frequencies of each char, then run through them, returning false once we find anything that breaks, and a big multiple condition return in the end.

    Runs 0ms, beats 100%, 50 testcases, 5 wrong submissions.
    */
    public boolean equalFrequency(String word) {
        int[] freq = new int[26];
        for (char c: word.toCharArray())
            freq[c - 'a']++;
        int firstCount = 0;
        int first = 0;
        int secondCount = 0;
        int second = 0;
        for (int val: freq)
            if (val > 0) {
                if (firstCount == 0) {
                    firstCount = 1;
                    first = val;
                } else if (secondCount == 0 && val != first) {
                    if (val != 1 && first != 1 && (val > first + 1 || val < first - 1)) return false; // cannot fix anyway
                    secondCount = 1;
                    second = val;
                } else if (val == first) {
                    firstCount++;
                    if (secondCount > 1) return false; // cannot fix by removing just one char
                } else if (val == second) {
                    secondCount++;
                    if (firstCount > 1) return false; // cannot fix by removing just one char
                } else
                    return false; // third different non-zero frequency
            }
        return (secondCount == 0 && (firstCount == 1 || first == 1)) || (firstCount == 1 && first == second + 1) || (secondCount == 1 && second == first + 1) || (first == 1 && firstCount == 1) || (second == 1 && secondCount == 1);
    }
}
