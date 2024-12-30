package cz.braza.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
2182. Construct String With Repeat Limit
Medium
Topics
Companies
Hint
You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.

Return the lexicographically largest repeatLimitedString possible.

A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.



Example 1:

Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
Example 2:

Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.


Constraints:

1 <= repeatLimit <= s.length <= 105
s consists of lowercase English letters.
 */
public class L2182ConstructStringWithRepeatLimit {
    /*
    Count frequencies, create maxheap from it, build char array, then convert it to a result string.
    Runs 25ms, beats 67%
     */
    public String repeatLimitedString(String s, int repeatLimit) {
        // count frequencies
        int[] freq = new int[26];
        for (char c: s.toCharArray())
            freq[c - 'a']++;
        // build maxheap:
        PriorityQueue<int[]> znaky = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < freq.length; i++)
            if (freq[i] > 0)
                znaky.offer(new int[]{i, freq[i]});
        // build char array:
        char[] result = new char[s.length()];
        int index = 0;
        while(!znaky.isEmpty()) {
            // pick best:
            int[] curr = znaky.poll();
            int repeat = curr[1] > repeatLimit ? repeatLimit : curr[1];
            curr[1] -= repeat;
            for (int i = 0; i < repeat; i++)
                result[index++] = (char)('a' + curr[0]);
            // still something?
            if (curr[1] > 0) {
                if (znaky.isEmpty()) break; // can't do anything
                int[] next = znaky.poll();
                result[index++] = (char)('a' + next[0]);
                znaky.offer(curr);
                next[1]--;
                if (next[1] > 0) znaky.offer(next);
            }
        }
        return Arrays.toString(result).trim();
    }
}
