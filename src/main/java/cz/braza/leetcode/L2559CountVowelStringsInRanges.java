package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
2559. Count Vowel Strings in Ranges
Medium
Topics
Companies
Hint
You are given a 0-indexed array of strings words and a 2D array of integers queries.

Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.

Return an array ans of size queries.length, where ans[i] is the answer to the ith query.

Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.



Example 1:

Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].
Example 2:

Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].


Constraints:

1 <= words.length <= 10^5
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 10^5
1 <= queries.length <= 10^5
0 <= li <= ri < words.length
 */
public class L2559CountVowelStringsInRanges {
    /*
    First we traverse words and precompute sum of vowel-strings up to a given index.
    We then use it for the queries - process each query and compute the result
    with two values from the precomputed sums.
    Runs 9ms, beats 35.8%, 93 test cases, time O(n+q), space O(n+q)
     */
    public int[] vowelStrings(String[] words, int[][] queries) {
        // precompute sum of vowel strings:
        int[] vowelStringsCount = new int[words.length];
        String VOWELS = "aeiou";
        int count = 0;
        for (int idx = 0; idx < words.length; idx++) {
            String w = words[idx];
            if (VOWELS.contains("" + w.charAt(0)) && VOWELS.contains("" + w.charAt(w.length() - 1)))
                count++;
            vowelStringsCount[idx] = count;
        }
        // compute the result:
        int[] result = new int[queries.length];
        for (int idx = 0; idx < queries.length; idx++)
            result[idx] = vowelStringsCount[queries[idx][1]] - (queries[idx][0] > 0 ? vowelStringsCount[queries[idx][0] - 1] : 0);
        return result;
    }

    /*
    Instead of String of vowels, use hash set with chars only
    Runs 8ms, beats 48.3%
     */
    public int[] vowelStringsHashSet(String[] words, int[][] queries) {
        // precompute sum of vowel strings:
        int[] vowelStringsCount = new int[words.length];
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int count = 0;
        for (int idx = 0; idx < words.length; idx++) {
            String w = words[idx];
            if (vowels.contains(w.charAt(0)) && vowels.contains(w.charAt(w.length() - 1)))
                count++;
            vowelStringsCount[idx] = count;
        }
        // compute the result:
        int[] result = new int[queries.length];
        for (int idx = 0; idx < queries.length; idx++)
            result[idx] = vowelStringsCount[queries[idx][1]] - (queries[idx][0] > 0 ? vowelStringsCount[queries[idx][0] - 1] : 0);
        return result;
    }

}
