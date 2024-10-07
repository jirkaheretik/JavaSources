package cz.braza.leetcode;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, return true if s2 contains a
 * permutation
 *  of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * Remark: PERMUTATION must mean the characters are one after another, not just "somewhere in the string"
 */
public class L567StringPermutation {
    private static int[] getFrequencies(String s) {
        int[] freqs = new int[26]; // already zeroed
        for (char c: s.toCharArray())
            freqs[c - 97]++;
        return freqs;
    }
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1freqs = getFrequencies(s1);
        int[] s2freqs = getFrequencies(s2.substring(0, s1.length()));
        if (Arrays.compare(s1freqs, s2freqs) == 0) return true;
        int start = 0;
        while (start + s1.length() < s2.length()) {
            // remove first chars frequency:
            s2freqs[s2.charAt(start) - 97]--;
            // add new chars frequency:
            s2freqs[s2.charAt(start + s1.length()) - 97]++;
            start++;
            if (Arrays.compare(s1freqs, s2freqs) == 0) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String s1 = "ab";
        String s2A = "eidbaooo";
        String s2B = "eidboaoo";
        System.out.println(checkInclusion(s1, s2A));
        System.out.println(checkInclusion(s1, s2B));
    }
}
