package cz.braza.leetcode;

import java.util.Arrays;

/*
14. Longest Common Prefix
Solved
Easy
Topics
Companies
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.


Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters.
 */
public class L14LongestCommonPrefix {
    // 1st version before any leetcode experience, bad performance
    // 8ms, beats 11 %
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        for (int i = 0; i <= 200; i++) {
            if (strs[0].length() <= i) return prefix; // we are done
            char newChar = strs[0].charAt(i);
            for (String item: strs)
                if (item.length() <= i || newChar != item.charAt(i))
                    return prefix;
            prefix += newChar;
        }
        return prefix; // array of same strings with length 200
    }

    // version 2 (thought): sort the array, then the longest common prefix must
    // be for 1st and last element as well, no need to consider other strings
    // result again: 8ms, beats 11%
    public String longestCommonPrefixSorted(String[] strs) {
        Arrays.sort(strs);
        String prefix = "";
        for (int index = 0; index < Math.min(strs[0].length(), strs[strs.length - 1].length()); index++)
            if (strs[0].charAt(index) != strs[strs.length - 1].charAt(index)) return prefix;
            else prefix += strs[0].charAt(index);
        return prefix;
    }

    /*
    Wow, this got FASTER than linear solution (next)!
    0ms, beats 100%
     */
    public String longestCommonPrefixSortedWithPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int maxCommonIndex = 0;
        while (maxCommonIndex < first.length() && maxCommonIndex < last.length() && first.charAt(maxCommonIndex) == last.charAt(maxCommonIndex))
            maxCommonIndex++;
        return first.substring(0, maxCommonIndex);
    }

    /*
    Idea: go through the array ONCE to find first and last string - O(n)
    Then compare those two only, do NOT add up prefix, just note index and return substring

    Result: 1ms, beats 71.74 %
     */
    public static String longestCommonPrefixMinimax(String[] strs) {
        String first = strs[0];
        String last = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].compareTo(first) < 0) first = strs[i];
            if (strs[i].compareTo(last) > 0) last = strs[i];
        }
        int maxCommonIndex = 0;
        while (maxCommonIndex < first.length() && maxCommonIndex < last.length() && first.charAt(maxCommonIndex) == last.charAt(maxCommonIndex))
            maxCommonIndex++;
        return first.substring(0, maxCommonIndex);
    }

    public static void main(String[] args) {
        String[] test = {"dog","racecar","car"};
        System.out.println(longestCommonPrefixMinimax(test));
    }
}
