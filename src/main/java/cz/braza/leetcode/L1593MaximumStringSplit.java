package cz.braza.leetcode;

import java.util.*;

/*
1593. Split a String Into the Max Number of Unique Substrings
Medium
Topics
Companies
Hint
Given a string s, return the maximum number of unique substrings that the given string can be split into.

You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
Example 2:

Input: s = "aba"
Output: 2
Explanation: One way to split maximally is ['a', 'ba'].
Example 3:

Input: s = "aa"
Output: 1
Explanation: It is impossible to split the string any further.


Constraints:

1 <= s.length <= 16

s contains only lower case English letters.
 */
public class L1593MaximumStringSplit {
    /*
    Hospodskej pokus před půlnocí, prochází většinou testů, běží lineárně, ale nebacktrackuje,
    proto někdy to nedá správně (+- 1)
     */
    public int maxUniqueSplit(String s) {
        Deque<String> utrzky = new ArrayDeque<>();
        String leftOver = "";
        String current = "";
        for (char c: s.toCharArray()) {
            current += c;
            if (utrzky.contains(current))
                continue;
            utrzky.add(current);
            current = "";
        }
        // je-li current prázdné, voila hurá
        if (current.isEmpty()) return utrzky.size();
        do {
            current = utrzky.pop() + current;
            if (!utrzky.contains(current))
                return Math.max(utrzky.size() + 1, maxUniqueSplit(new StringBuilder(s).reverse().toString()));
        } while (true);
    }


    /*
    Backtracking solution
     */
    public int maxUniqueSplitSolution(String s) {
        return backtrack(0, s, new HashSet<>());
    }
    private int backtrack(int start, String s, HashSet<String> seen) {
        if (start == s.length()) {
            return 0;
        }
        int maxSplits = 0;
        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (!seen.contains(substring)) {
                seen.add(substring);
                maxSplits = Math.max(maxSplits, 1 + backtrack(end, s, seen));
                seen.remove(substring);
            }
        }
        return maxSplits;
    }

}
