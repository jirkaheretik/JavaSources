package cz.braza.leetcode;

/*
Daily 11.2.2025

1910. Remove All Occurrences of a Substring
Medium
Topics
Companies
Hint
Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:

Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".
Example 2:

Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".


Constraints:

1 <= s.length <= 1000
1 <= part.length <= 1000
s and part consists of lowercase English letters.
 */
public class L1910RemoveAllOccurencesOfSubstring {
    /*
    Very easy approach, probably not effective: just use internal string
    functions contains(), indexOf() and substring().

    Runs 1ms, beats 99.5%, 80 testcases
     */
    public String removeOccurrencesSimple(String s, String part) {
        int patternLength = part.length();
        while (s.contains(part)) {
            int pos = s.indexOf(part);
            s = s.substring(0, pos) + s.substring(pos + patternLength);
        }
        return s;
    }

    /*
    Partially own implementation - we append characters to string builder, and if
    it is the last char of the pattern and pattern is there, remove it (shorten length).
    Should be fewer checks, but still slower:

    Runs 2ms, beats 60.4%
     */
    public String removeOccurrencesSB(String s, String part) {
        StringBuilder sb = new StringBuilder();
        char last = part.charAt(part.length() - 1);
        int pattLength = part.length();
        for (char c: s.toCharArray()) {
            sb.append(c);
            if (c == last && sb.lastIndexOf(part) >= 0)
                sb.setLength(sb.length() - pattLength); // remove pattern (it cannot be anywhere except at the end)
        }
        return sb.toString();
    }
}
