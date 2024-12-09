package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
3152. Special Array II
Medium
Topics
Companies
Hint
An array is considered special if every pair of its adjacent elements contains two numbers with different parity.

You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that
subarray
 nums[fromi..toi] is special or not.

Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.



Example 1:

Input: nums = [3,4,1,2,6], queries = [[0,4]]

Output: [false]

Explanation:

The subarray is [3,4,1,2,6]. 2 and 6 are both even.

Example 2:

Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]

Output: [false,true]

Explanation:

The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= queries.length <= 105
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */
public class L3152SpecialArray {
    /*
    We create a list of special subintervals (alternating odd-even, both start and end index DO belong to the interval.
    Then for each query, we binary search the list for left in the list, and result is whether right is in the same interval
    Runs 23ms, beats 15%
     */
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        // 1. process nums and create array to get answers from
        List<int[]> intervals = new ArrayList<>();
        int start = 0;
        int index = 1;
        while (index < nums.length) {
            if (nums[index] % 2 == nums[index - 1] % 2) {
                // special condition fail, remember last interval
                intervals.add(new int[]{start, index - 1});
                start = index;
            }
            index++;
        }
        intervals.add(new int[]{start, index - 1});
        for (int[] interval: intervals)
            System.out.printf("Special interval indices %d-%d%n", interval[0], interval[1]);
        // 2. process queries
        boolean[] result = new boolean[queries.length];
        for (int idx = 0; idx < queries.length; idx++) {
            // find interval where query[0] is:
            int left = 0;
            int right = intervals.size() - 1;
            while (left <= right) {
                int current = left + (right - left) / 2;
                int[] curInt = intervals.get(current);
                if (queries[idx][0] >= curInt[0] && queries[idx][0] <= curInt[1]) {
                    result[idx] = queries[idx][1] >= curInt[0] && queries[idx][1] <= curInt[1];
                    break; // we are done here
                }
                if (queries[idx][0] > curInt[1])
                    left = current + 1;
                else
                    right = current - 1;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    /*
    Same as before, just static array instead of ArrayList
    Runs 22ms, beats 15% :-(
     */
    public boolean[] isArraySpecialNoList(int[] nums, int[][] queries) {
        // 1. process nums and create array to get answers from
        int[][] intervals = new int[nums.length][];
        int intIndex = 0;
        //List<int[]> intervals = new ArrayList<>();
        int start = 0;
        int index = 1;
        while (index < nums.length) {
            if (nums[index] % 2 == nums[index - 1] % 2) {
                // special condition fail, remember last interval
                intervals[intIndex++] = new int[]{start, index - 1};
                start = index;
            }
            index++;
        }
        intervals[intIndex++] = new int[]{start, index - 1};
        for (int[] interval: intervals)
            System.out.printf("Special interval indices %d-%d%n", interval[0], interval[1]);
        // 2. process queries
        boolean[] result = new boolean[queries.length];
        for (int idx = 0; idx < queries.length; idx++) {
            // find interval where query[0] is:
            int left = 0;
            int right = intIndex - 1;
            while (left <= right) {
                int current = left + (right - left) / 2;
                int[] curInt = intervals[current];
                if (queries[idx][0] >= curInt[0] && queries[idx][0] <= curInt[1]) {
                    result[idx] = queries[idx][1] >= curInt[0] && queries[idx][1] <= curInt[1];
                    break; // we are done here
                }
                if (queries[idx][0] > curInt[1])
                    left = current + 1;
                else
                    right = current - 1;
            }
        }
        return result;
    }

    /*
    Find just "violating indices" (same parity as previous element),
    then for each query look if it contains such an index (not special) or not (then it must be special)
    Runs 6ms, beats 24.6%
     */
    public boolean[] isArraySpecialUpdatedEditorial(int[] nums, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        ArrayList<Integer> violatingIndices = new ArrayList<>();
        for (int i = 1; i < nums.length; i++)
            // same parity, found violating index
            if (nums[i] % 2 == nums[i - 1] % 2)
                violatingIndices.add(i);
        for (int i = 0; i < queries.length; i++)
            result[i] = !binarySearch(queries[i][0] + 1, queries[i][1], violatingIndices);
        return result;
    }

    private static boolean binarySearch(int start, int end, ArrayList<Integer> violatingIndices) {
        int left = 0;
        int right = violatingIndices.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int violatingIndex = violatingIndices.get(mid);

            if (violatingIndex < start) {
                // check right half
                left = mid + 1;
            } else if (violatingIndex > end) {
                // check left half
                right = mid - 1;
            } else {
                // violatingIndex falls in between start and end
                return true;
            }
        }
        return false;
    }

    /*
    Find just "violating indices" (same parity as previous element),
    then for each query look if it contains such an index (not special) or not (then it must be special)
    As before, just array instead of List
    Runs 5ms, beats 29.3%
     */
    public boolean[] isArraySpecialUpdatedEditorialNoList(int[] nums, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        int[] violatingIndices = new int[nums.length];
        int violatingIdx = 0;
        for (int i = 1; i < nums.length; i++)
            // same parity, found violating index
            if (nums[i] % 2 == nums[i - 1] % 2)
                violatingIndices[violatingIdx++] = i;
        for (int i = 0; i < queries.length; i++)
            result[i] = !binarySearch(queries[i][0] + 1, queries[i][1], violatingIndices, violatingIdx);
        return result;
    }

    private static boolean binarySearch(int start, int end, int[] violatingIndices, int arrLength) {
        int left = 0;
        int right = arrLength - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int violatingIndex = violatingIndices[mid];

            if (violatingIndex < start)
                left = mid + 1;
            else if (violatingIndex > end)
                right = mid - 1;
            else
                return true;
        }
        return false;
    }
}
