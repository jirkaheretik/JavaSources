package cz.braza.leetcode;

/*
1684. Count the Number of Consistent Strings
Easy

You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.



Example 1:

Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:

Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:

Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 */
/// TODO:
/// První verze na chvostu rychlosti, Tomáíš mě zaúkoloval to vylepšit.
/// Druhá verze jen drobné zlepšení, také s.IndexOf jsem nahradil za s.contains
/// No a pak přepis přes tu mapu, která zásadně zrychlí vyhledání
public class L1684ConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        int consistent = 0;
        outer:
        for (String word: words) {
            for (char c : word.toCharArray())
                if (!allowed.contains("" + c))
                    continue outer; // next word
            consistent++;
        }
        return consistent;
    }

    public int countConsistentStringsReversed(String allowed, String[] words) {
        int consistent = words.length;
        for (String word: words)
            for (char c : word.toCharArray())
                if (!allowed.contains("" + c)) {
                    consistent--;
                    break;
                }
        return consistent;
    }

    public int countConsistentStringsWithBoolMap(String allowed, String[] words) {
        boolean[] okChars = new boolean[26];
        for (char c: allowed.toCharArray())
            okChars[c - 'a'] = true;
        int consistent = words.length;
        for (String word: words)
            for (char c: word.toCharArray())
                if (!okChars[c - 'a']) {
                    consistent--;
                    break;
                }
        return consistent;
    }

}
