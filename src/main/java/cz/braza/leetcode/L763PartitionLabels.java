package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Daily 30.3.2025 - https://leetcode.com/problems/partition-labels/
763. Partition Labels
Medium
Topics
Companies
Hint
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.



Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class L763PartitionLabels {
    /*
    Line sweep or something - mark starts and ends of every lowercase letter blocks,
    then count blocks that are running (started), and decrease count when they have ended.
    If count is 0, there is a block, we add it to the result.
    Runs 2ms, beats 99.4%, 118 testcases
     */
    public List<Integer> partitionLabels(String s) {
        // first map the blocks (start and end of a given char block)
        int[] starts = new int[s.length()];
        int[] ends = new int[s.length()];
        for (char c = 'a'; c <= 'z'; c++) {
            int left = s.indexOf(c);
            if (left >= 0) {
                int right = s.lastIndexOf(c);
                starts[left]++;
                ends[right]++;
            }
        }
        System.out.println(Arrays.toString(starts));
        System.out.println(Arrays.toString(ends));
        // then construct the result:
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int lastIndex = -1;
        for (int i = 0; i < starts.length; i++) {
            count += starts[i] - ends[i];
            if (count == 0) {
                result.add(i - lastIndex);
                lastIndex = i;
            }
        }
        return result;
    }

    /*
    In previous version we need to traverse the array (or even two) of the length same as s - input string.
    But there are only 26 possible pairs (start-end) disregarding the length of a string.
    We can thus just compute start-end for each character, sort the array, and find splits there.
    Runs 4ms, beats 92.8%, possibly due to low contraints (s.length() <= 500).
     */
    public List<Integer> partitionLabelsNew(String s) {
        // first map the blocks (start and end of a given char block)
        int[][] blocks = new int[26][2];
        for (char c = 'a'; c <= 'z'; c++) {
            int left = s.indexOf(c);
            if (left >= 0) {
                int right = s.lastIndexOf(c);
                blocks[c - 'a'] = new int[]{left, right};
            }
        }
        Arrays.sort(blocks, Comparator.comparingInt(a -> a[0]));
        // then construct the result:
        List<Integer> result = new ArrayList<>();
        int lastIndex = 0;
        int lastStart = 0;
        for (int[] block: blocks)
            if (block[0] > lastIndex) {
                result.add(lastIndex - lastStart + 1);
                lastStart = block[0];
                lastIndex = block[1];
            } else if (block[1] > lastIndex) lastIndex = block[1];
        // add the rest:
        result.add(lastIndex - lastStart + 1);
        return result;
    }

    /*
    Instead of processing whole 26x2 array all the time, just note how many different letters we have and process/sort only them.
    Again, runs 4ms too :-(
     */
    public List<Integer> partitionLabelsNewV2(String s) {
        // first map the blocks (start and end of a given char block)
        int[][] blocks = new int[26][2];
        int index = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int left = s.indexOf(c);
            if (left >= 0) {
                int right = s.lastIndexOf(c);
                blocks[index++] = new int[]{left, right};
            }
        }
        Arrays.sort(blocks, 0, index, Comparator.comparingInt(a -> a[0]));
        // then construct the result:
        List<Integer> result = new ArrayList<>();
        int lastIndex = 0;
        int lastStart = 0;
        for (int idx = 0; idx < index; idx++)
            if (blocks[idx][0] > lastIndex) {
                result.add(lastIndex - lastStart + 1);
                lastStart = blocks[idx][0];
                lastIndex = blocks[idx][1];
            } else if (blocks[idx][1] > lastIndex) lastIndex = blocks[idx][1];
        // add the rest:
        result.add(lastIndex - lastStart + 1);
        return result;
    }

    public List<Integer> partitionLabelsMartin(String s) {
        List<Integer> result = new ArrayList<>();
        int firstIndex = 0;
        int lastIndex = 0;
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!visited[c - 'a']) {
                visited[c - 'a'] = true;
                int pos = s.lastIndexOf(c);
                if (pos > lastIndex) lastIndex = pos;
            }
            if (i == lastIndex) {
                result.add(lastIndex - firstIndex + 1);
                firstIndex = i + 1;
            }
        }
        return result;
    }
}
