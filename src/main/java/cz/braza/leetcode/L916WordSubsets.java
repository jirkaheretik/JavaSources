package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
916. Word Subsets
Medium
Topics
Companies
You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.



Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]


Constraints:

1 <= words1.length, words2.length <= 10^4
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
 */
public class L916WordSubsets {
    /*
    Make a function to count char frequencies in any word. Then find an overset
    of all the frequencies from words2 (that is, maximal frequency of every letter).
    Then we go through the words1 list, count frequencies, and if frequency of every letter
    is the same or higher than in the totalMap (the overset), this is the "universal word"
    and we add it to the result list.
    Runs 13ms, beats 81.4%, 56 testcases
     */
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // preprocess words2 to get final map:
        int[] totalMap = new int[26];
        for (String w: words2) {
            int[] map = getFrequencies(w);
            for (int i = 0; i < 26; i++)
                if (map[i] > totalMap[i]) totalMap[i] = map[i];
        }
        // now look for words in words1 and compare their frequencies to totalMap
        List<String> result = new ArrayList<>();
        for (String w: words1) {
            int[] map = getFrequencies(w);
            boolean canBe = true;
            for (int i = 0; i < 26; i++)
                if (map[i] < totalMap[i]) {
                    canBe = false;
                    break; // no need to test other frequencies
                }
            if (canBe) result.add(w);
        }
        return result;
    }

    public static int[] getFrequencies(String w) {
        int[] result = new int[26];
        for (char c: w.toCharArray())
            result[c - 'a']++;
        return result;
    }
}
