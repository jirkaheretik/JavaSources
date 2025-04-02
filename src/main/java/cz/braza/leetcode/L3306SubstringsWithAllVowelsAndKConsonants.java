package cz.braza.leetcode;

import java.util.HashMap;

/*
Daily 10.3.2025 - https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/

3306. Count of Substrings Containing Every Vowel and K Consonants II
Medium
Topics
Companies
Hint
You are given a string word and a non-negative integer k.

Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.



Example 1:

Input: word = "aeioqq", k = 1

Output: 0

Explanation:

There is no substring with every vowel.

Example 2:

Input: word = "aeiou", k = 0

Output: 1

Explanation:

The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".

Example 3:

Input: word = "ieaouqqieaouqq", k = 1

Output: 3

Explanation:

The substrings with every vowel and one consonant are:

word[0..5], which is "ieaouq".
word[6..11], which is "qieaou".
word[7..12], which is "ieaouq".


Constraints:

5 <= word.length <= 2 * 10^5
word consists only of lowercase English letters.
0 <= k <= word.length - 5
 */
public class L3306SubstringsWithAllVowelsAndKConsonants {
    /*
    From editorial, slightly updated, inlined isVowel, kept hashmap for now
    Runs 196ms, beats 70.9%, 768 testcases
     */
    public long countOfSubstrings(String word, int k) {
        long numValidSubstrings = 0;
        int start = 0;
        int end = 0;
        // keep track of counts of vowels and consonants
        HashMap<Character, Integer> vowelCount = new HashMap<>();
        int consonantCount = 0;

        // compute index of next consonant for all indices
        int[] nextConsonant = new int[word.length()];
        int nextConsonantIndex = word.length();
        for (int i = word.length() - 1; i >= 0; i--) {
            nextConsonant[i] = nextConsonantIndex;
            char c = word.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') nextConsonantIndex = i;
        }

        // start sliding window
        while (end < word.length()) {
            // insert new letter
            char c = word.charAt(end);

            // update counts
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vowelCount.put(c, vowelCount.getOrDefault(c, 0) + 1);
            else
                consonantCount++;

            // shrink window if too many consonants in our window
            while (consonantCount > k) {
                char startLetter = word.charAt(start);
                if (startLetter == 'a' || startLetter == 'e' || startLetter == 'i' || startLetter == 'o' || startLetter == 'u') {
                    vowelCount.put(startLetter, vowelCount.get(startLetter) - 1);
                    if (vowelCount.get(startLetter) == 0)
                        vowelCount.remove(startLetter);
                } else
                    consonantCount--;
                start++;
            }

            // while we have a valid window, try to shrink it
            while (start < word.length() && vowelCount.keySet().size() == 5 && consonantCount == k) {
                // count the current valid substring, as well as valid substrings produced by appending more vowels
                numValidSubstrings += nextConsonant[end] - end;
                char startLetter = word.charAt(start);
                if (startLetter == 'a' || startLetter == 'e' || startLetter == 'i' || startLetter == 'o' || startLetter == 'u') {
                    vowelCount.put(startLetter, vowelCount.get(startLetter) - 1);
                    if (vowelCount.get(startLetter) == 0)
                        vowelCount.remove(startLetter);
                } else
                    consonantCount--;

                start++;
            }
            end++;
        }

        return numValidSubstrings;
    }
}
