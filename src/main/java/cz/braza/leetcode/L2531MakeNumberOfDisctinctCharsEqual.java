package cz.braza.leetcode;

/*
2531. Make Number of Distinct Characters Equal
Medium
Topics
Companies
Hint
You are given two 0-indexed strings word1 and word2.

A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].

Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.



Example 1:

Input: word1 = "ac", word2 = "b"
Output: false
Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
Example 2:

Input: word1 = "abcc", word2 = "aab"
Output: true
Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
Example 3:

Input: word1 = "abcde", word2 = "fghij"
Output: true
Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.


Constraints:

1 <= word1.length, word2.length <= 10^5
word1 and word2 consist of only lowercase English letters.
 */
public class L2531MakeNumberOfDisctinctCharsEqual {
    /*
    Plan: count character frequencies in each word separately.
    Then, from this array, count three values:
    - number of different characters (non zero frequency) - D
    - number of single characters (frequency == 1) - S
    - number of repeating characters (freq > 1) - M = D - S
    If needed, we also need to count (from both arrays):
    - number of SAME characters, different characters, both single and multiple...
    To return false:
    - abs(D1, D2) > 2
    - D1==D2 && (S1 == 0 && M2 == 0 || S2 == 0 && M1 == 0)
    - D1 > D2 (diff must be one or two, see above), multiple possibilities...
     */
    public boolean isItPossible(String word1, String word2) {
        // count frequencies first:
        int[] freq1 = new int[26];
        for (char c: word1.toCharArray()) freq1[c - 'a']++;
        int[] freq2 = new int[26];
        for (char c: word2.toCharArray()) freq2[c - 'a']++;
        int[] res1 = getResults(freq1);
        int[] res2 = getResults(freq2);
        if (res1[0] - res2[0] > 2 || res2[0] - res1[0] > 2) return false;
        if (res1[0] == res2[0] && (res1[1] == 0 && res2[2] == 0 || res1[2] == 0 && res2[1] == 0)) return false;
        // now we need frequencies compared:
        int[] bam = compareFrequencies(freq1, freq2);
        // get single cases I can resolve:
        // they differ by two, but there is at least one single in the higher one and one common multiple it the lower one
        if (res1[0] - res2[0] == 2 && bam[0] > 0 && bam[7] > 0) return true;
        if (res2[0] - res1[0] == 2 && bam[2] > 0 && bam[5] > 0) return true;
        // they do not differ and I can leave it such:
        if (res1[0] == res2[0] && (bam[0] > 0 && bam[2] > 0 || bam[1] > 0 && bam[3] > 0 || bam[4] > 0 && bam[6] > 0 || bam[5] > 0 && bam[7] > 0)) return true;
        // they differ by one, and I can either remove it and don't screw the other, or add to the lower one without changing the other:
        if ((res1[0] == res2[0] + 1) && ((bam[0] > 0 || bam[4] > 0) && bam[7] > 0)) return true;
        if ((res1[0] + 1 == res2[0]) && ((bam[2] > 0 || bam[6] > 0) && bam[5] > 0)) return true;
        return false; // if nothing else succeeded
    }

    public static int[] getResults(int[] frequencies) {
        int[] result = new int[3]; // 0 = all, 1 = single, 2 = multiple
        for (int f: frequencies)
            if (f > 0)
                if (f > 1) result[2]++;
                else result[1]++;
        result[0] = result[1] + result[2];
        return result;
    }

    public static int[] compareFrequencies(int[] freq1, int[] freq2) {
        int[] result = new int[8]; // 0 - S1, 1 - M1, 2 - S2, 3 - M2
        for (int idx = 0; idx < 26; idx++) {
            if (freq1[idx] > 0 && freq2[idx] == 0)
                if (freq1[idx] == 1) result[0]++;
                else result[1]++;
            else if (freq2[idx] > 0 && freq1[idx] == 0)
                if (freq2[idx] == 1) result[2]++;
                else result[3]++;
            else if (freq1[idx] > 0) {
                // both frequencies are there
                if (freq1[idx] == 1) result[4]++;
                else result[5]++;
                if (freq2[idx] == 1) result[6]++;
                else result[7]++;
            }
        }
        return result;
    }
}
